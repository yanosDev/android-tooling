@file:YDRevisionIn(implementedAt = "2026-04-18", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.scaffold.YDScaffold
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview
import de.yanosdev.styleguide.theme.util.YDStatusBarColorManager
import de.yanosdev.tooling.ui.home.model.HomeAction
import de.yanosdev.tooling.ui.home.model.HomeHeaderSectionData
import de.yanosdev.tooling.ui.home.model.HomeState
import de.yanosdev.tooling.ui.home.section.HomeBodySection
import de.yanosdev.tooling.ui.home.section.HomeHeaderSection
import de.yanosdev.tooling.ui.home.viewmodel.HomeViewModel

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel,
    navBack: @Composable () -> Unit,
    modifier: Modifier = Modifier
) = YDScaffold(modifier = modifier) { contentPadding ->
    YDStatusBarColorManager(statusBarColor = colorScheme.primary)
    val state by viewModel.state.collectAsStateWithLifecycle()
    Content(
        modifier = modifier,
        state = state,
        contentPadding = contentPadding,
        onAction = { action -> },
    )
}

@Composable
private fun Content(
    contentPadding: PaddingValues,
    state: HomeState,
    onAction: (HomeAction) -> Unit,
    modifier: Modifier = Modifier,
) = Column(modifier = modifier) {
    HomeHeaderSection(
        modifier = modifier
            .padding(
                top = contentPadding.calculateTopPadding()
            ),
        data = HomeHeaderSectionData(),
        onHomeHeaderAction = { },
    )
    HomeBodySection()
}

@PhonePreview
@Composable
private fun Preview() = YDPreview {
    Content(
        state = HomeState.Loading,
        contentPadding = PaddingValues(),
        onAction = {}
    )
}