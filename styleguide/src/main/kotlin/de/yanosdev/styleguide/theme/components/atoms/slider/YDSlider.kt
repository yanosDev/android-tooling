@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.slider

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.setProgress
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview
import kotlin.math.roundToInt

private val TrackHeight = 4.dp
private val ThumbRadius = 10.dp
private val TickRadius = 3.dp
private val MinTouchTarget = 48.dp

/**
 * A horizontal slider for selecting a value within [valueRange].
 *
 * The thumb is drawn on Canvas and draggable across the full width of the composable. When
 * [steps] is greater than zero the slider is discrete: the thumb snaps to `steps + 2` evenly
 * spaced positions (including the endpoints) and tick marks are drawn along the track.
 *
 * @param value Current value. Must be within [valueRange]; values outside are coerced.
 * @param onValueChange Called continuously while the user drags the thumb.
 * @param modifier Modifier applied to the slider.
 * @param colors Track, tick, and thumb colors.
 * @param enabled Whether the slider responds to interaction.
 * @param steps Number of discrete steps between [valueRange] start and end (exclusive).
 *   0 means continuous. Passing 3 gives 5 discrete positions: start, 3 intermediates, end.
 * @param valueRange The range of values the slider can take.
 */
@Composable
fun YDSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    colors: YDSliderColors = YDSliderDefaults.sliderColors(),
    enabled: Boolean = true,
    steps: Int = 0,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
) {
    val coercedValue = value.coerceIn(valueRange.start, valueRange.endInclusive)
    val fraction = if (valueRange.endInclusive == valueRange.start) 0f
    else (coercedValue - valueRange.start) / (valueRange.endInclusive - valueRange.start)

    var isDragging by remember { mutableStateOf(value = false) }

    val activeTrackColor = colors.activeTrackColor(enabled = enabled)
    val inactiveTrackColor = colors.inactiveTrackColor(enabled = enabled)
    val thumbColor = colors.thumbColor(enabled = enabled)
    val activeTickColor = colors.activeTickColor(enabled = enabled)
    val inactiveTickColor = colors.inactiveTickColor(enabled = enabled)

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = MinTouchTarget)
            .semantics(mergeDescendants = true) {
                progressBarRangeInfo = ProgressBarRangeInfo(
                    current = coercedValue,
                    range = valueRange,
                    steps = steps,
                )
                setProgress { targetValue ->
                    if (!enabled) return@setProgress false
                    onValueChange(snapToStep(value = targetValue, steps = steps, valueRange = valueRange))
                    true
                }
            }
            .pointerInput(enabled, steps, valueRange) {
                if (!enabled) return@pointerInput
                val thumbPx = ThumbRadius.toPx()
                val trackStart = thumbPx
                val trackLen = size.width - 2f * thumbPx

                awaitEachGesture {
                    val down = awaitFirstDown(requireUnconsumed = false)
                    isDragging = true
                    val rawFraction = ((down.position.x - trackStart) / trackLen).coerceIn(0f, 1f)
                    onValueChange(
                        snapToStep(
                            value = valueRange.start + rawFraction * (valueRange.endInclusive - valueRange.start),
                            steps = steps,
                            valueRange = valueRange,
                        )
                    )
                    do {
                        val event = awaitPointerEvent()
                        val change = event.changes.firstOrNull() ?: break
                        if (change.pressed) {
                            change.consume()
                            val f = ((change.position.x - trackStart) / trackLen).coerceIn(0f, 1f)
                            onValueChange(
                                snapToStep(
                                    value = valueRange.start + f * (valueRange.endInclusive - valueRange.start),
                                    steps = steps,
                                    valueRange = valueRange,
                                )
                            )
                        }
                    } while (event.changes.any { it.pressed })
                    isDragging = false
                }
            },
    ) {
        drawSlider(
            fraction = fraction,
            steps = steps,
            isDragging = isDragging && enabled,
            activeTrackColor = activeTrackColor,
            inactiveTrackColor = inactiveTrackColor,
            activeTickColor = activeTickColor,
            inactiveTickColor = inactiveTickColor,
            thumbColor = thumbColor,
        )
    }
}

private fun DrawScope.drawSlider(
    fraction: Float,
    steps: Int,
    isDragging: Boolean,
    activeTrackColor: Color,
    inactiveTrackColor: Color,
    activeTickColor: Color,
    inactiveTickColor: Color,
    thumbColor: Color,
) {
    val thumbPx = ThumbRadius.toPx()
    val trackH = TrackHeight.toPx()
    val trackStartX = thumbPx
    val trackEndX = size.width - thumbPx
    val trackLen = trackEndX - trackStartX
    val centerY = size.height / 2f
    val thumbX = trackStartX + fraction * trackLen

    // Inactive (background) track
    drawRoundRect(
        color = inactiveTrackColor,
        topLeft = Offset(x = trackStartX, y = centerY - trackH / 2f),
        size = Size(width = trackLen, height = trackH),
        cornerRadius = CornerRadius(x = trackH / 2f),
    )

    // Active (filled) track
    if (fraction > 0f) {
        drawRoundRect(
            color = activeTrackColor,
            topLeft = Offset(x = trackStartX, y = centerY - trackH / 2f),
            size = Size(width = fraction * trackLen, height = trackH),
            cornerRadius = CornerRadius(x = trackH / 2f),
        )
    }

    // Tick marks for discrete steps — (steps + 2) positions including endpoints
    if (steps > 0) {
        val tickPx = TickRadius.toPx()
        val totalPositions = steps + 1
        for (i in 0..totalPositions) {
            val tickFraction = i.toFloat() / totalPositions
            val tickX = trackStartX + tickFraction * trackLen
            drawCircle(
                color = if (tickFraction <= fraction + 0.001f) activeTickColor else inactiveTickColor,
                radius = tickPx,
                center = Offset(x = tickX, y = centerY),
            )
        }
    }

    // Drag ripple
    if (isDragging) {
        drawCircle(
            color = thumbColor.copy(alpha = 0.12f),
            radius = thumbPx * 2.5f,
            center = Offset(x = thumbX, y = centerY),
        )
    }

    // Thumb
    drawCircle(
        color = thumbColor,
        radius = thumbPx,
        center = Offset(x = thumbX, y = centerY),
    )
}

private fun snapToStep(
    value: Float,
    steps: Int,
    valueRange: ClosedFloatingPointRange<Float>,
): Float {
    if (steps <= 0) return value.coerceIn(valueRange.start, valueRange.endInclusive)
    val totalIntervals = steps + 1
    val fraction = (value - valueRange.start) / (valueRange.endInclusive - valueRange.start)
    val snapped = (fraction * totalIntervals).roundToInt().toFloat() / totalIntervals
    return (valueRange.start + snapped.coerceIn(0f, 1f) * (valueRange.endInclusive - valueRange.start))
        .coerceIn(valueRange.start, valueRange.endInclusive)
}

@PhonePreview
@Composable
private fun SliderPreview() = YDPreview {
    var continuous by remember { mutableStateOf(value = 0.4f) }
    var discrete by remember { mutableStateOf(value = 0f) }

    Column(
        modifier = Modifier.padding(all = spacings.large),
        verticalArrangement = Arrangement.spacedBy(space = spacings.medium),
    ) {
        YDSlider(value = continuous, onValueChange = { continuous = it })
        YDSlider(value = discrete, onValueChange = { discrete = it }, steps = 4)
        YDSlider(value = 0.6f, onValueChange = {}, enabled = false)
    }
}
