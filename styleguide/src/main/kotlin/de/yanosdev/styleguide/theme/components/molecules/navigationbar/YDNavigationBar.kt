@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.navigationbar

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
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

private val IndicatorWidth = 64.dp
private val IndicatorHeight = 32.dp
private val IconSize = 24.dp
private val ItemHeight = 80.dp
private val IndicatorShape = RoundedCornerShape(percent = 50)

/**
 * Navigation bar displayed at the bottom of compact-width screens.
 *
 * Hosts a horizontal row of [YDNavigationBarItem]s. Exactly one item should be selected at a
 * time; the caller owns the selected state and updates it in each item's [YDNavigationBarItem.onClick].
 * Bottom system-bar insets are consumed automatically via [windowInsets].
 *
 * @param modifier Modifier applied to the surface container.
 * @param colors Container and item colors.
 * @param windowInsets Insets to apply below the item row for system navigation bar clearance.
 * @param content Slot populated with [YDNavigationBarItem] composables.
 */
@Composable
fun YDNavigationBar(
    modifier: Modifier = Modifier,
    colors: YDNavigationBarColors = YDNavigationBarDefaults.navigationBarColors(),
    windowInsets: WindowInsets = WindowInsets.navigationBars,
    content: @Composable () -> Unit,
) {
    YDSurface(
        modifier = modifier.fillMaxWidth(),
        color = colors.containerColor,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(insets = windowInsets)
                .height(height = ItemHeight)
                .padding(horizontal = spacings.small),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            content()
        }
    }
}

/**
 * A single destination inside a [YDNavigationBar].
 *
 * When [selected], a pill-shaped indicator is drawn behind [content] (the icon). An optional
 * [label] is shown below the icon in a smaller style. Tapping the item calls [onClick]; when
 * [enabled] is false the item ignores input and renders with a dimmed color.
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
fun YDNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: YDNavigationBarColors = YDNavigationBarDefaults.navigationBarColors(),
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
                .padding(horizontal = spacings.tiny, vertical = spacings.small),
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
private fun NavigationBarPreview() = YDPreview {
    var selected by remember { mutableIntStateOf(value = 0) }
    YDNavigationBar {
        YDNavigationBarItem(
            selected = selected == 0,
            onClick = { selected = 0 },
            label = { YDText(text = "Home", style = typography.smRegular) },
        ) {
            YDIcon(imageVector = YDIcons.Home, contentDescription = null)
        }
        YDNavigationBarItem(
            selected = selected == 1,
            onClick = { selected = 1 },
            label = { YDText(text = "Search", style = typography.smRegular) },
        ) {
            YDIcon(imageVector = YDIcons.Search, contentDescription = null)
        }
        YDNavigationBarItem(
            selected = selected == 2,
            onClick = { selected = 2 },
            label = { YDText(text = "Settings", style = typography.smRegular) },
        ) {
            YDIcon(imageVector = YDIcons.Settings, contentDescription = null)
        }
    }
}
