@file:YDRevisionIn(implementedAt = "2026-04-17", revisionAfterInDays = 365)

package de.yanosdev.tooling

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.tooling.ui.YDStyleGuideApp

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // On versions < SDK 30 Modifier.navigationBarsPadding() always returns 0.dp, which
            // makes it incompatible for edge to edge.
            enableEdgeToEdge()
        }
        /* TODO: Implement this
        // For API >= 31, we call setApplicationNightMode here, as it will not apply properly on
        // first launch for API 35 when called in Application.onCreate.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            setNightMode(mode = systemSettingsRepository.nightModeState.value)
        }

        systemSettingsRepository.updateAppearance(
            appearance = when (systemInfoRepository.uiNightMode) {
                Configuration.UI_MODE_NIGHT_YES -> AppearanceDto.Dark
                else -> AppearanceDto.Light
            }
        )

        */
        super.onCreate(savedInstanceState)
        setContent {
            YDStyleGuideApp()
        }
    }
}