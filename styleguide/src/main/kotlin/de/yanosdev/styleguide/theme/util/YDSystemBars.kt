@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.util

import android.content.Context
import android.os.Build
import android.view.View
import android.view.WindowInsetsController
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.core.util.findActivity

@Suppress("DEPRECATION")
internal fun Context.updateNavBar(color: Color) {
    val isLightBackground = color.luminance() > 0.5f

    findActivity().window.apply {
        // For SDK < 30 (Android 11) we need to update the navigation bar color because we are not drawing edge-to-edge
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            navigationBarColor = color.toArgb()
        }

        // For SDK 30 (Android 11) we need to apply the legacy flags in addition to using the WindowInsetsController
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R) {
            decorView.systemUiVisibility = if (isLightBackground) {
                decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            } else {
                decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            insetsController?.setSystemBarsAppearance(
                if (isLightBackground) WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS else 0,
                WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
            )
        }
    }
}

@Suppress("DEPRECATION")
internal fun Context.updateStatusBar(color: Color) {
    val isLightBackground = color.luminance() > 0.5f

    findActivity().window.apply {
        // For SDK < 30 (Android 11) we need to update the status bar color because we are not drawing edge-to-edge
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            statusBarColor = color.toArgb()
        }

        // For SDK 30 (Android 11) we need to apply the legacy flags in addition to using the WindowInsetsController
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R) {
            decorView.systemUiVisibility = if (isLightBackground) {
                decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            insetsController?.setSystemBarsAppearance(
                if (isLightBackground) WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS else 0,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        }
    }
}