@file:YDRevisionIn(implementedAt = "2026-04-23", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.topbar

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.themes.contentColorFor

internal object YDTopAppBarDefaults {
    @Composable
    fun topAppBarColors(
        containerColor: Color = colorScheme.surfaceContainerDefault,
        scrolledContainerColor: Color = containerColor,
        navigationIconContentColor: Color = contentColorFor(containerColor),
        titleContentColor: Color = contentColorFor(containerColor),
        actionIconContentColor: Color = contentColorFor(containerColor),
    ) = YDTopAppBarColors(
        containerColor,
        scrolledContainerColor,
        navigationIconContentColor,
        titleContentColor,
        actionIconContentColor
    )

    val windowInsets: WindowInsets
        @Composable
        get() = WindowInsets.systemBars.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)

    @Suppress("unused")
    @Composable
    fun pinnedScrollBehavior(
        state: YDTopAppBarState = rememberYDTopAppBarState(),
        onCanScroll: () -> Boolean = { true }
    ): YDTopAppBarScrollBehavior = YDPinnedScrollBehavior(state = state, canScroll = onCanScroll)

    @Suppress("unused")
    @Composable
    fun enterAlwaysScrollBehavior(
        state: YDTopAppBarState = rememberYDTopAppBarState(),
        canScroll: () -> Boolean = { true },
        snapAnimationSpec: AnimationSpec<Float>? = spring(stiffness = Spring.StiffnessMediumLow),
        flingAnimationSpec: DecayAnimationSpec<Float>? = rememberSplineBasedDecay()
    ): YDTopAppBarScrollBehavior =
        YDEnterAlwaysScrollBehavior(
            state = state,
            snapAnimationSpec = snapAnimationSpec,
            flingAnimationSpec = flingAnimationSpec,
            canScroll = canScroll
        )

    @Suppress("unused")
    @Composable
    fun exitUntilCollapsedScrollBehavior(
        state: YDTopAppBarState = rememberYDTopAppBarState(),
        canScroll: () -> Boolean = { true },
        snapAnimationSpec: AnimationSpec<Float>? = spring(stiffness = Spring.StiffnessMediumLow),
        flingAnimationSpec: DecayAnimationSpec<Float>? = rememberSplineBasedDecay()
    ): YDTopAppBarScrollBehavior =
        YDExitUntilCollapsedScrollBehavior(
            state = state,
            snapAnimationSpec = snapAnimationSpec,
            flingAnimationSpec = flingAnimationSpec,
            canScroll = canScroll
        )
}