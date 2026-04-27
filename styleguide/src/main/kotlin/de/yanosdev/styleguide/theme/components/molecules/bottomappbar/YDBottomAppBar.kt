@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.bottomappbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.YDSurface
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcon
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcons
import de.yanosdev.styleguide.theme.components.molecules.fab.YDFab
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDContentColor
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview

private val BarHeight = 80.dp

/**
 * Bottom app bar with a row of action slots and an optional trailing FAB.
 *
 * Use for screen-level actions — not navigation (use [YDNavigationBar] for that). The [content]
 * slot holds action icon buttons laid out at the start. The optional [floatingActionButton] slot
 * is placed at the end. Bottom system-bar insets are consumed automatically.
 *
 * @param modifier Modifier applied to the surface container.
 * @param colors Container and icon tint colors.
 * @param windowInsets Insets applied below the bar for system navigation bar clearance.
 * @param floatingActionButton Optional FAB composable anchored to the end of the bar.
 * @param content Action icon buttons placed at the start of the bar.
 */
@Composable
fun YDBottomAppBar(
    modifier: Modifier = Modifier,
    colors: YDBottomAppBarColors = YDBottomAppBarDefaults.bottomAppBarColors(),
    windowInsets: WindowInsets = WindowInsets.navigationBars,
    floatingActionButton: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    YDSurface(
        modifier = modifier.fillMaxWidth(),
        color = colors.containerColor,
    ) {
        CompositionLocalProvider(value = LocalYDContentColor provides colors.contentColor) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(insets = windowInsets)
                    .height(height = BarHeight)
                    .padding(horizontal = spacings.medium),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(space = spacings.small),
            ) {
                Row(
                    modifier = Modifier.weight(weight = 1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(space = spacings.small),
                ) {
                    content()
                }
                if (floatingActionButton != null) {
                    Spacer(modifier = Modifier.width(width = spacings.small))
                    floatingActionButton()
                }
            }
        }
    }
}

@PhonePreview
@Composable
private fun BottomAppBarPreview() = YDPreview {
    YDBottomAppBar(
        floatingActionButton = {
            YDFab(onClick = {}) {
                YDIcon(imageVector = YDIcons.Add, contentDescription = null)
            }
        },
    ) {
        YDIcon(imageVector = YDIcons.Menu, contentDescription = null)
        YDIcon(imageVector = YDIcons.Search, contentDescription = null)
        YDIcon(imageVector = YDIcons.Filter, contentDescription = null)
    }
}
