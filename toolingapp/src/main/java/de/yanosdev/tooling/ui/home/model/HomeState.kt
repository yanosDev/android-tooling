package de.yanosdev.tooling.ui.home.model


internal sealed interface HomeState {
    data object Loading : HomeState
    data class Error(val failure: Exception) : HomeState
    data class Content(val data: Unit) : HomeState
}