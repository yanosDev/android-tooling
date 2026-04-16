@file:YDRevisionIn(implementedAt = "2026-04-16", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.foundations.semantics

import androidx.annotation.FloatRange
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.packFloats
import androidx.compose.ui.util.unpackFloat1
import androidx.compose.ui.util.unpackFloat2
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.foundations.token.YDShadowTokens

@Immutable
@JvmInline
value class YDShadow internal constructor(internal val packedValue: Long) {
    @Stable
    val elevation: Dp
        get() = unpackFloat1(packedValue).dp

    @Stable
    val progress: Float
        get() = unpackFloat2(packedValue)

    @Stable
    fun animated(@FloatRange(from = 0.0, to = 1.0) progress: Float) = YDShadow(
        packedValue = packFloats(val1 = elevation.value, val2 = progress)
    )

    companion object Companion {

        @Stable
        val Zero = YDShadow(elevation = YDShadowTokens.Zero)
    }
}

@Stable
internal fun YDShadow(
    elevation: Dp,
    progress: Float = 1.0f,
) = YDShadow(
    packedValue = packFloats(
        val1 = elevation.value,
        val2 = progress
    )
)

@Immutable
data class YDShadows(
    val medium: YDShadow,
)

@Stable
internal val DefaultYDShadows = YDShadows(
    medium = YDShadow(elevation = YDShadowTokens.medium),
)

internal val LocalYDShadowEnabled = compositionLocalOf { false }

internal val LocalYDShadows = staticCompositionLocalOf { DefaultYDShadows }