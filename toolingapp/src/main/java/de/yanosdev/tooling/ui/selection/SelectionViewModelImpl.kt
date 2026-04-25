@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.selection

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModelImpl
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.styleguide.theme.components.organisms.screen.model.toUIContent
import de.yanosdev.tooling.ui.selection.model.SelectionAction
import de.yanosdev.tooling.ui.selection.model.SelectionScreenData

internal class SelectionViewModelImpl : YDViewModelImpl<UIState<SelectionScreenData>, SelectionAction>(
    defaultState = SelectionScreenData().toUIContent()
), SelectionViewModel {
    override fun onAction(action: SelectionAction) {
        when (action) {
            else -> super.onAction(action = action)
        }
    }
}
