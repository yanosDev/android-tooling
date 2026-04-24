@file:YDRevisionIn(implementedAt = "2026-04-17", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDShadowEnabled
import de.yanosdev.styleguide.theme.foundations.semantics.YDShadow
import de.yanosdev.styleguide.theme.foundations.token.YDColorTokens

@ReadOnlyComposable
@Composable
internal fun Modifier.ydShadow(
    clip: Boolean = true,
    shadowElevation: YDShadow,
    shape: Shape = RectangleShape,
) = if (shadowElevation.elevation > 0.dp && LocalYDShadowEnabled.current) {
    shadow(
        elevation = shadowElevation.elevation * shadowElevation.progress,
        shape = shape,
        clip = clip,
        ambientColor = ShadowColor,
        spotColor = ShadowColor,
    )
} else this

private val ShadowColor = YDColorTokens.Neutral40