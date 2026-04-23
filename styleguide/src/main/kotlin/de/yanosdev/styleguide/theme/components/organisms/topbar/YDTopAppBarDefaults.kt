@file:YDRevisionIn(implementedAt = "2026-04-23", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.organisms.topbar

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

object YDTopAppBarDefaults {
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

    /**
     * Returns a pinned [YDTopAppBarScrollBehavior] that tracks nested-scroll callbacks and
     * updates its [YDTopAppBarState.contentOffset] accordingly.
     *
     * @param state the state object to be used to control or observe the top app bar's scroll
     * state. See [rememberYDTopAppBarState] for a state that is remembered across compositions.
     * @param canScroll a callback used to determine whether scroll events are to be handled by this
     * pinned [YDTopAppBarScrollBehavior]
     */
    @Suppress("unused")
    @Composable
    fun pinnedScrollBehavior(
        state: YDTopAppBarState = rememberYDTopAppBarState(),
        canScroll: () -> Boolean = { true }
    ): YDTopAppBarScrollBehavior = YDPinnedScrollBehavior(state = state, canScroll = canScroll)

    /**
     * Returns a [YDTopAppBarScrollBehavior]. A top app bar that is set up with this
     * [YDTopAppBarScrollBehavior] will immediately collapse when the content is pulled up, and will
     * immediately appear when the content is pulled down.
     *
     * @param state the state object to be used to control or observe the top app bar's scroll
     * state. See [rememberYDTopAppBarState] for a state that is remembered across compositions.
     * @param canScroll a callback used to determine whether scroll events are to be
     * handled by this [YDEnterAlwaysScrollBehavior]
     * @param snapAnimationSpec an optional [AnimationSpec] that defines how the top app bar snaps
     * to either fully collapsed or fully extended state when a fling or a drag scrolled it into an
     * intermediate position
     * @param flingAnimationSpec an optional [DecayAnimationSpec] that defined how to fling the top
     * app bar when the user flings the app bar itself, or the content below it
     */
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

    /**
     * Returns a [YDTopAppBarScrollBehavior] that adjusts its properties to affect the colors and
     * height of the top app bar.
     *
     * A top app bar that is set up with this [YDTopAppBarScrollBehavior] will immediately collapse
     * when the nested content is pulled up, and will expand back the collapsed area when the
     * content is  pulled all the way down.
     *
     * @param state the state object to be used to control or observe the top app bar's scroll
     * state. See [rememberYDTopAppBarState] for a state that is remembered across compositions.
     * @param canScroll a callback used to determine whether scroll events are to be
     * handled by this [YDExitUntilCollapsedScrollBehavior]
     * @param snapAnimationSpec an optional [AnimationSpec] that defines how the top app bar snaps
     * to either fully collapsed or fully extended state when a fling or a drag scrolled it into an
     * intermediate position
     * @param flingAnimationSpec an optional [DecayAnimationSpec] that defined how to fling the top
     * app bar when the user flings the app bar itself, or the content below it
     */
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