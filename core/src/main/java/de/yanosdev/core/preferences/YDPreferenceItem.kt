@file:YDRevisionIn(implementedAt = "2026-04-27", revisionAfterInDays = 365)

package de.yanosdev.core.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import de.yanosdev.annotation.YDRevisionIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Property delegate for a single DataStore preference entry.
 *
 * Reads return the latest cached value from an eagerly-started [StateFlow] — no suspend,
 * no blocking. Writes launch a coroutine on [scope] and return immediately. If DataStore
 * throws an IO error the cache falls back to the initial default.
 *
 * Create instances via [YDPreferences.preference] or [YDPreferences.nullablePreference].
 *
 * @param T The stored value type. May be nullable for optional preferences.
 */
class YDPreferenceItem<T> @PublishedApi internal constructor(
    private val dataStore: DataStore<Preferences>,
    private val scope: CoroutineScope,
    read: (Preferences) -> T,
    private val write: suspend (MutablePreferences, T) -> Unit,
    private val onClear: suspend (MutablePreferences) -> Unit,
) : ReadWriteProperty<Any?, T> {

    /** Observable stream of the preference value, updated on every DataStore write. */
    val flow: Flow<T> get() = state

    private val state: StateFlow<T> = dataStore.data
        .catch { emit(emptyPreferences()) }
        .map(read)
        .stateIn(
            scope = scope,
            started = SharingStarted.Eagerly,
            initialValue = read(emptyPreferences()),
        )

    override fun getValue(thisRef: Any?, property: KProperty<*>): T = state.value

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        scope.launch { dataStore.edit { prefs -> write(prefs, value) } }
    }

    /** Removes the key from the store so the next read returns the default. */
    fun clear() {
        scope.launch { dataStore.edit { prefs -> onClear(prefs) } }
    }
}
