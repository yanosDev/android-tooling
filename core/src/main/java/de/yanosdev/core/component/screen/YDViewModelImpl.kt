@file:YDRevisionIn(implementedAt = "2026-04-20", revisionAfterInDays = 365)

package de.yanosdev.core.component.screen

import androidx.lifecycle.ViewModel
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.core.navigation.model.NavAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

open class YDViewModelImpl<T, Z>(defaultState: T) : ViewModel(), YDViewModel<T, Z> {
    private val _state = MutableStateFlow(defaultState)
    override val state = _state.asStateFlow()
    private val _navEvents = ydMutableEvents<NavAction>()
    override val navEvents = _navEvents.asImmutable()

    override fun onAction(action: Z) {
        when (action) {
            is NavAction -> onNavAction(action)
        }
    }

    override fun onNavAction(action: NavAction) = _navEvents.send(action)
}
