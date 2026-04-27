@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home

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
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.core.util.findActivity
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcon
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcons
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.molecules.navigationbar.YDNavigationBar
import de.yanosdev.styleguide.theme.components.molecules.navigationbar.YDNavigationBarItem
import de.yanosdev.styleguide.theme.components.molecules.scaffold.YDScaffold
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContent
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.YDStatusBarColorManager
import de.yanosdev.tooling.navigation.YDStyleGuideNavKey
import de.yanosdev.tooling.ui.home.content.CompactContent
import de.yanosdev.tooling.ui.home.content.ExpandedContent
import de.yanosdev.tooling.ui.home.content.MediumContent
import de.yanosdev.tooling.ui.home.model.HomeAction
import de.yanosdev.tooling.ui.home.model.HomeSection
import de.yanosdev.tooling.ui.home.model.StyleGuideItems
import de.yanosdev.tooling.ui.home.viewmodel.HomeViewModel

internal fun HomeSection.icon(): ImageVector = when (this) {
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
                        StyleGuideItems.SubAtoms.Typographies -> YDStyleGuideNavKey.Typographies
                        StyleGuideItems.Atoms.Icon -> YDStyleGuideNavKey.Icon
                        StyleGuideItems.Atoms.Selection -> YDStyleGuideNavKey.Selection
                        StyleGuideItems.Atoms.Slider -> YDStyleGuideNavKey.Slider
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
