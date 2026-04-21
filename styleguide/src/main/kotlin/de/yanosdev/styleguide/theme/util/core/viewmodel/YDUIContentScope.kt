@file:YDRevisionIn(implementedAt = "2026-04-21", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.util.core.viewmodel

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.util.core.model.NavAction

interface YDUIContentScope<T, Z> {

    val data: T

    fun onScreenAction(action: Z)
}

class YDUIContentScopeImpl<T, Z>(
    override val data: T,
    val onNavAction: (NavAction) -> Unit,
    val onAction: (Z) -> Unit
) : YDUIContentScope<T, Z> {
    override fun onScreenAction(action: Z) {
        when (action) {
            is NavAction -> onNavAction(action)
            else -> onAction(action)
        }
    }
}