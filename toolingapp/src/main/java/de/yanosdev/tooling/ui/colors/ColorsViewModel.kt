@file:YDRevisionIn(implementedAt = "2026-04-23", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.colors

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModelImpl
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.styleguide.theme.components.organisms.screen.model.toUIContent
import de.yanosdev.tooling.ui.colors.model.ColorsAction
import de.yanosdev.tooling.ui.colors.model.ColorsScreenData

internal class ColorsViewModelImpl : YDViewModelImpl<UIState<ColorsScreenData>, ColorsAction>(
    defaultState = ColorsScreenData().toUIContent()
), ColorsViewModel {
    override fun onAction(action: ColorsAction) {
        when (action) {
            else -> super.onAction(action)
        }
    }
}