@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.util


import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import de.yanosdev.annotation.YDRevisionIn

/**
 * Derived from Material 3 PredictiveBack.android.
 */
val YDPredictiveBackEasing: Easing = CubicBezierEasing(0.1f, 0.1f, 0f, 1f)

/**
 * Derived from Material 3 PredictiveBack.android.
 */
object YDPredictiveBack {
    fun transform(progress: Float) = YDPredictiveBackEasing.transform(progress)
}