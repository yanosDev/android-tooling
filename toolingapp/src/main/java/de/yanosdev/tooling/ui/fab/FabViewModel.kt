@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.fab

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModel
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.tooling.ui.fab.model.FabAction
import de.yanosdev.tooling.ui.fab.model.FabScreenData

internal interface FabViewModel : YDViewModel<UIState<FabScreenData>, FabAction>
