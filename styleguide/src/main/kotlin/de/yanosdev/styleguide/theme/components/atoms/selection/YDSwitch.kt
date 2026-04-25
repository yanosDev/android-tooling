@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.selection

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.atoms.util.rememberYDRipple
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview

private val TrackWidth = 52.dp
private val TrackHeight = 28.dp
private val ThumbRadius = 10.dp

/**
 * Labeled switch row. The entire row is the touch target.
 *
 * The indicator is drawn on Canvas — not a Material3 component. The thumb is a circle that slides
 * left/right with a bouncy spring, contained within the pill-shaped track. The track cross-fades
 * fully between inactive and active colors via a separate tween so colors reach 100% saturation
 * independent of the spring overshoot.
 *
 * @param checked Whether the switch is currently on.
 * @param text Label shown to the left of the indicator.
 * @param onCheckedChange Called with the new value when the row is tapped.
 * @param modifier Modifier applied to the full-width row.
 * @param colors Colors for active, inactive, and disabled states.
 * @param enabled Whether the row responds to taps.
 */
@Composable
fun YDSwitch(
    checked: Boolean,
    text: String,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    colors: YDSelectionColors = YDSelectionDefaults.selectionColors(),
    enabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }

    // Thumb position — bouncy spring for physical feel.
    val thumbProgress by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow,
        ),
        label = "switchThumb",
    )

    // Track fill — steady tween so the color fully reaches 0 or 1 regardless of spring overshoot.
    val fillProgress by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        animationSpec = tween(durationMillis = 200),
        label = "switchFill",
    )

    val activeTrack = colors.activeColor(enabled = enabled)
    val inactiveTrack = colors.inactiveColor(enabled = enabled)
    val thumbColor = colors.activeContentColor(enabled = enabled)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = rememberYDRipple(),
                enabled = enabled,
                role = Role.Switch,
                onClick = { onCheckedChange(!checked) },
            )
            .padding(horizontal = spacings.large, vertical = spacings.medium),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        YDText(
            text = text,
            style = typography.mdRegular,
            modifier = Modifier.weight(weight = 1f),
        )
        Spacer(modifier = Modifier.width(width = spacings.medium))
        Canvas(
            modifier = Modifier
                .width(width = TrackWidth)
                .height(height = TrackHeight),
        ) {
            drawSwitch(
                thumbProgress = thumbProgress,
                fillProgress = fillProgress,
                activeTrackColor = activeTrack,
                inactiveTrackColor = inactiveTrack,
                thumbColor = thumbColor,
            )
        }
    }
}

private fun DrawScope.drawSwitch(
    thumbProgress: Float,
    fillProgress: Float,
    activeTrackColor: Color,
    inactiveTrackColor: Color,
    thumbColor: Color,
) {
    val trackW = size.width
    val trackH = size.height
    val trackCorner = CornerRadius(trackH / 2f)

    val thumbRadiusPx = ThumbRadius.toPx()
    // Keep the circle center within the pill so it never breaks out of the track boundary.
    val thumbMinCenterX = thumbRadiusPx + (trackH / 2f - thumbRadiusPx)
    val thumbMaxCenterX = trackW - thumbRadiusPx - (trackH / 2f - thumbRadiusPx)
    val thumbCenterX = lerp(thumbMinCenterX, thumbMaxCenterX, thumbProgress.coerceIn(0f, 1f))
    val thumbCenterY = trackH / 2f

    // Track — full cross-fade so it reaches 100% active or inactive color.
    drawRoundRect(
        color = lerp(inactiveTrackColor, activeTrackColor, fillProgress),
        size = Size(trackW, trackH),
        cornerRadius = trackCorner,
    )

    // Thumb — circle contained within the track boundaries.
    drawCircle(
        color = thumbColor,
        radius = thumbRadiusPx,
        center = Offset(thumbCenterX, thumbCenterY),
    )
}

@PhonePreview
@Composable
private fun SwitchPreview() = YDPreview {
    Column(
        modifier = Modifier.padding(all = spacings.medium),
        verticalArrangement = Arrangement.spacedBy(spacings.tiny),
    ) {
        var checked by remember { mutableStateOf(value = false) }
        YDSwitch(checked = checked, text = "Dark mode", onCheckedChange = { checked = it })
        YDSwitch(checked = true, text = "Notifications on", onCheckedChange = {})
        YDSwitch(checked = false, text = "Disabled off", onCheckedChange = {}, enabled = false)
        YDSwitch(checked = true, text = "Disabled on", onCheckedChange = {}, enabled = false)
    }
}
