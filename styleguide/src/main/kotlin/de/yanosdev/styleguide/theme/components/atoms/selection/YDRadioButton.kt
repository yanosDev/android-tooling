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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
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

private val IndicatorSize = 22.dp
private val StrokeWidthDp = 2.dp
private const val DotFraction = 0.45f

/**
 * Labeled radio button row. The entire row is the touch target.
 *
 * Radio buttons should be used in groups where exactly one is selected at a time. Caller is
 * responsible for ensuring mutual exclusivity by updating [selected] in [onClick].
 *
 * The indicator is drawn on Canvas. The ring color cross-fades on a tween; the inner dot pops
 * in with a bouncy spring that gives a satisfying tactile feel.
 *
 * @param selected Whether this radio button is currently selected.
 * @param text Label shown to the left of the indicator.
 * @param onClick Called when the row is tapped. Caller must update [selected].
 * @param modifier Modifier applied to the full-width row.
 * @param colors Colors for active, inactive, and disabled states.
 * @param enabled Whether the row responds to taps.
 */
@Composable
fun YDRadioButton(
    selected: Boolean,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: YDSelectionColors = YDSelectionDefaults.selectionColors(),
    enabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }

    val dotProgress by animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium,
        ),
        label = "radioDot",
    )
    val ringColor by animateColorAsState(
        targetValue = if (selected) colors.activeColor(enabled = enabled) else colors.inactiveColor(enabled = enabled),
        animationSpec = tween(durationMillis = 150),
        label = "radioRing",
    )
    val dotColor = colors.activeColor(enabled = enabled)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = rememberYDRipple(),
                enabled = enabled,
                role = Role.RadioButton,
                onClick = onClick,
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
            drawRadio(
                dotProgress = dotProgress,
                ringColor = ringColor,
                dotColor = dotColor,
            )
        }
    }
}

private fun DrawScope.drawRadio(
    dotProgress: Float,
    ringColor: Color,
    dotColor: Color,
) {
    val s = size.minDimension
    val strokePx = StrokeWidthDp.toPx()
    val center = Offset(s / 2f, s / 2f)
    val outerRadius = s / 2f - strokePx / 2f

    // Outer ring — color animates between active and inactive
    drawCircle(
        color = ringColor,
        radius = outerRadius,
        center = center,
        style = Stroke(width = strokePx),
    )

    // Inner dot — scales in with spring (clamp to avoid artifact from spring overshoot)
    val clamped = dotProgress.coerceIn(0f, 1f)
    if (clamped > 0f) {
        drawCircle(
            color = dotColor,
            radius = outerRadius * DotFraction * clamped,
            center = center,
        )
    }
}

@PhonePreview
@Composable
private fun RadioButtonPreview() = YDPreview {
    val options = listOf("Option A", "Option B", "Option C")
    Column(
        modifier = Modifier.padding(all = spacings.medium),
        verticalArrangement = Arrangement.spacedBy(spacings.tiny),
    ) {
        var selected by remember { mutableIntStateOf(value = 0) }
        options.forEachIndexed { index, label ->
            YDRadioButton(
                selected = selected == index,
                text = label,
                onClick = { selected = index },
            )
        }
        YDRadioButton(selected = false, text = "Disabled", onClick = {}, enabled = false)
        YDRadioButton(selected = true, text = "Disabled selected", onClick = {}, enabled = false)
    }
}
