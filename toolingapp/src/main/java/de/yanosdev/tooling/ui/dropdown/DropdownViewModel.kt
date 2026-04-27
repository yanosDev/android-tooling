@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.dropdown

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModel
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.tooling.ui.dropdown.model.DropdownAction
import de.yanosdev.tooling.ui.dropdown.model.DropdownScreenData

internal interface DropdownViewModel : YDViewModel<UIState<DropdownScreenData>, DropdownAction>
