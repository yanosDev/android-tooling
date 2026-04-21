@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home.model

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.util.core.model.NavAction

internal sealed interface HomeBodyAction : HomeAction {
    data object NavToText : HomeBodyAction, NavAction
}