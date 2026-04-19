@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home.model

import de.yanosdev.annotation.YDRevisionIn


sealed interface HomeState {
    data object Loading : HomeState
    data class Error(val failure: Exception) : HomeState
    data class Content(val data: Unit) : HomeState
}