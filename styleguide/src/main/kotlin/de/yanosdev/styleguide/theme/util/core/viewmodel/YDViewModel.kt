@file:YDRevisionIn(implementedAt = "2026-04-21", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.util.core.viewmodel

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.util.core.model.NavAction
import kotlinx.coroutines.flow.StateFlow

interface YDViewModel<T, Z> {
    val navEvents: YDViewModelEvents<NavAction>
    val state: StateFlow<T>

    fun onAction(action: Z)

    fun onNavAction(action: NavAction)
}