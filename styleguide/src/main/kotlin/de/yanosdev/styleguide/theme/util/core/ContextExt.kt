@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.util.core

import android.app.UiModeManager
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.getSystemService
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.util.core.model.NightMode

/**
 * Opens any Uri.
 */
fun Context.openUri(uri: Uri) {
    try {
        val normalizedUri = uri.normalizeScheme()
        startActivity(Intent(Intent.ACTION_VIEW, normalizedUri))
    } catch (e: ActivityNotFoundException) {
        e
        // TODO: Implement logger
    }
}

/**
 * Sets the current [de.yanosdev.styleguide.theme.util.core.model.NightMode] for the app.
 *
 * Generally, for S (12) and higher, we want to use the modern way of setting the night mode via the
 * [UiModeManager]. This is necessary to avoid issues with edge-to-edge when switching the night
 * mode during runtime.
 */
fun Context.setNightMode(mode: NightMode) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        requireNotNull(getSystemService<UiModeManager>()).setApplicationNightMode(mode.uiModeManagerValue)
    } else {
        AppCompatDelegate.setDefaultNightMode(mode.appCompatDelegateValue)
    }
}

private val NightMode.uiModeManagerValue
    get() = when (this) {
        NightMode.FollowSystem -> UiModeManager.MODE_NIGHT_AUTO
        NightMode.No -> UiModeManager.MODE_NIGHT_NO
        NightMode.Yes -> UiModeManager.MODE_NIGHT_YES
    }

private val NightMode.appCompatDelegateValue
    get() = when (this) {
        NightMode.FollowSystem -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        NightMode.No -> AppCompatDelegate.MODE_NIGHT_NO
        NightMode.Yes -> AppCompatDelegate.MODE_NIGHT_YES
    }
