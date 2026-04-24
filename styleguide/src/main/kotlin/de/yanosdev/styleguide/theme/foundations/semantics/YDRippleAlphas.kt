@file:YDRevisionIn(implementedAt = "2026-04-16", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.foundations.semantics

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.foundations.token.YDAlphaTokens

@Immutable
data class YDRippleAlpha(
    val draggedAlpha: Float,
    val focusedAlpha: Float,
    val hoveredAlpha: Float,
    val pressedAlpha: Float
)

@Stable
private val DefaultYDRippleAlpha = YDRippleAlpha(
    draggedAlpha = YDAlphaTokens.Drag,
    focusedAlpha = YDAlphaTokens.Focus,
    hoveredAlpha = YDAlphaTokens.Hover,
    pressedAlpha = YDAlphaTokens.Press
)

internal val LocalYDRippleAlpha = staticCompositionLocalOf { DefaultYDRippleAlpha }