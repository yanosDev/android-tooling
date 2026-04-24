@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home.viewmodel

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModelImpl
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.styleguide.theme.components.organisms.screen.model.toUIContent
import de.yanosdev.tooling.ui.home.model.HomeAction
import de.yanosdev.tooling.ui.home.model.HomeScreenData

internal class HomeViewModelImpl : YDViewModelImpl<UIState<HomeScreenData>, HomeAction>(
    defaultState = HomeScreenData().toUIContent()
), HomeViewModel {
    override fun onAction(action: HomeAction) {
        when (action) {
            else -> super.onAction(action)
        }
    }
}