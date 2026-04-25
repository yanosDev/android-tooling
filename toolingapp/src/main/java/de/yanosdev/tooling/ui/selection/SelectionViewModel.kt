@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.selection

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModel
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.tooling.ui.selection.model.SelectionAction
import de.yanosdev.tooling.ui.selection.model.SelectionScreenData

internal interface SelectionViewModel : YDViewModel<UIState<SelectionScreenData>, SelectionAction>
