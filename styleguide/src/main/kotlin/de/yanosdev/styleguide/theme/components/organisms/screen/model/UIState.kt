@file:YDRevisionIn(implementedAt = "2026-04-21", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.organisms.screen.model

import de.yanosdev.annotation.YDRevisionIn

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

fun <T> T.toUIContent() = UIState.Content(data = this)