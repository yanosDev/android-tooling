@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.card

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModel
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.tooling.ui.card.model.CardAction
import de.yanosdev.tooling.ui.card.model.CardScreenData

internal interface CardViewModel : YDViewModel<UIState<CardScreenData>, CardAction>
