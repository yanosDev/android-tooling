@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.button

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.core.component.screen.YDViewModel
import de.yanosdev.core.component.screen.model.UIState
import de.yanosdev.tooling.ui.button.model.ButtonAction
import de.yanosdev.tooling.ui.button.model.ButtonScreenData

internal interface ButtonViewModel : YDViewModel<UIState<ButtonScreenData>, ButtonAction>