@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.selection

import androidx.compose.animation.animateColorAsState
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.atoms.util.rememberYDRipple
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview
import kotlin.math.sqrt

private val IndicatorSize = 22.dp
private val StrokeWidthDp = 2.dp
private const val CornerFraction = 0.18f

/**
 * Labeled checkbox row. The entire row is the touch target.
 *
 * The indicator is drawn on Canvas — not a Material3 component. The checkmark draws via a
 * dash-path animation driven by a bouncy spring; the fill cross-fades on a fast tween so the
 * two motions feel distinct.
 *
 * @param checked Whether the checkbox is currently checked.
 * @param text Label shown to the left of the indicator.
 * @param onCheckedChange Called with the new value when the row is tapped.
 * @param modifier Modifier applied to the full-width row.
 * @param colors Colors for active, inactive, and disabled states.
 * @param enabled Whether the row responds to taps.
 */
@Composable
fun YDCheckbox(
    checked: Boolean,
    text: String,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    colors: YDSelectionColors = YDSelectionDefaults.selectionColors(),
    enabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }

    val fillProgress by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        animationSpec = tween(durationMillis = 130),
        label = "checkboxFill",
    )
    val checkProgress by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium,
        ),
        label = "checkboxMark",
    )
    val borderColor by animateColorAsState(
        targetValue = if (checked) colors.activeColor(enabled = enabled) else colors.inactiveColor(enabled = enabled),
        animationSpec = tween(durationMillis = 130),
        label = "checkboxBorder",
    )
    val fillColor = colors.activeColor(enabled = enabled)
    val markColor = colors.activeContentColor(enabled = enabled)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = rememberYDRipple(),
                enabled = enabled,
                role = Role.Checkbox,
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
        Canvas(modifier = Modifier.size(size = IndicatorSize)) {
            drawCheckbox(
                fillProgress = fillProgress,
                checkProgress = checkProgress,
                borderColor = borderColor,
                fillColor = fillColor,
                markColor = markColor,
            )
        }
    }
}

private fun DrawScope.drawCheckbox(
    fillProgress: Float,
    checkProgress: Float,
    borderColor: Color,
    fillColor: Color,
    markColor: Color,
) {
    val s = size.minDimension
    val strokePx = StrokeWidthDp.toPx()
    val corner = CornerRadius(s * CornerFraction)
    val halfStroke = strokePx / 2f

    // Background fill — fast fade in/out
    if (fillProgress > 0f) {
        drawRoundRect(
            color = fillColor.copy(alpha = fillProgress),
            cornerRadius = corner,
        )
    }

    // Border — color tracks checked state
    drawRoundRect(
        color = borderColor,
        topLeft = Offset(halfStroke, halfStroke),
        size = Size(s - strokePx, s - strokePx),
        cornerRadius = corner,
        style = Stroke(width = strokePx),
    )

    // Checkmark via dash-path (spring-driven, clamp to avoid visual artifacts from overshoot)
    val clamped = checkProgress.coerceIn(0f, 1f)
    if (clamped > 0f) {
        val sx = s * 0.22f
        val sy = s * 0.52f
        val mx = s * 0.40f
        val my = s * 0.72f
        val ex = s * 0.78f
        val ey = s * 0.28f

        val totalLen = sqrt((mx - sx) * (mx - sx) + (my - sy) * (my - sy)) +
                sqrt((ex - mx) * (ex - mx) + (ey - my) * (ey - my))

        drawPath(
            path = Path().apply {
                moveTo(sx, sy)
                lineTo(mx, my)
                lineTo(ex, ey)
            },
            color = markColor,
            style = Stroke(
                width = strokePx * 1.25f,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round,
                pathEffect = PathEffect.dashPathEffect(
                    intervals = floatArrayOf(totalLen, totalLen),
                    phase = totalLen * (1f - clamped),
                ),
            ),
        )
    }
}

@PhonePreview
@Composable
private fun CheckboxPreview() = YDPreview {
    Column(
        modifier = Modifier.padding(all = spacings.medium),
        verticalArrangement = Arrangement.spacedBy(spacings.tiny),
    ) {
        var checked by remember { mutableStateOf(value = false) }
        YDCheckbox(checked = checked, text = "Enable notifications", onCheckedChange = { checked = it })
        YDCheckbox(checked = true, text = "Already checked", onCheckedChange = {})
        YDCheckbox(checked = false, text = "Disabled unchecked", onCheckedChange = {}, enabled = false)
        YDCheckbox(checked = true, text = "Disabled checked", onCheckedChange = {}, enabled = false)
    }
}
