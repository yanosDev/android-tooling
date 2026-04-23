@file:YDRevisionIn(implementedAt = "2026-04-23", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.typographies

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.core.component.screen.YDViewModelImpl
import de.yanosdev.core.component.screen.model.UIState
import de.yanosdev.core.component.screen.model.toUIContent
import de.yanosdev.tooling.ui.typographies.model.TypographiesAction
import de.yanosdev.tooling.ui.typographies.model.TypographiesScreenData

internal class TypographiesViewModelImpl : YDViewModelImpl<UIState<TypographiesScreenData>, TypographiesAction>(
    defaultState = TypographiesScreenData().toUIContent()
), TypographiesViewModel {
    override fun onAction(action: TypographiesAction) {
        when (action) {
            else -> super.onAction(action)
        }
    }
}