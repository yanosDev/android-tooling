@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.dropdown

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.divider.YDDivider
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcon
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcons
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.molecules.button.YDPrimaryButton
import de.yanosdev.styleguide.theme.components.molecules.button.YDSecondaryButton
import de.yanosdev.styleguide.theme.components.molecules.dropdown.YDDropdownMenu
import de.yanosdev.styleguide.theme.components.molecules.dropdown.YDDropdownMenuDivider
import de.yanosdev.styleguide.theme.components.molecules.dropdown.YDDropdownMenuItem
import de.yanosdev.styleguide.theme.components.organisms.screen.YDDefaultScreen
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContent
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.tooling.ui.dropdown.model.DropdownAction
import de.yanosdev.tooling.ui.dropdown.model.DropdownScreenData

private val IconSize = 20.dp

@Composable
internal fun DropdownScreen(
    navBack: @Composable () -> Unit,
    viewModel: DropdownViewModel,
    modifier: Modifier = Modifier,
) = YDDefaultScreen(
    modifier = modifier,
    navBack = navBack,
    title = "Dropdown Menus",
) { contentPadding ->
    LaunchedEffect(viewModel.navEvents) {
        viewModel.navEvents.collect { navAction ->
            when (navAction) {
                else -> {}
            }
        }
    }

    YDUIContent(viewModel = viewModel) {
        Content(contentPadding = contentPadding)
    }
}

