@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.fab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.divider.YDDivider
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcon
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcons
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.molecules.fab.YDExtendedFab
import de.yanosdev.styleguide.theme.components.molecules.fab.YDFab
import de.yanosdev.styleguide.theme.components.molecules.fab.YDSmallFab
import de.yanosdev.styleguide.theme.components.molecules.scaffold.YDScaffold
import de.yanosdev.styleguide.theme.components.molecules.topbar.YDTopAppBar
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContent
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.tooling.ui.fab.model.FabAction
import de.yanosdev.tooling.ui.fab.model.FabScreenData

@Composable
internal fun FabScreen(
    navBack: @Composable () -> Unit,
    viewModel: FabViewModel,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()
    val fabExpanded by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }

    LaunchedEffect(viewModel.navEvents) {
        viewModel.navEvents.collect { navAction ->
            when (navAction) {
                else -> {}
            }
        }
    }

    YDScaffold(
        modifier = modifier,
        topBar = {
            YDTopAppBar(
                title = "Floating Action Buttons",
                navigationIcon = navBack,
            )
        },
        floatingActionButton = {
            YDExtendedFab(
                text = "Create",
                onClick = {},
                expanded = fabExpanded,
                icon = { YDIcon(imageVector = YDIcons.Add, contentDescription = null) },
            )
        },
    ) { contentPadding ->
        YDUIContent(viewModel = viewModel) {
            Content(contentPadding = contentPadding, listState = listState)
        }
    }
}

@Composable
internal fun YDUIContentScope<FabScreenData, FabAction>.Content(
    contentPadding: PaddingValues,
    listState: androidx.compose.foundation.lazy.LazyListState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        state = listState,
        contentPadding = contentPadding,
    ) {
        item {
            YDText(
                text = "Standard",
                style = typography.smRegular,
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
            )
            Row(
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
                horizontalArrangement = Arrangement.spacedBy(space = spacings.medium),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                YDFab(onClick = {}) {
                    YDIcon(imageVector = YDIcons.Add, contentDescription = null)
                }
                YDFab(onClick = {}) {
                    YDIcon(imageVector = YDIcons.Edit, contentDescription = null)
                }
                YDFab(onClick = {}, enabled = false) {
                    YDIcon(imageVector = YDIcons.Add, contentDescription = null)
                }
            }
        }
        item { YDDivider(modifier = Modifier.padding(vertical = spacings.small)) }
        item {
            YDText(
                text = "Small",
                style = typography.smRegular,
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
            )
            Row(
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
                horizontalArrangement = Arrangement.spacedBy(space = spacings.medium),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                YDSmallFab(onClick = {}) {
                    YDIcon(imageVector = YDIcons.Add, contentDescription = null)
                }
                YDSmallFab(onClick = {}) {
                    YDIcon(imageVector = YDIcons.Search, contentDescription = null)
                }
                YDSmallFab(onClick = {}, enabled = false) {
                    YDIcon(imageVector = YDIcons.Add, contentDescription = null)
                }
            }
        }
        item { YDDivider(modifier = Modifier.padding(vertical = spacings.small)) }
        item {
            YDText(
                text = "Extended",
                style = typography.smRegular,
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
            )
            Row(
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
                horizontalArrangement = Arrangement.spacedBy(space = spacings.medium),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                YDExtendedFab(
                    text = "Create",
                    onClick = {},
                    icon = { YDIcon(imageVector = YDIcons.Add, contentDescription = null) },
                )
                YDExtendedFab(
                    text = "Create",
                    onClick = {},
                    expanded = false,
                    icon = { YDIcon(imageVector = YDIcons.Add, contentDescription = null) },
                )
            }
        }
        item {
            Row(
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
                horizontalArrangement = Arrangement.spacedBy(space = spacings.medium),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                YDExtendedFab(text = "No icon", onClick = {})
                YDExtendedFab(
                    text = "Disabled",
                    onClick = {},
                    enabled = false,
                    icon = { YDIcon(imageVector = YDIcons.Add, contentDescription = null) },
                )
            }
        }
        item { YDDivider(modifier = Modifier.padding(vertical = spacings.small)) }
        item {
            YDText(
                text = "Scroll down to collapse the live FAB in the bottom-right corner",
                style = typography.smRegular,
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
            )
        }
        // Extra items so the FAB collapse animation is visible when scrolling
        items(count = 20) { index ->
            YDText(
                text = "Item ${index + 1}",
                style = typography.mdRegular,
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.medium),
            )
        }
    }
}

@PhonePreview
@Composable
private fun Preview() = YDContentPreview(data = FabScreenData()) {
    Content(
        contentPadding = PaddingValues(),
        listState = rememberLazyListState(),
    )
}
