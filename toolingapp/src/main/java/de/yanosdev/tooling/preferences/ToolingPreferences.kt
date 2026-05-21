@file:YDRevisionIn(implementedAt = "2026-04-27", revisionAfterInDays = 365)

package de.yanosdev.tooling.preferences

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.core.preferences.YDPreferences
import de.yanosdev.core.util.NightMode
import kotlinx.coroutines.CoroutineScope

private val Context.dataStore by preferencesDataStore(name = "tooling_preferences")

class ToolingPreferences(
    context: Context,
    scope: CoroutineScope,
) : YDPreferences(
    dataStore = context.applicationContext.dataStore,
    scope = scope,
) {
    var nightMode: NightMode by enumPreference(default = NightMode.FollowSystem)
}
