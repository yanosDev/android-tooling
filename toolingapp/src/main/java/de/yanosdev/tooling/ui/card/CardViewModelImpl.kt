@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.card

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModelImpl
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.styleguide.theme.components.organisms.screen.model.toUIContent
import de.yanosdev.tooling.ui.card.model.CardAction
import de.yanosdev.tooling.ui.card.model.CardScreenData

internal class CardViewModelImpl : YDViewModelImpl<UIState<CardScreenData>, CardAction>(
    defaultState = CardScreenData().toUIContent()
), CardViewModel {
    override fun onAction(action: CardAction) {
        when (action) {
            else -> super.onAction(action = action)
        }
    }
}
