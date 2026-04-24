@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.util


import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import de.yanosdev.annotation.YDRevisionIn

internal val YDPredictiveBackEasing: Easing = CubicBezierEasing(0.1f, 0.1f, 0f, 1f)

internal object YDPredictiveBack {
    fun transform(progress: Float) = YDPredictiveBackEasing.transform(progress)
}