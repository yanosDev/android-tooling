@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.searchbar

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModel
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.tooling.ui.searchbar.model.SearchBarAction
import de.yanosdev.tooling.ui.searchbar.model.SearchBarScreenData

internal interface SearchBarViewModel : YDViewModel<UIState<SearchBarScreenData>, SearchBarAction>
