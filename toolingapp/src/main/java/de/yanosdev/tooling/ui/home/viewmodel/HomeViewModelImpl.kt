@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.tooling.ui.home.model.HomeAction
import de.yanosdev.tooling.ui.home.model.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class HomeViewModelImpl : ViewModel(), HomeViewModel {
    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    override val state = _state.asStateFlow()

    override fun onHomeAction(action: HomeAction) {

    }
}