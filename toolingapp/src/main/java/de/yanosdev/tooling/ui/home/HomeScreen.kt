@file:YDRevisionIn(implementedAt = "2026-04-18", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.scaffold.YDScaffold
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.styleguide.theme.util.YDStatusBarColorManager
import de.yanosdev.styleguide.theme.util.core.YDScreen
import de.yanosdev.styleguide.theme.util.core.viewmodel.YDUIContentScope
import de.yanosdev.tooling.ui.home.model.HomeAction
import de.yanosdev.tooling.ui.home.model.HomeScreenData
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

    LaunchedEffect(viewModel.navEvents) {
        viewModel.navEvents.collect { navAction ->
            when (navAction) {
                else -> {}
            }
        }
    }

    YDScreen(viewModel = viewModel) {
        Content(
            contentPadding = contentPadding,
        )
    }
}

@Composable
internal fun YDUIContentScope<HomeScreenData, HomeAction>.Content(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) = Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(spacings.medium)) {
    HomeHeaderSection(modifier = modifier.padding(top = contentPadding.calculateTopPadding()))
    HomeBodySection()
}

@PhonePreview
@Composable
private fun Preview() = YDContentPreview(data = HomeScreenData()) {
    Content(
        contentPadding = PaddingValues(),
    )
}