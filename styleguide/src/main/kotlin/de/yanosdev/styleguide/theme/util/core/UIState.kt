package de.yanosdev.styleguide.theme.util.core

interface UIState<out T> {
    data class Content<T>(
        val data: T,
        val isRefreshing: Boolean = false,
    ) : UIState<T>


    data class Error(
        val failure: String
    ) : UIState<Nothing>

    data object Loading : UIState<Nothing>
}