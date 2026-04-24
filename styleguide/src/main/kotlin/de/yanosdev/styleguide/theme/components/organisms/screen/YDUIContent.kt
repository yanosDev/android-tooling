@file:YDRevisionIn(implementedAt = "2026-04-18", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.organisms.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.core.navigation.model.NavAction
import de.yanosdev.styleguide.theme.components.organisms.screen.model.UIState
import de.yanosdev.styleguide.theme.util.YDAnimatedContent

@Composable
fun <T, Z> YDUIContent(
    viewModel: YDViewModel<UIState<T>, Z>,
    content: @Composable (YDUIContentScope<T, Z>.() -> Unit)
) {
    YDAnimatedContent(targetState = viewModel.state.collectAsStateWithLifecycle().value) { targetState ->
        when (targetState) {
            is UIState.Content -> {
                rememberYDScreenScope(
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
fun <T, Z> rememberYDScreenScope(
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