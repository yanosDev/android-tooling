@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.util.ProvideTonalElevation
import de.yanosdev.styleguide.theme.components.atoms.util.rememberYDRipple
import de.yanosdev.styleguide.theme.components.atoms.util.ydShadow
import de.yanosdev.styleguide.theme.foundations.semantics.YDShadow
import de.yanosdev.styleguide.theme.foundations.semantics.YDTonal
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.themes.contentColorFor
import de.yanosdev.styleguide.theme.util.ydMinTouchTargetSize

/**
 *
 * 1) Clipping to the specified [shape].
 *
 * 2) Drawing the [border].
 *
 * 3) Drawing a background [color].
 *
 * 4) Setting the correct [contentColor] for elements on the surface.
 *
 * Derived from M3 Surface.
 */
@Composable
internal fun YDSurface(
    modifier: Modifier = Modifier,
    border: BorderStroke? = null,
    color: Color = colorScheme.surfaceContainerDefault,
    contentColor: Color = contentColorFor(backgroundColor = color),
    shadowElevation: YDShadow = YDShadow.Zero,
    shape: Shape = RectangleShape,
    tonalElevation: YDTonal = YDTonal.Zero,
    content: @Composable () -> Unit
) {
    ProvideTonalElevation(
        tonalElevation = tonalElevation,
        backgroundColor = color,
        contentColor = contentColor,
    ) { backgroundColor ->
        Box(
            modifier = modifier
                .ydSurface(
                    shape = shape,
                    backgroundColor = backgroundColor,
                    border = border,
                    shadowElevation = shadowElevation
                )
                .semantics(mergeDescendants = false) {}
                .pointerInput(Unit) {},
            propagateMinConstraints = true,
            content = { content() }
        )

    }
}


/**
 *
 * 1) Clipping to the specified [shape].
 *
 * 2) Drawing the [border].
 *
 * 3) Drawing a background [color].
 *
 * 4) Setting the correct [contentColor] for elements on the surface.
 *
 * This version is clickable.
 *
 * Derived from Material3 Surface.
 */
@Composable
@NonRestartableComposable
fun C24Surface(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    border: BorderStroke? = null,
    color: Color = colorScheme.surfaceContainerDefault,
    contentColor: Color = contentColorFor(color),
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shadowElevation: YDShadow = YDShadow.Zero,
    shape: Shape = RectangleShape,
    tonalElevation: YDTonal = YDTonal.Zero,
    onLongClick: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    ProvideTonalElevation(
        tonalElevation = tonalElevation,
        backgroundColor = color,
        contentColor = contentColor
    ) { backgroundColor ->
        Box(
            modifier = modifier
                .ydMinTouchTargetSize()
                .ydSurface(
                    shape = shape,
                    backgroundColor = backgroundColor,
                    border = border,
                    shadowElevation = shadowElevation,
                )
                .combinedClickable(
                    interactionSource = interactionSource,
                    indication = rememberYDRipple(),
                    enabled = enabled,
                    role = Role.Button,
                    onClick = onClick,
                    onLongClick = onLongClick
                ),
            propagateMinConstraints = true
        ) {
            content()
        }
    }
}

@Composable
private fun Modifier.ydSurface(
    backgroundColor: Color,
    border: BorderStroke?,
    shadowElevation: YDShadow,
    shape: Shape,
) = this
    .ydShadow(
        shadowElevation = shadowElevation,
        shape = shape,
        clip = false,
    )
    .then(if (border != null) Modifier.border(border, shape) else Modifier)
    .background(color = backgroundColor, shape = shape)
    .clip(shape)