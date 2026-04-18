@file:OptIn(ExperimentalLayoutApi::class)

@file:YDRevisionIn(implementedAt = "2026-04-18", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.foundations.semantics.YDShadow
import de.yanosdev.styleguide.theme.foundations.semantics.YDTonal
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.themes.contentColorFor

/**
 * Derived from M3 Surface
 */
@Composable
internal fun YDSurface(
    modifier: Modifier = Modifier,
    border: BorderStroke? = null,
    color: Color = colorScheme.surfaceContainerDefault,
    contentColor: Color = contentColorFor(backgroundColor = color),
    shadowElevation: YDShadow = YDShadow.Zero,
    shape: Shape = RectangleShape,
    test: Boolean = false,
    tonalElevation: YDTonal = YDTonal.Zero,
    content: @Composable () -> Unit
) {

}