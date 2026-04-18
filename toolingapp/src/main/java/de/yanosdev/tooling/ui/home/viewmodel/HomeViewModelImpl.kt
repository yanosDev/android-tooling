package de.yanosdev.tooling.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import de.yanosdev.tooling.ui.home.model.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class HomeViewModelImpl : ViewModel(), HomeViewModel {
    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state = _state.asStateFlow()
}