@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.searchbar

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModelImpl
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.styleguide.theme.components.organisms.screen.model.toUIContent
import de.yanosdev.tooling.ui.searchbar.model.SearchBarAction
import de.yanosdev.tooling.ui.searchbar.model.SearchBarScreenData

internal class SearchBarViewModelImpl : YDViewModelImpl<UIState<SearchBarScreenData>, SearchBarAction>(
    defaultState = SearchBarScreenData().toUIContent()
), SearchBarViewModel {
    override fun onAction(action: SearchBarAction) {
        when (action) {
            else -> super.onAction(action = action)
        }
    }
}
