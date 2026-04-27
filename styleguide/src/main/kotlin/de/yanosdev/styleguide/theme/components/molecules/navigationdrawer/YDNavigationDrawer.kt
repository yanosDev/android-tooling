@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.navigationdrawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.YDSurface
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcon
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcons
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.atoms.util.rememberYDRipple
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDContentColor
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview

private val IconSize = 24.dp

/**
 * Permanent navigation drawer displayed along the start edge on expanded-width screens.
 *
 * Hosts a vertical list of [YDNavigationDrawerItem]s with an optional [header] slot at the top.
 * The drawer is always visible — use it for expanded (desktop/tablet landscape) layouts where a
 * persistent sidebar aids navigation. Status-bar insets are consumed so content is not obscured.
 *
 * @param modifier Modifier applied to the surface container.
 * @param colors Container and item colors.
 * @param windowInsets Insets applied to the top of the drawer for system status bar clearance.
 * @param header Optional composable placed above the item list, e.g. a branded header.
 * @param content Slot populated with [YDNavigationDrawerItem] composables.
 */
@Composable
fun YDNavigationDrawer(
    modifier: Modifier = Modifier,
    colors: YDNavigationDrawerColors = YDNavigationDrawerDefaults.navigationDrawerColors(),
    windowInsets: WindowInsets = WindowInsets.statusBars,
    header: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    YDSurface(
        modifier = modifier
            .fillMaxHeight()
            .width(width = YDNavigationDrawerDefaults.DrawerWidth),
        color = colors.containerColor,
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .windowInsetsPadding(insets = windowInsets)
                .verticalScroll(state = rememberScrollState())
                .padding(horizontal = spacings.small, vertical = spacings.medium),
            verticalArrangement = Arrangement.spacedBy(space = spacings.extraTiny),
        ) {
            if (header != null) {
                Box(modifier = Modifier.padding(bottom = spacings.medium)) {
                    header()
                }
            }
            content()
        }
    }
}

/**
 * A single destination inside a [YDNavigationDrawer].
 *
 * Displays [label] with an optional leading [icon] in a horizontal row. When [selected],
 * the row is highlighted with the indicator color. Tapping the item calls [onClick]; when
 * [enabled] is false the item ignores input and renders with a dimmed color.
 *
 * @param label Text label for the destination.
 * @param selected Whether this destination is currently active.
 * @param onClick Called when the item is tapped.
 * @param modifier Modifier applied to the item row.
 * @param colors Colors for the indicator and text.
 * @param enabled Whether the item accepts taps.
 * @param icon Optional leading icon composable drawn before the label.
 */
@Composable
fun YDNavigationDrawerItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: YDNavigationDrawerColors = YDNavigationDrawerDefaults.navigationDrawerColors(),
    enabled: Boolean = true,
    icon: (@Composable () -> Unit)? = null,
) {
    val contentColor = colors.itemContentColor(selected = selected, enabled = enabled)
    val interactionSource = remember { MutableInteractionSource() }

    CompositionLocalProvider(value = LocalYDContentColor provides contentColor) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clip(shape = YDNavigationDrawerDefaults.ItemShape)
                .then(
                    if (selected) Modifier.background(color = colors.indicatorColor)
                    else Modifier
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = rememberYDRipple(bounded = true),
                    enabled = enabled,
                    role = Role.Tab,
                    onClick = onClick,
                )
                .padding(horizontal = spacings.large, vertical = spacings.medium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space = spacings.medium),
        ) {
            if (icon != null) {
                Box(modifier = Modifier.size(size = IconSize)) {
                    icon()
                }
            }
            YDText(
                text = label,
                style = typography.mdMediumBold,
            )
        }
    }
}

@PhonePreview
@Composable
private fun NavigationDrawerPreview() = YDPreview {
    var selected by remember { mutableIntStateOf(value = 0) }
    YDNavigationDrawer {
        YDNavigationDrawerItem(
            label = "Home",
            selected = selected == 0,
            onClick = { selected = 0 },
            icon = { YDIcon(imageVector = YDIcons.Home, contentDescription = null) },
        )
        YDNavigationDrawerItem(
            label = "Search",
            selected = selected == 1,
            onClick = { selected = 1 },
            icon = { YDIcon(imageVector = YDIcons.Search, contentDescription = null) },
        )
        YDNavigationDrawerItem(
            label = "Settings",
            selected = selected == 2,
            onClick = { selected = 2 },
            icon = { YDIcon(imageVector = YDIcons.Settings, contentDescription = null) },
        )
    }
}
