@file:YDRevisionIn(implementedAt = "2026-04-22", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.progress

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme

internal object YDProgressIndicatorDefaults {
    val linearColor @Composable get() = colorScheme.onSurface
    val linearTrackColor @Composable get() = colorScheme.surfaceContainerNeutralVariant
    val circularColor @Composable get() = colorScheme.onSurface

    val CircularStrokeWidth = 2.dp

    val ProgressAnimationSpec = SpringSpec(
        dampingRatio = Spring.DampingRatioNoBouncy,
        stiffness = Spring.StiffnessVeryLow,
        // The default threshold is 0.01, or 1% of the overall progress range, which is quite
        // large and noticeable. We purposefully choose a smaller threshold.
        visibilityThreshold = 1 / 1000f
    )
}