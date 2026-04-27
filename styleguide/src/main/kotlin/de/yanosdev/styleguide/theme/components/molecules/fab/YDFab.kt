@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.fab

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.YDSurface
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcon
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcons
import de.yanosdev.styleguide.theme.components.atoms.util.rememberYDRipple
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview

/**
 * Standard floating action button (56 × 56 dp). Use for the primary action on a screen.
 *
 * The [content] slot is centered inside the circular surface — typically a single [YDIcon].
 * The full surface is the touch target; ripple is clipped to the circle shape.
 *
 * @param onClick Called when the FAB is tapped.
 * @param modifier Modifier applied to the FAB.
 * @param colors Container and content colors.
 * @param enabled Whether the FAB responds to taps.
 * @param content Composable drawn inside the FAB, centered.
 */
@Composable
fun YDFab(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: YDFabColors = YDFabDefaults.fabColors(),
    enabled: Boolean = true,
    content: @Composable () -> Unit,
) = BaseFab(
    onClick = onClick,
    size = YDFabDefaults.FabSize,
    modifier = modifier,
    colors = colors,
    enabled = enabled,
    content = content,
)

/**
 * Small floating action button (40 × 40 dp). Use for secondary or contextual actions.
 *
 * Identical to [YDFab] but smaller. The [content] slot is centered; typically a [YDIcon].
 *
 * @param onClick Called when the FAB is tapped.
 * @param modifier Modifier applied to the FAB.
 * @param colors Container and content colors.
 * @param enabled Whether the FAB responds to taps.
 * @param content Composable drawn inside the FAB, centered.
 */
@Composable
fun YDSmallFab(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: YDFabColors = YDFabDefaults.fabColors(),
    enabled: Boolean = true,
    content: @Composable () -> Unit,
) = BaseFab(
    onClick = onClick,
    size = YDFabDefaults.SmallFabSize,
    modifier = modifier,
    colors = colors,
    enabled = enabled,
    content = content,
)

@Composable
private fun BaseFab(
    onClick: () -> Unit,
    size: androidx.compose.ui.unit.Dp,
    modifier: Modifier,
    colors: YDFabColors,
    enabled: Boolean,
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    YDSurface(
        modifier = modifier.size(size = size),
        color = colors.containerColor(enabled = enabled),
        contentColor = colors.contentColor(enabled = enabled),
        shadowElevation = YDFabDefaults.ShadowElevation,
        shape = YDFabDefaults.FabShape,
    ) {
        Box(
            modifier = Modifier.clickable(
                interactionSource = interactionSource,
                indication = rememberYDRipple(),
                enabled = enabled,
                role = Role.Button,
                onClick = onClick,
            ),
            contentAlignment = Alignment.Center,
        ) {
            content()
        }
    }
}

@PhonePreview
@Composable
private fun FabPreview() = YDPreview {
    Column(
        modifier = Modifier.padding(all = spacings.medium),
        verticalArrangement = Arrangement.spacedBy(space = spacings.medium),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(space = spacings.medium),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            YDFab(onClick = {}) {
                YDIcon(imageVector = YDIcons.Add, contentDescription = null)
            }
            YDFab(onClick = {}, enabled = false) {
                YDIcon(imageVector = YDIcons.Add, contentDescription = null)
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(space = spacings.medium),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            YDSmallFab(onClick = {}) {
                YDIcon(imageVector = YDIcons.Add, contentDescription = null)
            }
            YDSmallFab(onClick = {}, enabled = false) {
                YDIcon(imageVector = YDIcons.Add, contentDescription = null)
            }
        }
    }
}
