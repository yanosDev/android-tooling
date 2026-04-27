@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.dropdown

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModelImpl
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.styleguide.theme.components.organisms.screen.model.toUIContent
import de.yanosdev.tooling.ui.dropdown.model.DropdownAction
import de.yanosdev.tooling.ui.dropdown.model.DropdownScreenData

internal class DropdownViewModelImpl : YDViewModelImpl<UIState<DropdownScreenData>, DropdownAction>(
    defaultState = DropdownScreenData().toUIContent()
), DropdownViewModel {
    override fun onAction(action: DropdownAction) {
        when (action) {
            else -> super.onAction(action = action)
        }
    }
}
