@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.icon

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModelImpl
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.styleguide.theme.components.organisms.screen.model.toUIContent
import de.yanosdev.tooling.ui.icon.model.IconAction
import de.yanosdev.tooling.ui.icon.model.IconScreenData

internal class IconViewModelImpl : YDViewModelImpl<UIState<IconScreenData>, IconAction>(
    defaultState = IconScreenData().toUIContent()
), IconViewModel {
    override fun onAction(action: IconAction) {
        when (action) {
            else -> super.onAction(action = action)
        }
    }
}
