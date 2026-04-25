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
import de.yanosdev.styleguide.theme.components.molecules.scaffold.YDScaffold
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContent
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.styleguide.theme.util.YDStatusBarColorManager
import de.yanosdev.tooling.navigation.YDStyleGuideNavKey
import de.yanosdev.tooling.ui.home.model.HomeAction
import de.yanosdev.tooling.ui.home.model.HomeScreenData
import de.yanosdev.tooling.ui.home.model.StyleGuideItems
import de.yanosdev.tooling.ui.home.section.HomeBodySection
import de.yanosdev.tooling.ui.home.section.HomeHeaderSection
import de.yanosdev.tooling.ui.home.viewmodel.HomeViewModel

@Composable
internal fun HomeScreen(
    navBack: @Composable () -> Unit,
    navToItem: (YDStyleGuideNavKey) -> Unit,
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) = YDScaffold(modifier = modifier) { contentPadding ->
    YDStatusBarColorManager(statusBarColor = colorScheme.primary)

    LaunchedEffect(viewModel.navEvents) {
        viewModel.navEvents.collect { navAction ->
            when (navAction) {
                is HomeAction.NavToStyleItem -> navToItem(
                    when (navAction.item) {
                        StyleGuideItems.SubAtoms.Colors -> YDStyleGuideNavKey.Colors
                        StyleGuideItems.Atoms.Text -> YDStyleGuideNavKey.Text
                        StyleGuideItems.SubAtoms.Typographies -> YDStyleGuideNavKey.Typographies
                        StyleGuideItems.Atoms.Icon -> YDStyleGuideNavKey.Icon
                        StyleGuideItems.Atoms.IconButton -> YDStyleGuideNavKey.IconButton
                        StyleGuideItems.Atoms.Surface -> YDStyleGuideNavKey.Surface
                        StyleGuideItems.Atoms.Scaffold -> YDStyleGuideNavKey.Scaffold
                        StyleGuideItems.Molecules.Button -> YDStyleGuideNavKey.Button
                        StyleGuideItems.Molecules.Card -> YDStyleGuideNavKey.Card
                        StyleGuideItems.Molecules.Dialog -> YDStyleGuideNavKey.Dialog
                        StyleGuideItems.Molecules.Picker -> YDStyleGuideNavKey.Picker
                    }
                )

                else -> {}
            }
        }
    }

    YDUIContent(viewModel = viewModel) {
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