@file:YDRevisionIn(implementedAt = "2026-04-17", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.foundations.semantics

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import de.yanosdev.annotation.YDRevisionIn

@Immutable
@JvmInline
value class YDTonal internal constructor(internal val value: Int) {
    val isZero
        get() = value == 0

    operator fun plus(other: YDTonal) = YDTonal(
        value = value + other.value,
    )

    companion object Companion {

        @Stable
        val Zero = YDTonal(value = 0)
    }
}

@Immutable
data class YDTonals(
    val level1: YDTonal,
)

@Stable
internal val DefaultYDTonals = YDTonals(
    level1 = YDTonal(value = 1),
)

internal val LocalYDTonalEnabled = compositionLocalOf { false }

internal val LocalAbsoluteYDTonal = compositionLocalOf { YDTonal.Zero }

internal val LocalYDTonals = staticCompositionLocalOf { DefaultYDTonals }
