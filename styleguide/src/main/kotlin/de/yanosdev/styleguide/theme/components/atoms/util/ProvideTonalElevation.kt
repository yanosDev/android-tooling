@file:YDRevisionIn(implementedAt = "2026-04-17", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.foundations.semantics.LocalAbsoluteYDTonal
import de.yanosdev.styleguide.theme.foundations.semantics.LocalTonalYDColors
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDColors
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDContentColor
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDTonalEnabled
import de.yanosdev.styleguide.theme.foundations.semantics.YDTonal
import de.yanosdev.styleguide.theme.foundations.semantics.elevatedColorFor

@Composable
internal fun ProvideTonalElevation(
    backgroundColor: Color,
    contentColor: Color,
    tonalElevation: YDTonal,
    content: @Composable (Color) -> Unit,
) {
    if (!LocalYDTonalEnabled.current || tonalElevation.isZero) {
        CompositionLocalProvider(LocalYDContentColor provides contentColor) {
            content(backgroundColor)
        }
    } else {
        val absoluteTonalElevation = LocalAbsoluteYDTonal.current + tonalElevation
        val colorScheme = LocalYDColors.current
        val elevatedColorScheme = LocalTonalYDColors.current.getOrElse(
            index = absoluteTonalElevation.value
        ) {
            // TODO: Add logs implementation with util package than activate this.
            /*logError(
                tag = LogTag.TonalElevation,
                cause = IllegalStateException("Tonal elevation of $absoluteTonalElevation is not supported"),
            )*/
            LocalTonalYDColors.current.last()
        }

        CompositionLocalProvider(
            LocalAbsoluteYDTonal provides absoluteTonalElevation,
            LocalYDColors provides elevatedColorScheme,
            LocalYDContentColor provides colorScheme.elevatedColorFor(
                color = contentColor,
                elevatedScheme = elevatedColorScheme,
            ),
        ) {
            content(
                colorScheme.elevatedColorFor(
                    color = backgroundColor,
                    elevatedScheme = elevatedColorScheme,
                ),
            )
        }
    }
}