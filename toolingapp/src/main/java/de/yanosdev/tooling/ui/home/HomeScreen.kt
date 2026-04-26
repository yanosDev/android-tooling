@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.core.util.findActivity
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcon
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcons
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.molecules.navigationbar.YDNavigationBar
import de.yanosdev.styleguide.theme.components.molecules.navigationbar.YDNavigationBarItem
import de.yanosdev.styleguide.theme.components.molecules.navigationdrawer.YDNavigationDrawer
import de.yanosdev.styleguide.theme.components.molecules.navigationdrawer.YDNavigationDrawerItem
import de.yanosdev.styleguide.theme.components.molecules.navigationrail.YDNavigationRail
import de.yanosdev.styleguide.theme.components.molecules.navigationrail.YDNavigationRailItem
import de.yanosdev.styleguide.theme.components.molecules.scaffold.YDScaffold
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContent
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.styleguide.theme.util.YDStatusBarColorManager
import de.yanosdev.tooling.navigation.YDStyleGuideNavKey
import de.yanosdev.tooling.ui.home.model.HomeAction
import de.yanosdev.tooling.ui.home.model.HomeScreenData
import de.yanosdev.tooling.ui.home.model.HomeSection
import de.yanosdev.tooling.ui.home.model.StyleGuideItems
import de.yanosdev.tooling.ui.home.section.HomeBodySection
import de.yanosdev.tooling.ui.home.section.HomeHeaderSection
import de.yanosdev.tooling.ui.home.viewmodel.HomeViewModel

private fun HomeSection.icon(): ImageVector = when (this) {
    HomeSection.SubAtoms -> YDIcons.Bolt
    HomeSection.Atoms -> YDIcons.Code
    HomeSection.Molecules -> YDIcons.Layers
}

@Composable
internal fun HomeScreen(
    navBack: @Composable () -> Unit,
    navToItem: (YDStyleGuideNavKey) -> Unit,
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
) {
    var selectedSection by remember { mutableStateOf(value = HomeSection.SubAtoms) }
    val widthSizeClass = calculateWindowSizeClass(LocalContext.current.findActivity()).widthSizeClass

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
                        StyleGuideItems.Atoms.Selection -> YDStyleGuideNavKey.Selection
                        StyleGuideItems.Atoms.Slider -> YDStyleGuideNavKey.Slider
                        StyleGuideItems.Atoms.Surface -> YDStyleGuideNavKey.Surface
                        StyleGuideItems.Atoms.Scaffold -> YDStyleGuideNavKey.Scaffold
                        StyleGuideItems.Molecules.Button -> YDStyleGuideNavKey.Button
                        StyleGuideItems.Molecules.Card -> YDStyleGuideNavKey.Card
                        StyleGuideItems.Molecules.Chip -> YDStyleGuideNavKey.Chip
                        StyleGuideItems.Molecules.Dialog -> YDStyleGuideNavKey.Dialog
                        StyleGuideItems.Molecules.Dropdown -> YDStyleGuideNavKey.Dropdown
                        StyleGuideItems.Molecules.Fab -> YDStyleGuideNavKey.Fab
                        StyleGuideItems.Molecules.Picker -> YDStyleGuideNavKey.Picker
                        StyleGuideItems.Molecules.SearchBar -> YDStyleGuideNavKey.SearchBar
                        StyleGuideItems.Molecules.Snackbar -> YDStyleGuideNavKey.Snackbar
                    }
                )

                else -> {}
            }
        }
    }

    when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> YDScaffold(
            modifier = modifier,
            bottomBar = {
                YDNavigationBar {
                    HomeSection.entries.forEach { section ->
                        YDNavigationBarItem(
                            selected = selectedSection == section,
                            onClick = { selectedSection = section },
                            label = { YDText(text = section.label, style = typography.xsRegular) },
                        ) {
                            YDIcon(imageVector = section.icon(), contentDescription = section.label)
                        }
                    }
                }
            },
        ) { contentPadding ->
            YDUIContent(viewModel = viewModel) {
                CompactContent(contentPadding = contentPadding, selectedSection = selectedSection)
            }
        }

        WindowWidthSizeClass.Medium -> YDScaffold(modifier = modifier) { contentPadding ->
            YDUIContent(viewModel = viewModel) {
                MediumContent(
                    contentPadding = contentPadding,
                    selectedSection = selectedSection,
                    onSectionChange = { selectedSection = it },
                )
            }
        }

        else -> YDScaffold(modifier = modifier) { contentPadding ->
            YDUIContent(viewModel = viewModel) {
                ExpandedContent(
                    contentPadding = contentPadding,
                    selectedSection = selectedSection,
                    onSectionChange = { selectedSection = it },
                )
            }
        }
    }
}

@Composable
private fun YDUIContentScope<HomeScreenData, HomeAction>.CompactContent(
    contentPadding: PaddingValues,
    selectedSection: HomeSection,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        HomeHeaderSection(modifier = Modifier.padding(top = contentPadding.calculateTopPadding()))
        HomeBodySection(selectedSection = selectedSection)
    }
}

@Composable
private fun YDUIContentScope<HomeScreenData, HomeAction>.MediumContent(
    contentPadding: PaddingValues,
    selectedSection: HomeSection,
    onSectionChange: (HomeSection) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.fillMaxSize()) {
        YDNavigationRail(windowInsets = WindowInsets(left = 0.dp, top = 0.dp, right = 0.dp, bottom = 0.dp)) {
            HomeSection.entries.forEach { section ->
                YDNavigationRailItem(
                    selected = selectedSection == section,
                    onClick = { onSectionChange(section) },
                    label = { YDText(text = section.label, style = typography.xsRegular) },
                ) {
                    YDIcon(imageVector = section.icon(), contentDescription = section.label)
                }
            }
        }
        Column(
            modifier = Modifier
                .weight(weight = 1f)
                .padding(top = contentPadding.calculateTopPadding()),
        ) {
            HomeHeaderSection()
            HomeBodySection(selectedSection = selectedSection)
        }
    }
}

@Composable
private fun YDUIContentScope<HomeScreenData, HomeAction>.ExpandedContent(
    contentPadding: PaddingValues,
    selectedSection: HomeSection,
    onSectionChange: (HomeSection) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(top = contentPadding.calculateTopPadding()),
    ) {
        YDNavigationDrawer(
            windowInsets = WindowInsets(left = 0.dp, top = 0.dp, right = 0.dp, bottom = 0.dp),
            header = { HomeHeaderSection() },
        ) {
            HomeSection.entries.forEach { section ->
                YDNavigationDrawerItem(
                    label = section.label,
                    selected = selectedSection == section,
                    onClick = { onSectionChange(section) },
                    icon = { YDIcon(imageVector = section.icon(), contentDescription = section.label) },
                )
            }
        }
        HomeBodySection(
            selectedSection = selectedSection,
            modifier = Modifier.weight(weight = 1f),
        )
    }
}

@PhonePreview
@Composable
private fun CompactPreview() = YDContentPreview(data = HomeScreenData()) {
    CompactContent(contentPadding = PaddingValues(), selectedSection = HomeSection.Atoms)
}
