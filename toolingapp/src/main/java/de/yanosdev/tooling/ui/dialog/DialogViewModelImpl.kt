@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.dialog

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModelImpl
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.styleguide.theme.components.organisms.screen.model.toUIContent
import de.yanosdev.tooling.ui.dialog.model.DialogAction
import de.yanosdev.tooling.ui.dialog.model.DialogScreenData

internal class DialogViewModelImpl : YDViewModelImpl<UIState<DialogScreenData>, DialogAction>(
    defaultState = DialogScreenData().toUIContent()
), DialogViewModel {
    override fun onAction(action: DialogAction) {
        when (action) {
            else -> super.onAction(action = action)
        }
    }
}
