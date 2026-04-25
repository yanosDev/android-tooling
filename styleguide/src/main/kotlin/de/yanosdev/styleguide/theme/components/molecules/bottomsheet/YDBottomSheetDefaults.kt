@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.bottomsheet


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.themes.YDTheme.shadows
import de.yanosdev.styleguide.theme.themes.YDTheme.shapes
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.tonals

@Stable
internal object YDBottomSheetDefaults {
    val Padding: PaddingValues
        @Composable get() = PaddingValues(top = spacings.large)

    val Shape: Shape
        @Composable get() = shapes.large.copy(
            bottomEnd = CornerSize(size = 0.dp),
            bottomStart = CornerSize(size = 0.dp)
        )

    val ScrimColor: Color
        @Composable get() = colorScheme.scrim.copy(alpha = 0.32f)

    val ContainerColor: Color
        @Composable get() = colorScheme.surface

    val ShadowElevation @Composable get() = shadows.medium

    val TonalElevation @Composable get() = tonals.level1

    val SurfaceInsets: WindowInsets
        @Composable
        get() = WindowInsets.safeDrawing.only(
            sides = WindowInsetsSides.Top
        )

    val ContentInsets: WindowInsets
        @Composable
        get() = WindowInsets.systemBars.add(WindowInsets.displayCutout).only(
            sides = WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom
        )

    val Properties: YDBottomSheetProperties = YDBottomSheetProperties()

    @Composable
    fun TopAppBar(
        title: String,
        navigationIcon: @Composable () -> Unit,
    ) = YDBottomSheetTopAppBar(
        title = title,
        navigationIcon = navigationIcon
    )
}