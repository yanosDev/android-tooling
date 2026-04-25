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
 * Card that visually reflects a selected/deselected state via animated color transitions.
 *
 * The card is always clickable; selection state management is the caller's responsibility.
 *
 * @param selected Whether the card is currently selected.
 * @param onClick Called when the card is tapped. Caller must update [selected].
 * @param modifier Modifier applied to the card surface.
 * @param colors Color scheme for both selected and unselected states.
 * @param enabled Whether the card responds to clicks.
 * @param interactionSource Source of interaction events.
 * @param shadowElevation Drop shadow depth.
 * @param shape Card corner shape.
 * @param tonalElevation Tonal elevation applied to the background color.
 * @param onLongClick Called on long-press. Null disables long-click.
 * @param content Card body content.
 */
@Composable
fun YDSelectableCard(
    selected: Boolean,
    onClick: () -> Unit,
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
        targetValue = if (selected) colors.selectedContainerColor else colors.containerColor,
        label = "cardContainerColor",
    )
    val contentColor by animateColorAsState(
        targetValue = if (selected) colors.selectedContentColor else colors.contentColor,
        label = "cardContentColor",
    )
    val borderColor by animateColorAsState(
        targetValue = if (selected) colors.selectedBorderColor else colors.borderColor,
        label = "cardBorderColor",
    )

    YDSurface(
        onClick = onClick,
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
private fun SelectedPreview() = YDPreview {
    Column(
        modifier = Modifier.padding(all = spacings.medium),
        verticalArrangement = Arrangement.spacedBy(spacings.medium),
    ) {
        var selected by remember { mutableStateOf(value = false) }
        YDSelectableCard(
            selected = selected,
            onClick = { selected = !selected },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.padding(all = spacings.large),
                horizontalArrangement = Arrangement.spacedBy(spacings.medium),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                YDIcon(
                    imageVector = if (selected) YDIcons.Check else YDIcons.Add,
                    contentDescription = null,
                )
                Column {
                    YDText(text = "Selectable card", style = typography.h5)
                    YDText(text = if (selected) "Selected" else "Not selected", style = typography.mdRegular)
                }
            }
        }
    }
}
