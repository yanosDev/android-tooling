@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.card

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.YDSurface
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcon
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcons
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.foundations.semantics.YDShadow
import de.yanosdev.styleguide.theme.foundations.semantics.YDTonal
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview

private val CardBorderWidth = 1.dp

/**
 * Card that acts as a toggle, switching between on and off states with animated color transitions.
 *
 * Unlike [YDSelectableCard], this component exposes a typed [onToggleChange] callback that receives
 * the new boolean value on each tap, matching the toggle contract used by switches and checkboxes.
 *
 * @param toggled Whether the card is currently toggled on.
 * @param onToggleChange Called with the new toggled value when the card is tapped.
 * @param modifier Modifier applied to the card surface.
 * @param colors Color scheme for both toggled-on and toggled-off states.
 * @param enabled Whether the card responds to taps.
 * @param interactionSource Source of interaction events.
 * @param shadowElevation Drop shadow depth.
 * @param shape Card corner shape.
 * @param tonalElevation Tonal elevation applied to the background color.
 * @param onLongClick Called on long-press. Null disables long-click.
 * @param content Card body content.
 */
@Composable
fun YDToggleableCard(
    toggled: Boolean,
    onToggleChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    colors: YDCardColors = YDCardDefaults.cardColors(),
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shadowElevation: YDShadow = YDCardDefaults.ShadowElevation,
    shape: Shape = YDCardDefaults.Shape,
    tonalElevation: YDTonal = YDCardDefaults.TonalElevation,
    onLongClick: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val containerColor by animateColorAsState(
        targetValue = if (toggled) colors.selectedContainerColor else colors.containerColor,
        label = "cardContainerColor",
    )
    val contentColor by animateColorAsState(
        targetValue = if (toggled) colors.selectedContentColor else colors.contentColor,
        label = "cardContentColor",
    )
    val borderColor by animateColorAsState(
        targetValue = if (toggled) colors.selectedBorderColor else colors.borderColor,
        label = "cardBorderColor",
    )

    YDSurface(
        onClick = { onToggleChange(!toggled) },
        modifier = modifier,
        border = BorderStroke(width = CardBorderWidth, brush = SolidColor(borderColor)),
        color = containerColor,
        contentColor = contentColor,
        enabled = enabled,
        interactionSource = interactionSource,
        shadowElevation = shadowElevation,
        shape = shape,
        tonalElevation = tonalElevation,
        onLongClick = onLongClick,
        content = content,
    )
}

@PhonePreview
@Composable
private fun ToggledOffPreview() = YDPreview {
    Column(modifier = Modifier.padding(all = spacings.medium)) {
        YDToggleableCard(
            toggled = false,
            onToggleChange = {},
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.padding(all = spacings.large),
                horizontalArrangement = Arrangement.spacedBy(spacings.medium),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                YDIcon(imageVector = YDIcons.Bell, contentDescription = null)
                Column {
                    YDText(text = "Notifications", style = typography.h5)
                    YDText(text = "Tap to enable", style = typography.mdRegular)
                }
            }
        }
    }
}

@PhonePreview
@Composable
private fun ToggledOnPreview() = YDPreview {
    Column(modifier = Modifier.padding(all = spacings.medium)) {
        var toggled by remember { mutableStateOf(value = true) }
        YDToggleableCard(
            toggled = toggled,
            onToggleChange = { toggled = it },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.padding(all = spacings.large),
                horizontalArrangement = Arrangement.spacedBy(spacings.medium),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                YDIcon(imageVector = YDIcons.Bell, contentDescription = null)
                Column {
                    YDText(text = "Notifications", style = typography.h5)
                    YDText(text = "Tap to disable", style = typography.mdRegular)
                }
            }
        }
    }
}
