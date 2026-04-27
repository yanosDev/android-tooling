@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.slider

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModel
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.tooling.ui.slider.model.SliderAction
import de.yanosdev.tooling.ui.slider.model.SliderScreenData

internal interface SliderViewModel : YDViewModel<UIState<SliderScreenData>, SliderAction>
