@file:YDRevisionIn(implementedAt = "2026-04-16", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.util

import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDContentColor

@Composable
internal fun rememberYDRipple(
    bounded: Boolean = true,
    color: Color = LocalYDContentColor.current,
    radius: Dp = Dp.Unspecified
) = ripple(bounded, radius, color)