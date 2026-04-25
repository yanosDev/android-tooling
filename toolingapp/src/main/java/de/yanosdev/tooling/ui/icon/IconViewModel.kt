@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.icon

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModel
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.tooling.ui.icon.model.IconAction
import de.yanosdev.tooling.ui.icon.model.IconScreenData

internal interface IconViewModel : YDViewModel<UIState<IconScreenData>, IconAction>
