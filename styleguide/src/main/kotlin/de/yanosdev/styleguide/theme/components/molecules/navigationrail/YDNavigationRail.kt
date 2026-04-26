@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.navigationrail

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
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

private val RailWidth = 80.dp
private val IndicatorWidth = 56.dp
private val IndicatorHeight = 32.dp
private val IconSize = 24.dp
private val IndicatorShape = RoundedCornerShape(percent = 50)

/**
 * Navigation rail displayed along the start edge on medium-width screens.
 *
 * Hosts a vertical stack of [YDNavigationRailItem]s with an optional [header] slot at the top
 * (typically a floating action button). Status-bar insets are consumed so items are not obscured.
 *
 * @param modifier Modifier applied to the surface container.
 * @param colors Container and item colors.
 * @param windowInsets Insets applied to the top of the rail for system status bar clearance.
 * @param header Optional composable placed above the item list, e.g. a FAB.
 * @param content Slot populated with [YDNavigationRailItem] composables.
 */
@Composable
fun YDNavigationRail(
    modifier: Modifier = Modifier,
    colors: YDNavigationRailColors = YDNavigationRailDefaults.navigationRailColors(),
    windowInsets: WindowInsets = WindowInsets.statusBars,
    header: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    YDSurface(
        modifier = modifier
            .fillMaxHeight()
            .width(width = RailWidth),
        color = colors.containerColor,
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .windowInsetsPadding(insets = windowInsets)
                .verticalScroll(state = rememberScrollState())
                .padding(vertical = spacings.medium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = spacings.small),
        ) {
            if (header != null) {
                Box(
                    modifier = Modifier.padding(bottom = spacings.medium),
                    contentAlignment = Alignment.Center,
                ) {
                    header()
                }
            }
            content()
        }
    }
}

/**
 * A single destination inside a [YDNavigationRail].
 *
 * When [selected], a pill-shaped indicator is drawn behind [content] (the icon). An optional
 * [label] is shown below. Tapping the item calls [onClick]; when [enabled] is false the item
 * ignores input and renders with a dimmed color.
 *
 * Pass the icon as the trailing [content] lambda.
 *
 * @param selected Whether this destination is currently active.
 * @param onClick Called when the item is tapped.
 * @param modifier Modifier applied to the item column.
 * @param colors Colors for the indicator, icon, and label.
 * @param enabled Whether the item accepts taps.
 * @param label Optional text label shown below the icon indicator.
 * @param content The icon composable drawn inside the indicator area.
 */
@Composable
fun YDNavigationRailItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: YDNavigationRailColors = YDNavigationRailDefaults.navigationRailColors(),
    enabled: Boolean = true,
    label: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val contentColor = colors.itemContentColor(selected = selected, enabled = enabled)
    val animatedContentColor by animateColorAsState(targetValue = contentColor, label = "contentColor")
    val animatedIndicatorColor by animateColorAsState(
        targetValue = if (selected) colors.indicatorColor else Color.Transparent,
        label = "indicatorColor",
    )
    val interactionSource = remember { MutableInteractionSource() }

    CompositionLocalProvider(value = LocalYDContentColor provides animatedContentColor) {
        Column(
            modifier = modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = rememberYDRipple(bounded = false),
                    enabled = enabled,
                    role = Role.Tab,
                    onClick = onClick,
                )
                .padding(horizontal = spacings.small, vertical = spacings.small),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = spacings.extraTiny),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Box(
                    modifier = Modifier
                        .size(width = IndicatorWidth, height = IndicatorHeight)
                        .clip(shape = IndicatorShape)
                        .background(color = animatedIndicatorColor),
                )
                Box(modifier = Modifier.size(size = IconSize)) {
                    content()
                }
            }
            if (label != null) {
                label()
            }
        }
    }
}

@PhonePreview
@Composable
private fun NavigationRailPreview() = YDPreview {
    var selected by remember { mutableIntStateOf(value = 0) }
    YDNavigationRail {
        YDNavigationRailItem(
            selected = selected == 0,
            onClick = { selected = 0 },
            label = { YDText(text = "Home", style = typography.smRegular) },
        ) {
            YDIcon(imageVector = YDIcons.Home, contentDescription = null)
        }
        YDNavigationRailItem(
            selected = selected == 1,
            onClick = { selected = 1 },
            label = { YDText(text = "Search", style = typography.smRegular) },
        ) {
            YDIcon(imageVector = YDIcons.Search, contentDescription = null)
        }
        YDNavigationRailItem(
            selected = selected == 2,
            onClick = { selected = 2 },
            label = { YDText(text = "Settings", style = typography.smRegular) },
        ) {
            YDIcon(imageVector = YDIcons.Settings, contentDescription = null)
        }
    }
}
