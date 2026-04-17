@file:YDRevisionIn(implementedAt = "2026-04-17", revisionAfterInDays = 365)

package de.yanosdev.tooling.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import de.yanosdev.annotation.YDRevisionIn

internal class YDStyleGuideNavState(private val backStack: NavBackStack<YDStyleNavKey>) {
    val backStackSize by backStack::size

    fun push(key: YDStyleNavKey) = backStack.add(element = key)

    fun pop() = backStack.removeAt(index = backStack.lastIndex)

    @Composable
    fun YDStyleGuideNavState.entries(
        onProvideEntry: (YDStyleNavKey) -> NavEntry<YDStyleNavKey>,
    ) = rememberDecoratedNavEntries(
        backStack = backStack,
        entryProvider = onProvideEntry
    ).toMutableList()
}
