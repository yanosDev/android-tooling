@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.fab

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDViewModelImpl
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.styleguide.theme.components.organisms.screen.model.toUIContent
import de.yanosdev.tooling.ui.fab.model.FabAction
import de.yanosdev.tooling.ui.fab.model.FabScreenData

internal class FabViewModelImpl : YDViewModelImpl<UIState<FabScreenData>, FabAction>(
    defaultState = FabScreenData().toUIContent()
), FabViewModel {
    override fun onAction(action: FabAction) {
        when (action) {
            else -> super.onAction(action = action)
        }
    }
}
