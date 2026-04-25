@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.snackbar

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModelImpl
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.styleguide.theme.components.organisms.screen.model.toUIContent
import de.yanosdev.tooling.ui.snackbar.model.SnackbarAction
import de.yanosdev.tooling.ui.snackbar.model.SnackbarScreenData

internal class SnackbarViewModelImpl : YDViewModelImpl<UIState<SnackbarScreenData>, SnackbarAction>(
    defaultState = SnackbarScreenData().toUIContent()
), SnackbarViewModel {
    override fun onAction(action: SnackbarAction) {
        when (action) {
            else -> super.onAction(action = action)
        }
    }
}
