@file:YDRevisionIn(implementedAt = "2026-04-27", revisionAfterInDays = 365)

package de.yanosdev.tooling

import android.app.Application
import android.os.Build
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.core.util.setNightMode
import de.yanosdev.tooling.preferences.ToolingPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class StyleguideApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    lateinit var preferences: ToolingPreferences
        private set

    override fun onCreate() {
        super.onCreate()
        preferences = ToolingPreferences(context = this, scope = applicationScope)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            setNightMode(mode = preferences.nightMode)
        }
    }
}
