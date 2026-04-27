@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.slider

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModelImpl
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.styleguide.theme.components.organisms.screen.model.toUIContent
import de.yanosdev.tooling.ui.slider.model.SliderAction
import de.yanosdev.tooling.ui.slider.model.SliderScreenData

internal class SliderViewModelImpl : YDViewModelImpl<UIState<SliderScreenData>, SliderAction>(
    defaultState = SliderScreenData().toUIContent()
), SliderViewModel {
    override fun onAction(action: SliderAction) {
        when (action) {
            else -> super.onAction(action = action)
        }
    }
}
