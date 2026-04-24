@file:YDRevisionIn(implementedAt = "2026-04-23", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.typographies

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModelImpl
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.styleguide.theme.components.organisms.screen.model.toUIContent
import de.yanosdev.tooling.ui.typographies.model.TypographiesAction
import de.yanosdev.tooling.ui.typographies.model.TypographiesScreenData

internal class TypographiesViewModelImpl : YDViewModelImpl<UIState<TypographiesScreenData>, TypographiesAction>(
    defaultState = TypographiesScreenData().toUIContent()
), TypographiesViewModel {
    override fun onAction(action: TypographiesAction) {
        when (action) {
            else -> super.onAction(action = action)
        }
    }
}