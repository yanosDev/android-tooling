@file:YDRevisionIn(implementedAt = "2026-04-22", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.progress

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.util.PhonePreview

@Composable
fun YDLinearProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = YDProgressIndicatorDefaults.linearColor,
    trackColor: Color = YDProgressIndicatorDefaults.linearTrackColor,
) {
    Canvas(
        modifier
            .progressSemantics(progress)
            .size(LinearIndicatorWidth, LinearIndicatorHeight)
    ) {
        val strokeWidth = size.height
        drawLinearIndicatorTrack(color = trackColor, strokeWidth = strokeWidth)
        drawLinearIndicator(startFraction = 0f, endFraction = progress, color = color, strokeWidth = strokeWidth)
    }
}

private fun DrawScope.drawLinearIndicatorTrack(
    color: Color,
    strokeWidth: Float
) = drawLinearIndicator(startFraction = 0f, endFraction = 1f, color = color, strokeWidth = strokeWidth)

private fun DrawScope.drawLinearIndicator(
    startFraction: Float,
    endFraction: Float,
    color: Color,
    strokeWidth: Float
) {
    val width = size.width
    val height = size.height
    val yOffset = height / 2

    val isLtr = layoutDirection == LayoutDirection.Ltr
    val barStart = (if (isLtr) startFraction else 1f - endFraction) * width
    val barEnd = (if (isLtr) endFraction else 1f - startFraction) * width

    drawLine(color, Offset(barStart, yOffset), Offset(barEnd, yOffset), strokeWidth)
}

private val LinearIndicatorWidth = 240.dp
private val LinearIndicatorHeight = 4.dp

@PhonePreview
@Composable
private fun Preview() {
    YDLinearProgressIndicator(
        progress = 0.6f
    )
}