@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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

/**
 * Non-toggleable action chip. Use for surface-level actions or suggestions (e.g. quick filters,
 * AI-generated suggestions). Each tap triggers [onClick] — the chip has no persistent state.
 *
 * An optional [leadingIcon] slot accepts any composable, typically a [YDIcon] at 16 dp.
 *
 * @param text Label displayed inside the chip.
 * @param onClick Called when the chip is tapped.
 * @param modifier Modifier applied to the chip.
 * @param colors Colors for the default and disabled states.
 * @param enabled Whether the chip responds to taps.
 * @param leadingIcon Optional composable slot drawn before the label.
 */
@Composable
fun YDAssistChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: YDChipColors = YDChipDefaults.assistChipColors(),
    enabled: Boolean = true,
    leadingIcon: (@Composable () -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }

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
        Row(
            modifier = Modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = rememberYDRipple(),
                    enabled = enabled,
                    role = Role.Button,
                    onClick = onClick,
                )
                .padding(horizontal = spacings.medium, vertical = spacings.extraTiny),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            if (leadingIcon != null) {
                leadingIcon()
                Spacer(modifier = Modifier.width(width = spacings.extraTiny))
            }
            YDText(text = text, style = typography.smRegular)
        }
    }
}

@PhonePreview
@Composable
private fun AssistChipPreview() = YDPreview {
    Row(
        modifier = Modifier.padding(all = spacings.medium),
        horizontalArrangement = Arrangement.spacedBy(space = spacings.small),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        YDAssistChip(text = "Search", onClick = {})
        YDAssistChip(
            text = "Add filter",
            onClick = {},
            leadingIcon = {
                YDIcon(
                    imageVector = YDIcons.Filter,
                    contentDescription = null,
                    modifier = Modifier.size(size = LeadingIconSize),
                )
            },
        )
        YDAssistChip(text = "Disabled", onClick = {}, enabled = false)
    }
}
