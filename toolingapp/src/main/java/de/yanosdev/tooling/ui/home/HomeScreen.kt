@file:YDRevisionIn(implementedAt = "2026-04-18", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.YDScaffold
import de.yanosdev.styleguide.theme.util.core.ScreenWithViewModel
import de.yanosdev.tooling.ui.home.model.HomeAction
import de.yanosdev.tooling.ui.home.model.HomeState
import de.yanosdev.tooling.ui.home.viewmodel.HomeViewModel
import de.yanosdev.tooling.ui.home.viewmodel.HomeViewModelImpl

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel,
    navBack: @Composable () -> Unit,
    modifier: Modifier = Modifier
) = ScreenWithViewModel<HomeViewModelImpl, HomeViewModel>(
    viewModel = viewModel
) { viewModel ->
    val state by viewModel.state.collectAsStateWithLifecycle()
    Content(
        state = state,
        onAction = { action -> },
    )
}

@Composable
private fun Content(
    state: HomeState,
    onAction: (HomeAction) -> Unit,
    modifier: Modifier = Modifier,
) = YDScaffold { contentPadding ->

}

@Preview
@Composable
private fun Preview() {
    Content(
        state = HomeState.Loading,
        onAction = {}
    )
}