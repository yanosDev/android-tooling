@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.dialog

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModel
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.tooling.ui.dialog.model.DialogAction
import de.yanosdev.tooling.ui.dialog.model.DialogScreenData

internal interface DialogViewModel : YDViewModel<UIState<DialogScreenData>, DialogAction>
