@file:YDRevisionIn(implementedAt = "2026-04-21", revisionAfterInDays = 365)

package de.yanosdev.core.component.screen

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.core.navigation.model.NavAction
import kotlinx.coroutines.flow.StateFlow

interface YDViewModel<T, Z> {
    val navEvents: YDViewModelEvents<NavAction>
    val state: StateFlow<T>

    fun onAction(action: Z)

    fun onNavAction(action: NavAction)
}