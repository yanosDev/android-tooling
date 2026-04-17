@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

@file:YDRevisionIn(implementedAt = "2026-04-17", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.themes.YDRootTheme

@Composable
internal fun YDStyleGuideApp() {
    YDRootTheme {
//        val navState = rememberStyleguideNavState(start = YDStyleNavKey.Home)
//        NavDisplay()
    }
}