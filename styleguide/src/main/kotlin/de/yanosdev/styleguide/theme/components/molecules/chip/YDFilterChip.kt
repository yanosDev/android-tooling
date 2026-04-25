@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.chip

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
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
import androidx.compose.runtime.getValue
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

private val CheckIconSize = 14.dp

/**
 * Toggleable filter chip. Tapping cycles between selected and unselected; the caller owns the
 * [selected] state and updates it via [onSelectedChange].
 *
 * When selected, a check icon expands in from the leading edge and the chip fills with the active
 * color. Container, border, and content colors all animate on a short tween for a smooth transition.
 *
 * @param selected Whether the chip is currently selected.
 * @param text Label displayed inside the chip.
 * @param onSelectedChange Called with the new value when the chip is tapped.
 * @param modifier Modifier applied to the chip.
 * @param colors Colors for the selected, unselected, and disabled states.
 * @param enabled Whether the chip responds to taps.
 */
@Composable
fun YDFilterChip(
    selected: Boolean,
    text: String,
    onSelectedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    colors: YDChipColors = YDChipDefaults.filterChipColors(),
    enabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }

    val containerColor by animateColorAsState(
        targetValue = colors.containerColor(enabled = enabled, selected = selected),
        animationSpec = tween(durationMillis = 150),
        label = "chipContainer",
    )
    val borderColor by animateColorAsState(
        targetValue = colors.borderColor(enabled = enabled, selected = selected),
        animationSpec = tween(durationMillis = 150),
        label = "chipBorder",
    )
    val contentColor by animateColorAsState(
        targetValue = colors.contentColor(enabled = enabled, selected = selected),
        animationSpec = tween(durationMillis = 150),
        label = "chipContent",
    )

    YDSurface(
        modifier = modifier.wrapContentSize(),
        border = BorderStroke(width = YDChipDefaults.BorderWidth, color = borderColor),
        color = containerColor,
        contentColor = contentColor,
        shape = YDChipDefaults.Shape,
    ) {
        Row(
            modifier = Modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = rememberYDRipple(),
                    enabled = enabled,
                    role = Role.Checkbox,
                    onClick = { onSelectedChange(!selected) },
                )
                .padding(horizontal = spacings.medium, vertical = spacings.extraTiny),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            AnimatedVisibility(
                visible = selected,
                enter = fadeIn(animationSpec = tween(durationMillis = 150)) + expandHorizontally(expandFrom = Alignment.Start),
                exit = fadeOut(animationSpec = tween(durationMillis = 150)) + shrinkHorizontally(shrinkTowards = Alignment.Start),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    YDIcon(
                        imageVector = YDIcons.Check,
                        contentDescription = null,
                        modifier = Modifier.size(size = CheckIconSize),
                    )
                    Spacer(modifier = Modifier.width(width = spacings.extraTiny))
                }
            }
            YDText(text = text, style = typography.smRegular)
        }
    }
}

@PhonePreview
@Composable
private fun FilterChipPreview() = YDPreview {
    Row(
        modifier = Modifier.padding(all = spacings.medium),
        horizontalArrangement = Arrangement.spacedBy(space = spacings.small),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        YDFilterChip(selected = false, text = "Unselected", onSelectedChange = {})
        YDFilterChip(selected = true, text = "Selected", onSelectedChange = {})
        YDFilterChip(selected = false, text = "Disabled", onSelectedChange = {}, enabled = false)
    }
}
