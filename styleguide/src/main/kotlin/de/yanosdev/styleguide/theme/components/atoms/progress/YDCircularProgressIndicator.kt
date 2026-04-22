@file:YDRevisionIn(implementedAt = "2026-04-22", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.progress

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.max

@Composable
fun YDCircularProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = YDProgressIndicatorDefaults.circularColor,
    strokeWidth: Dp = YDProgressIndicatorDefaults.CircularStrokeWidth
) {
    val stroke = with(LocalDensity.current) {
        Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Butt)
    }
    Canvas(
        modifier
            .progressSemantics(progress)
            .size(CircularIndicatorDiameter)
    ) {
        // Start at 12 o'clock
        val startAngle = 270f
        val sweep = progress * 360f
        drawDeterminateCircularIndicator(
            startAngle = startAngle,
            sweep = sweep,
            color = color,
            stroke = stroke
        )
    }
}

@Composable
fun YDCircularProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color = YDProgressIndicatorDefaults.circularColor,
    strokeWidth: Dp = YDProgressIndicatorDefaults.CircularStrokeWidth
) {
    val stroke = with(LocalDensity.current) {
        Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Square)
    }

    val transition = rememberInfiniteTransition()
    // The current rotation around the circle, so we know where to start the rotation from
    val currentRotation = transition.animateValue(
        0,
        RotationsPerCycle,
        Int.VectorConverter,
        infiniteRepeatable(
            animation = tween(
                durationMillis = RotationDuration * RotationsPerCycle,
                easing = LinearEasing
            )
        )
    )
    // How far forward (degrees) the base point should be from the start point
    val baseRotation = transition.animateFloat(
        0f,
        BaseRotationAngle,
        infiniteRepeatable(
            animation = tween(
                durationMillis = RotationDuration,
                easing = LinearEasing
            )
        )
    )
    // How far forward (degrees) both the head and tail should be from the base point
    val endAngle = transition.animateFloat(
        0f,
        JumpRotationAngle,
        infiniteRepeatable(
            animation = keyframes {
                durationMillis = HeadAndTailAnimationDuration + HeadAndTailDelayDuration
                0f at 0 using CircularEasing
                JumpRotationAngle at HeadAndTailAnimationDuration
            }
        )
    )
    val startAngle = transition.animateFloat(
        0f,
        JumpRotationAngle,
        infiniteRepeatable(
            animation = keyframes {
                durationMillis = HeadAndTailAnimationDuration + HeadAndTailDelayDuration
                0f at HeadAndTailDelayDuration using CircularEasing
                JumpRotationAngle at durationMillis
            }
        )
    )
    Canvas(
        modifier
            .progressSemantics()
            .size(CircularIndicatorDiameter)
    ) {
        val currentRotationAngleOffset = (currentRotation.value * RotationAngleOffset) % 360f

        // How long a line to draw using the start angle as a reference point
        val sweep = abs(endAngle.value - startAngle.value)

        // Offset by the constant offset and the per rotation offset
        val offset = StartAngleOffset + currentRotationAngleOffset + baseRotation.value
        drawIndeterminateCircularIndicator(
            startAngle = startAngle.value + offset,
            strokeWidth = strokeWidth,
            sweep = sweep,
            color = color,
            stroke = stroke
        )
    }
}

private fun DrawScope.drawCircularIndicator(
    startAngle: Float,
    sweep: Float,
    color: Color,
    stroke: Stroke
) {
    // To draw this circle we need a rect with edges that line up with the midpoint of the stroke.
    // To do this we need to remove half the stroke width from the total diameter for both sides.
    val diameterOffset = stroke.width / 2
    val arcDimen = size.width - 2 * diameterOffset
    drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = sweep,
        useCenter = false,
        topLeft = Offset(diameterOffset, diameterOffset),
        size = Size(arcDimen, arcDimen),
        style = stroke
    )
}


private fun DrawScope.drawDeterminateCircularIndicator(
    startAngle: Float,
    sweep: Float,
    color: Color,
    stroke: Stroke
) = drawCircularIndicator(startAngle, sweep, color, stroke)

private fun DrawScope.drawIndeterminateCircularIndicator(
    startAngle: Float,
    strokeWidth: Dp,
    sweep: Float,
    color: Color,
    stroke: Stroke
) {
    // Length of arc is angle * radius
    // Angle (radians) is length / radius
    // The length should be the same as the stroke width for calculating the min angle
    val squareStrokeCapOffset =
        (180.0 / PI).toFloat() * (strokeWidth / (CircularIndicatorDiameter / 2)) / 2f

    // Adding a square stroke cap draws half the stroke width behind the start point, so we want to
    // move it forward by that amount so the arc visually appears in the correct place
    val adjustedStartAngle = startAngle + squareStrokeCapOffset

    // When the start and end angles are in the same place, we still want to draw a small sweep, so
    // the stroke caps get added on both ends and we draw the correct minimum length arc
    val adjustedSweep = max(sweep, 0.1f)

    drawCircularIndicator(
        startAngle = adjustedStartAngle,
        sweep = adjustedSweep,
        color = color,
        stroke = stroke
    )
}


private val CircularSize = 48.dp
private val CircularIndicatorDiameter = CircularSize - YDProgressIndicatorDefaults.CircularStrokeWidth * 2

// Indeterminate circular indicator transition specs

// The animation comprises of 5 rotations around the circle forming a 5 pointed star.
// After the 5th rotation, we are back at the beginning of the circle.
private const val RotationsPerCycle = 5

// Each rotation is 1 and 1/3 seconds, but 1332ms divides more evenly
private const val RotationDuration = 1332

// How far the base point moves around the circle
private const val BaseRotationAngle = 286f


// How far the head and tail should jump forward during one rotation past the base point
private const val JumpRotationAngle = 290f

// The head animates for the first half of a rotation, then is static for the second half
// The tail is static for the first half and then animates for the second half
private const val HeadAndTailAnimationDuration = (RotationDuration * 0.5).toInt()

private const val HeadAndTailDelayDuration = HeadAndTailAnimationDuration

// The easing for the head and tail jump
private val CircularEasing = CubicBezierEasing(0.4f, 0f, 0.2f, 1f)

// Each rotation we want to offset the start position by this much, so we continue where
// the previous rotation ended. This is the maximum angle covered during one rotation.
private const val RotationAngleOffset = (BaseRotationAngle + JumpRotationAngle) % 360f

// When the rotation is at its beginning (0 or 360 degrees) we want it to be drawn at 12 o clock,
// which means 270 degrees when drawing.
private const val StartAngleOffset = -90f


@PhonePreview
@Composable
private fun Preview() = YDPreview {
    YDCircularProgressIndicator()
}