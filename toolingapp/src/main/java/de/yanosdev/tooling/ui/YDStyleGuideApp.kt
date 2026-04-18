@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

@file:YDRevisionIn(implementedAt = "2026-04-17", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.themes.YDRootTheme
import de.yanosdev.tooling.navigation.YDStyleGuideNavKey
import de.yanosdev.tooling.navigation.rememberYDStyleGuideNavState
import de.yanosdev.tooling.navigation.rememberYDStyleGuideNavigator
import de.yanosdev.tooling.navigation.ydStyleGuideNavEntries

@Composable
internal fun YDStyleGuideApp() {
    YDRootTheme {
        val navState = rememberYDStyleGuideNavState(start = YDStyleGuideNavKey.Home)
        val navigator = rememberYDStyleGuideNavigator(state = navState)
        NavDisplay(
            entries = navState.entries(
                onProvideEntry = entryProvider { ydStyleGuideNavEntries(navigator) }
            ),
            onBack = navigator::navigateBack,
        )
    }
}