@Composable
internal fun YDUIContentScope<DropdownScreenData, DropdownAction>.Content(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    var basicExpanded by remember { mutableStateOf(value = false) }
    var iconsExpanded by remember { mutableStateOf(value = false) }
    var dividersExpanded by remember { mutableStateOf(value = false) }
    var trailingExpanded by remember { mutableStateOf(value = false) }

    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
    ) {
        item {
            YDText(
                text = "Basic",
                style = typography.smRegular,
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
            )
            Box(modifier = Modifier.padding(horizontal = spacings.large)) {
                YDPrimaryButton(
                    text = "Open menu",
                    onClick = { basicExpanded = true },
                )
                YDDropdownMenu(
                    expanded = basicExpanded,
                    onDismissRequest = { basicExpanded = false },
                ) {
                    YDDropdownMenuItem(text = "Edit", onClick = { basicExpanded = false })
                    YDDropdownMenuItem(text = "Duplicate", onClick = { basicExpanded = false })
                    YDDropdownMenuItem(text = "Archive", onClick = { basicExpanded = false })
                    YDDropdownMenuItem(
                        text = "Delete",
                        onClick = { basicExpanded = false },
                        enabled = false,
                    )
                }
            }
        }

        item { YDDivider(modifier = Modifier.padding(vertical = spacings.medium)) }

        item {
            YDText(
                text = "With leading icons",
                style = typography.smRegular,
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
            )
            Box(modifier = Modifier.padding(horizontal = spacings.large)) {
                YDSecondaryButton(
                    text = "Open menu",
                    onClick = { iconsExpanded = true },
                )
                YDDropdownMenu(
                    expanded = iconsExpanded,
                    onDismissRequest = { iconsExpanded = false },
                ) {
                    YDDropdownMenuItem(
                        text = "Edit",
                        onClick = { iconsExpanded = false },
                        leadingIcon = {
                            YDIcon(
                                imageVector = YDIcons.Edit,
                                contentDescription = null,
                                modifier = Modifier.size(size = IconSize),
                            )
                        },
                    )
                    YDDropdownMenuItem(
                        text = "Share",
                        onClick = { iconsExpanded = false },
                        leadingIcon = {
                            YDIcon(
                                imageVector = YDIcons.Share,
                                contentDescription = null,
                                modifier = Modifier.size(size = IconSize),
                            )
                        },
                    )
                    YDDropdownMenuItem(
                        text = "Download",
                        onClick = { iconsExpanded = false },
                        leadingIcon = {
                            YDIcon(
                                imageVector = YDIcons.Download,
                                contentDescription = null,
                                modifier = Modifier.size(size = IconSize),
                            )
                        },
                    )
                    YDDropdownMenuItem(
                        text = "Delete",
                        onClick = { iconsExpanded = false },
                        leadingIcon = {
                            YDIcon(
                                imageVector = YDIcons.Trash,
                                contentDescription = null,
                                modifier = Modifier.size(size = IconSize),
                            )
                        },
                    )
                }
            }
        }

        item { YDDivider(modifier = Modifier.padding(vertical = spacings.medium)) }

        item {
            YDText(
                text = "With dividers",
                style = typography.smRegular,
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
            )
            Box(modifier = Modifier.padding(horizontal = spacings.large)) {
                YDSecondaryButton(
                    text = "Open menu",
                    onClick = { dividersExpanded = true },
                )
                YDDropdownMenu(
                    expanded = dividersExpanded,
                    onDismissRequest = { dividersExpanded = false },
                ) {
                    YDDropdownMenuItem(
                        text = "Copy",
                        onClick = { dividersExpanded = false },
                        leadingIcon = {
                            YDIcon(
                                imageVector = YDIcons.Copy,
                                contentDescription = null,
                                modifier = Modifier.size(size = IconSize),
                            )
                        },
                    )
                    YDDropdownMenuItem(
                        text = "Share",
                        onClick = { dividersExpanded = false },
                        leadingIcon = {
                            YDIcon(
                                imageVector = YDIcons.Share,
                                contentDescription = null,
                                modifier = Modifier.size(size = IconSize),
                            )
                        },
                    )
                    YDDropdownMenuDivider()
                    YDDropdownMenuItem(
                        text = "Delete",
                        onClick = { dividersExpanded = false },
                        leadingIcon = {
                            YDIcon(
                                imageVector = YDIcons.Trash,
                                contentDescription = null,
                                modifier = Modifier.size(size = IconSize),
                            )
                        },
                    )
                }
            }
        }

        item { YDDivider(modifier = Modifier.padding(vertical = spacings.medium)) }

        item {
            YDText(
                text = "With trailing content",
                style = typography.smRegular,
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
            )
            Box(modifier = Modifier.padding(horizontal = spacings.large)) {
                YDSecondaryButton(
                    text = "Open menu",
                    onClick = { trailingExpanded = true },
                )
                YDDropdownMenu(
                    expanded = trailingExpanded,
                    onDismissRequest = { trailingExpanded = false },
                ) {
                    YDDropdownMenuItem(
                        text = "Settings",
                        onClick = { trailingExpanded = false },
                        leadingIcon = {
                            YDIcon(
                                imageVector = YDIcons.Settings,
                                contentDescription = null,
                                modifier = Modifier.size(size = IconSize),
                            )
                        },
                        trailingContent = {
                            YDIcon(
                                imageVector = YDIcons.ArrowRight,
                                contentDescription = null,
                                modifier = Modifier.size(size = IconSize),
                            )
                        },
                    )
                    YDDropdownMenuItem(
                        text = "Profile",
                        onClick = { trailingExpanded = false },
                        leadingIcon = {
                            YDIcon(
                                imageVector = YDIcons.User,
                                contentDescription = null,
                                modifier = Modifier.size(size = IconSize),
                            )
                        },
                        trailingContent = {
                            YDIcon(
                                imageVector = YDIcons.ArrowRight,
                                contentDescription = null,
                                modifier = Modifier.size(size = IconSize),
                            )
                        },
                    )
                    YDDropdownMenuDivider()
                    YDDropdownMenuItem(
                        text = "Sign out",
                        onClick = { trailingExpanded = false },
                    )
                }
            }
        }

        item { Spacer(modifier = Modifier.height(height = spacings.huge)) }
    }
}

@PhonePreview
@Composable
private fun Preview() = YDContentPreview(data = DropdownScreenData()) {
    Content(contentPadding = PaddingValues())
}
