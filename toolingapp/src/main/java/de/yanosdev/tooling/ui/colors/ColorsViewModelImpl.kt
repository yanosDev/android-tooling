@file:YDRevisionIn(implementedAt = "2026-04-23", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.colors

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.core.component.screen.YDViewModel
import de.yanosdev.core.component.screen.model.UIState
import de.yanosdev.tooling.ui.colors.model.ColorsAction
import de.yanosdev.tooling.ui.colors.model.ColorsScreenData

internal interface ColorsViewModel : YDViewModel<UIState<ColorsScreenData>, ColorsAction>