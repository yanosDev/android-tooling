@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.fab

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.YDSurface
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcon
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcons
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.atoms.util.rememberYDRipple
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview

/**
 * Extended floating action button with a label and optional leading icon.
 *
 * When [expanded] is `true` (the default) the full label is visible alongside the icon, giving
 * a pill shape. When [expanded] is `false` and an [icon] is provided, the label animates out
 * with `shrinkHorizontally`, leaving just the icon in a circular pill — ideal for collapsing
 * the FAB while the user scrolls a list. When [icon] is `null` the label is always shown
 * regardless of [expanded].
 *
 * @param text Label displayed inside the FAB.
 * @param onClick Called when the FAB is tapped.
 * @param modifier Modifier applied to the FAB.
 * @param colors Container and content colors.
 * @param enabled Whether the FAB responds to taps.
 * @param expanded Whether the label is currently visible. Has no effect when [icon] is `null`.
 * @param icon Optional composable drawn before the label, typically a [YDIcon].
 */
@Composable
fun YDExtendedFab(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: YDFabColors = YDFabDefaults.fabColors(),
    enabled: Boolean = true,
    expanded: Boolean = true,
    icon: (@Composable () -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }

    YDSurface(
        modifier = modifier.heightIn(min = YDFabDefaults.FabSize),
        color = colors.containerColor(enabled = enabled),
        contentColor = colors.contentColor(enabled = enabled),
        shadowElevation = YDFabDefaults.ShadowElevation,
        shape = YDFabDefaults.FabShape,
    ) {
        Row(
            modifier = Modifier
                .widthIn(min = YDFabDefaults.FabSize)
                .clickable(
                    interactionSource = interactionSource,
                    indication = rememberYDRipple(),
                    enabled = enabled,
                    role = Role.Button,
                    onClick = onClick,
                )
                .padding(horizontal = spacings.medium, vertical = spacings.medium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            if (icon != null) {
                icon()
            }
            AnimatedVisibility(
                visible = expanded || icon == null,
                enter = fadeIn(animationSpec = tween(durationMillis = 200)) +
                        expandHorizontally(expandFrom = Alignment.Start),
                exit = fadeOut(animationSpec = tween(durationMillis = 200)) +
                        shrinkHorizontally(shrinkTowards = Alignment.Start),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (icon != null) {
                        Spacer(modifier = Modifier.width(width = spacings.small))
                    }
                    YDText(text = text, style = typography.smMediumBold)
                }
            }
        }
    }
}

@PhonePreview
@Composable
private fun ExtendedFabPreview() = YDPreview {
    Column(
        modifier = Modifier.padding(all = spacings.medium),
        verticalArrangement = Arrangement.spacedBy(space = spacings.medium),
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
        YDExtendedFab(text = "No icon", onClick = {})
        YDExtendedFab(
            text = "Disabled",
            onClick = {},
            enabled = false,
            icon = { YDIcon(imageVector = YDIcons.Add, contentDescription = null) },
        )
    }
}
