@file:YDRevisionIn(implementedAt = "2026-04-17", revisionAfterInDays = 365)

package de.yanosdev.tooling.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import de.yanosdev.annotation.YDRevisionIn


@Composable
internal fun rememberYDStyleGuideNavigator(state: YDStyleGuideNavState) =
    remember { YDStyleGuideNavigator(state = state) }

internal class YDStyleGuideNavigator(private val state: YDStyleGuideNavState) {
    fun navigate(key: YDStyleGuideNavKey) {
        state.push(key = key)
    }

    fun navigateBack() {
        state.pop()
    }

    val navigationIcon: @Composable () -> Unit
        get() = if (state.backStackSize > 1) {
            {
                //TODO:
                // NavigationUpIcon(onNavigateUp = ::navigateBack)
            }
        } else {
            {}
        }
}