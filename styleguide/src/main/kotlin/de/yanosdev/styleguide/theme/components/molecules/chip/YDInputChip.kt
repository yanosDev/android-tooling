@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
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

private val LeadingIconSize = 16.dp
private val DismissIconSize = 14.dp

/**
 * Dismissible input chip, typically used to represent a user's selection (a chosen tag, contact,
 * or filter value). Tapping the trailing × removes the chip via [onDismiss].
 *
 * The chip body (excluding the × button) is optionally tappable via [onClick] — useful for
 * editing the value the chip represents.
 *
 * An optional [leadingIcon] slot accepts any composable, typically a small [YDIcon] or avatar.
 *
 * @param text Label displayed inside the chip.
 * @param onDismiss Called when the trailing × button is tapped.
 * @param modifier Modifier applied to the chip.
 * @param colors Colors for the default and disabled states.
 * @param enabled Whether the chip responds to taps.
 * @param leadingIcon Optional composable slot drawn before the label.
 * @param onClick Optional callback for tapping the chip body (excluding the × button).
 */
@Composable
fun YDInputChip(
    text: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    colors: YDChipColors = YDChipDefaults.inputChipColors(),
    enabled: Boolean = true,
    leadingIcon: (@Composable () -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    val bodyInteractionSource = remember { MutableInteractionSource() }
    val dismissInteractionSource = remember { MutableInteractionSource() }

    YDSurface(
        modifier = modifier.wrapContentSize(),
        border = BorderStroke(
            width = YDChipDefaults.BorderWidth,
            color = colors.borderColor(enabled = enabled, selected = false),
        ),
        color = colors.containerColor(enabled = enabled, selected = false),
        contentColor = colors.contentColor(enabled = enabled, selected = false),
        shape = YDChipDefaults.Shape,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Chip body — optionally tappable
            Row(
                modifier = Modifier
                    .clickable(
                        interactionSource = bodyInteractionSource,
                        indication = if (onClick != null) rememberYDRipple() else null,
                        enabled = enabled && onClick != null,
                        role = Role.Button,
                        onClick = { onClick?.invoke() },
                    )
                    .padding(
                        start = spacings.medium,
                        end = spacings.extraTiny,
                        top = spacings.extraTiny,
                        bottom = spacings.extraTiny,
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                if (leadingIcon != null) {
                    leadingIcon()
                    Spacer(modifier = Modifier.width(width = spacings.extraTiny))
                }
                YDText(text = text, style = typography.smRegular)
            }

            Spacer(modifier = Modifier.width(width = spacings.extraTiny))

            // Dismiss button — separate tap target
            Box(
                modifier = Modifier
                    .clickable(
                        interactionSource = dismissInteractionSource,
                        indication = rememberYDRipple(),
                        enabled = enabled,
                        role = Role.Button,
                        onClick = onDismiss,
                    )
                    .padding(
                        start = spacings.extraTiny,
                        end = spacings.small,
                        top = spacings.extraTiny,
                        bottom = spacings.extraTiny,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                YDIcon(
                    imageVector = YDIcons.Close,
                    contentDescription = "Remove",
                    modifier = Modifier.size(size = DismissIconSize),
                )
            }
        }
    }
}

@PhonePreview
@Composable
private fun InputChipPreview() = YDPreview {
    Row(
        modifier = Modifier.padding(all = spacings.medium),
        horizontalArrangement = Arrangement.spacedBy(space = spacings.small),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        YDInputChip(text = "Kotlin", onDismiss = {})
        YDInputChip(
            text = "Design",
            onDismiss = {},
            leadingIcon = {
                YDIcon(
                    imageVector = YDIcons.Star,
                    contentDescription = null,
                    modifier = Modifier.size(size = LeadingIconSize),
                )
            },
        )
        YDInputChip(text = "Disabled", onDismiss = {}, enabled = false)
    }
}
