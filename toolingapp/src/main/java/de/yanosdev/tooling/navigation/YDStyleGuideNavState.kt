@file:YDRevisionIn(implementedAt = "2026-04-17", revisionAfterInDays = 365)

package de.yanosdev.tooling.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.serialization.NavBackStackSerializer
import androidx.navigation3.runtime.serialization.NavKeySerializer
import de.yanosdev.annotation.YDRevisionIn

internal class YDStyleGuideNavState(private val backStack: NavBackStack<YDStyleGuideNavKey>) {
    val backStackSize by backStack::size

    fun push(key: YDStyleGuideNavKey) = backStack.add(element = key)

    fun pop() = backStack.removeAt(index = backStack.lastIndex)

    @Composable
    fun entries(
        onProvideEntry: (YDStyleGuideNavKey) -> NavEntry<YDStyleGuideNavKey>,
    ) = rememberDecoratedNavEntries(
        backStack = backStack,
        entryProvider = onProvideEntry
    ).toMutableList()
}

@Composable
internal fun rememberYDStyleGuideNavState(start: YDStyleGuideNavKey): YDStyleGuideNavState {
    val backStack = rememberSerializable(
        serializer = NavBackStackSerializer(elementSerializer = NavKeySerializer())
    ) {
        NavBackStack(start)
    }

    return remember { YDStyleGuideNavState(backStack = backStack) }
}
