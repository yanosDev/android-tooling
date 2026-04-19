@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home.viewmodel

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.tooling.ui.home.model.HomeAction
import de.yanosdev.tooling.ui.home.model.HomeState
import kotlinx.coroutines.flow.StateFlow


internal interface HomeViewModel {
    val state: StateFlow<HomeState>

    fun onHomeAction(action: HomeAction)
}