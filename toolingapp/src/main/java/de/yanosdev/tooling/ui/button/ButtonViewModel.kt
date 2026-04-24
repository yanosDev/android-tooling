@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.button

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModelImpl
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.styleguide.theme.components.organisms.screen.model.toUIContent
import de.yanosdev.tooling.ui.button.model.ButtonAction
import de.yanosdev.tooling.ui.button.model.ButtonScreenData

internal class ButtonViewModelImpl : YDViewModelImpl<UIState<ButtonScreenData>, ButtonAction>(
    defaultState = ButtonScreenData().toUIContent()
), ButtonViewModel {
    override fun onAction(action: ButtonAction) {
        when (action) {
            else -> super.onAction(action = action)
        }
    }
}