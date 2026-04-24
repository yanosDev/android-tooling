@file:YDRevisionIn(implementedAt = "2026-04-23", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.typographies

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModel
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.tooling.ui.typographies.model.TypographiesAction
import de.yanosdev.tooling.ui.typographies.model.TypographiesScreenData

internal interface TypographiesViewModel : YDViewModel<UIState<TypographiesScreenData>, TypographiesAction>