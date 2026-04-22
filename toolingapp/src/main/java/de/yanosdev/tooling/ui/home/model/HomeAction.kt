@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home.model

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.core.navigation.model.NavAction

internal sealed interface HomeAction {
    data class NavToStyleItem(val item: StyleGuideItems) : HomeAction, NavAction
}