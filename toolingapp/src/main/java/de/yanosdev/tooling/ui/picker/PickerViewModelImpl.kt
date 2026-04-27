@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.picker

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModelImpl
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.styleguide.theme.components.organisms.screen.model.toUIContent
import de.yanosdev.tooling.ui.picker.model.PickerAction
import de.yanosdev.tooling.ui.picker.model.PickerScreenData

internal class PickerViewModelImpl : YDViewModelImpl<UIState<PickerScreenData>, PickerAction>(
    defaultState = PickerScreenData().toUIContent()
), PickerViewModel {
    override fun onAction(action: PickerAction) {
        when (action) {
            else -> super.onAction(action = action)
        }
    }
}
