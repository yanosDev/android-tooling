@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.chip

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModelImpl
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.styleguide.theme.components.organisms.screen.model.toUIContent
import de.yanosdev.tooling.ui.chip.model.ChipAction
import de.yanosdev.tooling.ui.chip.model.ChipScreenData

internal class ChipViewModelImpl : YDViewModelImpl<UIState<ChipScreenData>, ChipAction>(
    defaultState = ChipScreenData().toUIContent()
), ChipViewModel {
    override fun onAction(action: ChipAction) {
        when (action) {
            else -> super.onAction(action = action)
        }
    }
}
