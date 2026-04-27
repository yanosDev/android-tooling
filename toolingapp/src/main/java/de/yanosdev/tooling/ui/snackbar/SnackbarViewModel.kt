@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.snackbar

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModel
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.tooling.ui.snackbar.model.SnackbarAction
import de.yanosdev.tooling.ui.snackbar.model.SnackbarScreenData

internal interface SnackbarViewModel : YDViewModel<UIState<SnackbarScreenData>, SnackbarAction>
