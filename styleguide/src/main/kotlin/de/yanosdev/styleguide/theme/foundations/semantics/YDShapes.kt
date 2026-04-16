@file:YDRevisionIn(implementedAt = "2026-04-16", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.foundations.semantics

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn

@Immutable
internal data class YDShapes(
    val extraLarge: CornerBasedShape,
    val large: CornerBasedShape,
    val medium: CornerBasedShape,
    val small: CornerBasedShape
)

@Stable
internal val DefaultYDShapes = YDShapes(
    small = RoundedCornerShape(2.dp),
    medium = RoundedCornerShape(6.dp),
    large = RoundedCornerShape(8.dp),
    extraLarge = RoundedCornerShape(20.dp),
)

internal val LocalYDShapes = staticCompositionLocalOf { DefaultYDShapes }