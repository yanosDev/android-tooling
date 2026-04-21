@file:YDRevisionIn(implementedAt = "2026-04-18", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.util.core

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.util.core.model.NavAction
import de.yanosdev.styleguide.theme.util.core.model.UIState
import de.yanosdev.styleguide.theme.util.core.viewmodel.YDUIContentScope
import de.yanosdev.styleguide.theme.util.core.viewmodel.YDUIContentScopeImpl
import de.yanosdev.styleguide.theme.util.core.viewmodel.YDViewModel

@Composable
fun <T, Z> ScreenWithViewModelScope(
    viewModel: YDViewModel<UIState<T>, Z>,
    content: @Composable (YDUIContentScope<T, Z>.() -> Unit)
) {
    AnimatedContent(targetState = viewModel.state.collectAsStateWithLifecycle().value) { targetState ->
        when (targetState) {
            is UIState.Content -> {
                rememberYDViewModelScope(
                    data = targetState.data,
                    onAction = viewModel::onAction,
                    onNavAction = viewModel::onNavAction
                ).content()
            }

            else -> Unit
        }
    }
}

@Composable
fun <T, Z> rememberYDViewModelScope(
    data: T,
    onAction: (Z) -> Unit = {},
    onNavAction: (NavAction) -> Unit = {}
): YDUIContentScope<T, Z> = remember(data) {
    YDUIContentScopeImpl(
        data = data,
        onNavAction = onNavAction,
        onAction = onAction
    )
}