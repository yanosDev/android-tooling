@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.icon

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toolingGraphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIconDefaults.mediumSize
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDContentColor


@Composable
internal fun YDIcon(
    contentDescription: String?,
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    size: Dp = mediumSize,
    tint: Color = LocalYDContentColor.current
) = YDIcon(
    painter = rememberVectorPainter(imageVector),
    contentDescription = contentDescription,
    modifier = modifier,
    tint = tint,
    size = size
)

@Composable
internal fun YDIcon(
    bitmap: ImageBitmap,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    size: Dp = mediumSize,
    tint: Color = LocalYDContentColor.current
) = YDIcon(
    painter = remember(bitmap) { BitmapPainter(bitmap) },
    contentDescription = contentDescription,
    modifier = modifier,
    tint = tint,
    size = size
)

@Composable
internal fun YDIcon(
    contentDescription: String?,
    painter: Painter,
    modifier: Modifier = Modifier,
    size: Dp = mediumSize,
    tint: Color = LocalYDContentColor.current
) {
    val colorFilter = if (tint == Color.Unspecified) null else ColorFilter.tint(tint)
    val semantics = if (contentDescription != null) {
        Modifier.semantics {
            this.contentDescription = contentDescription
            this.role = Role.Image
        }
    } else {
        Modifier
    }

    Box(
        modifier
            .toolingGraphicsLayer()
            .defaultSizeFor(
                size = size,
                painter = painter
            )
            .paint(
                painter = painter,
                colorFilter = colorFilter,
                contentScale = ContentScale.Fit
            )
            .then(semantics)
    )
}

@Composable
internal fun YDToggleableIcon(
    contentDescription: String?,
    imageVector: ImageVector,
    toggledOn: Boolean,
    modifier: Modifier = Modifier,
    iconSize: Dp = mediumSize,
    tint: Color = LocalYDContentColor.current
) = YDToggleableIcon(
    painter = rememberVectorPainter(imageVector),
    contentDescription = contentDescription,
    toggledOn = toggledOn,
    modifier = modifier,
    tint = tint,
    iconSize = iconSize
)


@Composable
internal fun YDToggleableIcon(
    contentDescription: String?,
    painter: Painter,
    toggledOn: Boolean,
    modifier: Modifier = Modifier,
    iconSize: Dp = mediumSize,
    tint: Color = LocalYDContentColor.current
) {
    val colorFilter = if (tint == Color.Unspecified) null else ColorFilter.tint(tint)
    val semantics = if (contentDescription != null) {
        Modifier.semantics {
            this.contentDescription = contentDescription
            this.role = Role.Image
        }
    } else {
        Modifier
    }
    val progress by animateFloatAsState(
        targetValue = if (toggledOn) 0f else 1f,
        label = "Toggleable icon strike-through progress"
    )

    Canvas(
        modifier = modifier
            .defaultSizeFor(size = iconSize, painter = painter)
            .then(semantics)
    ) {
        drawStrikeThroughIcon(painter = painter, tint = tint, colorFilter = colorFilter, progress = progress)
    }
}

private fun Modifier.defaultSizeFor(size: Dp, painter: Painter) = composed {
    this.then(
        when {
            size.isSpecified -> Modifier.size(size)
            painter.intrinsicSize == Size.Unspecified || painter.intrinsicSize.isInfinite() -> Modifier.size(mediumSize)
            else -> Modifier
        }
    )
}

private fun Size.isInfinite() = width.isInfinite() && height.isInfinite()

private fun DrawScope.drawStrikeThroughIcon(
    painter: Painter,
    tint: Color,
    colorFilter: ColorFilter?,
    progress: Float,
) {
    val strokeWidth = 1.dp.toPx()
    val strokeOutline = 1.5.dp.toPx()
    val clipCornerRadius = CornerRadius(x = strokeOutline, y = strokeOutline)
    val clipPath = Path().apply {
        addRoundRect(
            RoundRect(
                left = 0f,
                top = size.height / 2 - strokeOutline,
                right = (size.width * progress + strokeOutline).coerceAtMost(size.width),
                bottom = size.height / 2 + strokeOutline,
                topLeftCornerRadius = clipCornerRadius,
                topRightCornerRadius = clipCornerRadius,
                bottomRightCornerRadius = clipCornerRadius,
                bottomLeftCornerRadius = clipCornerRadius
            )
        )
    }
    val strikeThroughAngle = 45f
    rotate(degrees = strikeThroughAngle) {
        clipPath(
            path = clipPath,
            clipOp = ClipOp.Difference
        ) {
            rotate(degrees = -strikeThroughAngle) {
                with(painter) { draw(size = size, colorFilter = colorFilter) }
            }
        }
        if (progress > 0f) {
            drawLine(
                color = tint,
                start = Offset(0f, size.height / 2),
                end = Offset(size.width * progress, size.height / 2),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )
        }
    }
}
