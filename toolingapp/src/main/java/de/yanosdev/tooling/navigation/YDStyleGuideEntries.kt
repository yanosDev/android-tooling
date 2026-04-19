@file:YDRevisionIn(implementedAt = "2026-04-17", revisionAfterInDays = 365)

package de.yanosdev.tooling.navigation

import androidx.navigation3.runtime.EntryProviderScope
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.tooling.ui.home.HomeScreen
import de.yanosdev.tooling.ui.home.viewmodel.HomeViewModelImpl

internal fun EntryProviderScope<YDStyleGuideNavKey>.ydStyleGuideNavEntries(navigator: YDStyleGuideNavigator) {
    entry<YDStyleGuideNavKey.Home> {
        HomeScreen(
            viewModel = HomeViewModelImpl(),
            navBack = {}
        )
    }
}
