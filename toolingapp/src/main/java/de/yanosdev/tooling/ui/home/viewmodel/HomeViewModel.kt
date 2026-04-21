@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home.viewmodel

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.util.core.model.UIState
import de.yanosdev.styleguide.theme.util.core.viewmodel.YDViewModel
import de.yanosdev.tooling.ui.home.model.HomeAction
import de.yanosdev.tooling.ui.home.model.HomeScreenData

internal interface HomeViewModel : YDViewModel<UIState<HomeScreenData>, HomeAction>