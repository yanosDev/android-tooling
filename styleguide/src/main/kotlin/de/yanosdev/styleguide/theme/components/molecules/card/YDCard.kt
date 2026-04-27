@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.YDSurface
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.foundations.semantics.YDShadow
import de.yanosdev.styleguide.theme.foundations.semantics.YDTonal
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview

private val CardBorderWidth = 1.dp

/**
 * Static filled card with no interaction.
 *
 * @param modifier Modifier applied to the card surface.
 * @param colors Color scheme controlling background and content colors.
 * @param shadowElevation Drop shadow depth.
 * @param shape Card corner shape.
 * @param tonalElevation Tonal elevation applied to the background color.
 * @param content Card body content.
 */
@Composable
fun YDCard(
    modifier: Modifier = Modifier,
    colors: YDCardColors = YDCardDefaults.cardColors(),
    shadowElevation: YDShadow = YDCardDefaults.ShadowElevation,
    shape: Shape = YDCardDefaults.Shape,
    tonalElevation: YDTonal = YDCardDefaults.TonalElevation,
    content: @Composable () -> Unit,
) {
    YDSurface(
        modifier = modifier,
        border = BorderStroke(width = CardBorderWidth, brush = SolidColor(colors.borderColor)),
        color = colors.containerColor,
        contentColor = colors.contentColor,
        shadowElevation = shadowElevation,
        shape = shape,
        tonalElevation = tonalElevation,
        content = content,
    )
}

/**
 * Clickable filled card with ripple feedback.
 *
 * @param onClick Called when the card is tapped.
 * @param modifier Modifier applied to the card surface.
 * @param colors Color scheme controlling background and content colors.
 * @param enabled Whether the card responds to clicks.
 * @param interactionSource Source of interaction events (hover, press, focus).
 * @param shadowElevation Drop shadow depth.
 * @param shape Card corner shape.
 * @param tonalElevation Tonal elevation applied to the background color.
 * @param onLongClick Called on long-press. Null disables long-click.
 * @param content Card body content.
 */
@Composable
fun YDCard(
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
    YDSurface(
        onClick = onClick,
        modifier = modifier,
        border = BorderStroke(width = CardBorderWidth, brush = SolidColor(colors.borderColor)),
        color = colors.containerColor,
        contentColor = colors.contentColor,
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
private fun StaticPreview() = YDPreview {
    Column(modifier = Modifier.padding(all = spacings.medium)) {
        YDCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(all = spacings.large)) {
                YDText(text = "Card title", style = typography.h5)
                YDText(text = "Card body content goes here.", style = typography.mdRegular)
            }
        }
    }
}

@PhonePreview
@Composable
private fun ClickablePreview() = YDPreview {
    Column(modifier = Modifier.padding(all = spacings.medium)) {
        YDCard(onClick = {}, modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(all = spacings.large)) {
                YDText(text = "Clickable card", style = typography.h5)
                YDText(text = "Tap me!", style = typography.mdRegular)
            }
        }
    }
}
