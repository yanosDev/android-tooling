@file:YDRevisionIn(implementedAt = "2026-04-27", revisionAfterInDays = 365)

package de.yanosdev.core.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import de.yanosdev.annotation.YDRevisionIn
import kotlinx.coroutines.CoroutineScope

/**
 * Base class for DataStore-backed preference containers.
 *
 * Subclass this and declare preference properties using [preference] or [nullablePreference].
 * The [dataStore] and [scope] are typically provided by DI as application-scoped singletons.
 *
 * ```kotlin
 * class AppPreferences(
 *     dataStore: DataStore<Preferences>,
 *     scope: CoroutineScope,
 * ) : YDPreferences(dataStore, scope) {
 *
 *     var isOnboarded: Boolean by preference(booleanPreferencesKey("is_onboarded"), default = false)
 *     var email: String? by nullablePreference(stringPreferencesKey("email"))
 * }
 * ```
 *
 * Reads are synchronous — backed by an in-memory [kotlinx.coroutines.flow.StateFlow] cache that starts collecting
 * eagerly from DataStore on instantiation. Writes return immediately and persist
 * asynchronously. Observe [YDPreferenceItem.flow] for reactive updates.
 */
abstract class YDPreferences(
    private val dataStore: DataStore<Preferences>,
    protected val scope: CoroutineScope,
) {
    /**
     * Creates a non-nullable preference delegate.
     *
     * Returns [default] when the key is absent. [clear][YDPreferenceItem.clear] removes
     * the key so the next read returns [default] again.
     */
    protected fun <T : Any> preference(
        key: Preferences.Key<T>,
        default: T,
    ): YDPreferenceItem<T> = YDPreferenceItem(
        dataStore = dataStore,
        scope = scope,
        read = { prefs -> prefs[key] ?: default },
        write = { prefs, value -> prefs[key] = value },
        onClear = { prefs -> prefs.remove(key) },
    )

    /**
     * Creates a nullable preference delegate.
     *
     * Returns `null` when the key is absent. Setting `null` removes the key from the store,
     * equivalent to calling [YDPreferenceItem.clear].
     */
    protected fun <T : Any> nullablePreference(
        key: Preferences.Key<T>,
    ): YDPreferenceItem<T?> = YDPreferenceItem(
        dataStore = dataStore,
        scope = scope,
        read = { prefs -> prefs[key] },
        write = { prefs, value -> if (value != null) prefs[key] = value else prefs.remove(key) },
        onClear = { prefs -> prefs.remove(key) },
    )

    /**
     * Creates a non-nullable enum preference delegate stored as its [Enum.name] string.
     *
     * Returns [default] when the key is absent or when the stored string no longer matches any
     * enum constant (e.g. after a rename). [clear][YDPreferenceItem.clear] resets to [default].
     */
    protected inline fun <reified T : Enum<T>> enumPreference(
        key: Preferences.Key<String> = stringPreferencesKey(T::class.java.simpleName),
        default: T,
    ): YDPreferenceItem<T> = createEnumPreferenceItem(
        key = key,
        default = default,
        fromString = { runCatching { enumValueOf<T>(it) }.getOrNull() },
    )

    @PublishedApi
    internal fun <T : Enum<T>> createEnumPreferenceItem(
        key: Preferences.Key<String>,
        default: T,
        fromString: (String) -> T?,
    ): YDPreferenceItem<T> = YDPreferenceItem(
        dataStore = dataStore,
        scope = scope,
        read = { prefs -> prefs[key]?.let(fromString) ?: default },
        write = { prefs, value -> prefs[key] = value.name },
        onClear = { prefs -> prefs.remove(key) },
    )
}
