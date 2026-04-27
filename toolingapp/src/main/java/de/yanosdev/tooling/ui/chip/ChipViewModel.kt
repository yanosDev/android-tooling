@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.chip

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModel
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.tooling.ui.chip.model.ChipAction
import de.yanosdev.tooling.ui.chip.model.ChipScreenData

internal interface ChipViewModel : YDViewModel<UIState<ChipScreenData>, ChipAction>
