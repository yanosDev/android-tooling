@file:YDRevisionIn(implementedAt = "2026-04-16", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.foundations.semantics

import androidx.compose.runtime.staticCompositionLocalOf
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.foundations.token.YDAlphas

internal data class YDRippleAlpha(
    val draggedAlpha: Float,
    val focusedAlpha: Float,
    val hoveredAlpha: Float,
    val pressedAlpha: Float
)

private val DefaultYDRippleAlpha = YDRippleAlpha(
    draggedAlpha = YDAlphas.Drag,
    focusedAlpha = YDAlphas.Focus,
    hoveredAlpha = YDAlphas.Hover,
    pressedAlpha = YDAlphas.Press
)

internal val LocalYDRippleAlpha = staticCompositionLocalOf { DefaultYDRippleAlpha }