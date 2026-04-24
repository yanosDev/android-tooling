@file:YDRevisionIn(implementedAt = "2026-04-23", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.topbar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.YDSurface
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDContentColor
import de.yanosdev.styleguide.theme.foundations.semantics.ProvideMergedYDTextStyle
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import kotlin.math.max
import kotlin.math.roundToInt

@Composable
fun YDTopAppBar(
    navigationIcon: @Composable () -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    centeredTitle: Boolean = true,
    colors: YDTopAppBarColors = YDTopAppBarDefaults.topAppBarColors(),
    windowInsets: WindowInsets = YDTopAppBarDefaults.windowInsets,
    actions: @Composable RowScope.() -> Unit = {}
) = YDTopAppBar(
    title = { YDText(text = title, overflow = TextOverflow.Ellipsis, maxLines = 1) },
    modifier = modifier,
    navigationIcon = navigationIcon,
    actions = actions,
    centeredTitle = centeredTitle,
    windowInsets = windowInsets,
    colors = colors,
)

@Composable
fun YDTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    centeredTitle: Boolean = true,
    windowInsets: WindowInsets = YDTopAppBarDefaults.windowInsets,
    colors: YDTopAppBarColors = YDTopAppBarDefaults.topAppBarColors()
) {
    SingleRowTopAppBar(
        modifier = modifier,
        title = title,
        titleTextStyle = typography.h4,
        centeredTitle = centeredTitle,
        navigationIcon = navigationIcon,
        actions = actions,
        windowInsets = windowInsets,
        colors = colors,
        scrollBehavior = null
    )
}

@Composable
private fun SingleRowTopAppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    titleTextStyle: TextStyle,
    centeredTitle: Boolean,
    navigationIcon: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit,
    windowInsets: WindowInsets,
    colors: YDTopAppBarColors,
    scrollBehavior: YDTopAppBarScrollBehavior?
) {
    val heightOffsetLimit =
        with(LocalDensity.current) { -TopAppBarSmallContainerHeight.toPx() }
    SideEffect {
        if (scrollBehavior?.state?.heightOffsetLimit != heightOffsetLimit) {
            scrollBehavior?.state?.heightOffsetLimit = heightOffsetLimit
        }
    }

    val colorTransitionFraction = scrollBehavior?.state?.overlappedFraction ?: 0f
    val fraction = if (colorTransitionFraction > 0.01f) 1f else 0f
    val appBarContainerColor by animateColorAsState(
        targetValue = colors.containerColor(fraction),
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow)
    )

    val actionsRow = @Composable {
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            content = actions
        )
    }

    val appBarDragModifier = if (scrollBehavior != null && !scrollBehavior.isPinned) {
        Modifier.draggable(
            orientation = Orientation.Vertical,
            state = rememberDraggableState { delta ->
                scrollBehavior.state.heightOffset = scrollBehavior.state.heightOffset + delta
            },
            onDragStopped = { velocity ->
                settleYDAppBar(
                    scrollBehavior.state,
                    velocity,
                    scrollBehavior.flingAnimationSpec,
                    scrollBehavior.snapAnimationSpec
                )
            }
        )
    } else {
        Modifier
    }

    YDSurface(modifier = modifier.then(appBarDragModifier), color = appBarContainerColor) {
        val height = LocalDensity.current.run {
            TopAppBarSmallContainerHeight.toPx() + (scrollBehavior?.state?.heightOffset
                ?: 0f)
        }
        YDTopAppBarLayout(
            modifier = Modifier
                .windowInsetsPadding(windowInsets)
                .clipToBounds(),
            heightPx = height,
            navigationIconContentColor = colors.navigationIconContentColor,
            titleContentColor = colors.titleContentColor,
            actionIconContentColor = colors.actionIconContentColor,
            title = title,
            titleTextStyle = titleTextStyle,
            titleAlpha = 1f,
            titleVerticalArrangement = Arrangement.Center,
            titleHorizontalArrangement =
                if (centeredTitle) Arrangement.Center else Arrangement.Start,
            titleBottomPadding = 0,
            hideTitleSemantics = false,
            navigationIcon = navigationIcon,
            actions = actionsRow,
        )
    }
}

@Composable
private fun YDTopAppBarLayout(
    modifier: Modifier,
    heightPx: Float,
    navigationIconContentColor: Color,
    titleContentColor: Color,
    actionIconContentColor: Color,
    title: @Composable () -> Unit,
    titleTextStyle: TextStyle,
    titleAlpha: Float,
    titleVerticalArrangement: Arrangement.Vertical,
    titleHorizontalArrangement: Arrangement.Horizontal,
    titleBottomPadding: Int,
    hideTitleSemantics: Boolean,
    navigationIcon: @Composable () -> Unit,
    actions: @Composable () -> Unit,
) {
    Layout(
        {
            Box(
                Modifier
                    .layoutId("navigationIcon")
                    .padding(start = TopAppBarHorizontalPadding)
            ) {
                CompositionLocalProvider(
                    LocalYDContentColor provides navigationIconContentColor,
                    content = navigationIcon
                )
            }
            Box(
                Modifier
                    .layoutId("title")
                    .then(if (hideTitleSemantics) Modifier.clearAndSetSemantics { } else Modifier)
            ) {
                ProvideMergedYDTextStyle(value = titleTextStyle) {
                    CompositionLocalProvider(
                        LocalYDContentColor provides titleContentColor.copy(alpha = titleAlpha),
                        content = title
                    )
                }
            }
            Box(
                Modifier
                    .layoutId("actionIcons")
                    .padding(end = TopAppBarHorizontalPadding)
            ) {
                CompositionLocalProvider(
                    LocalYDContentColor provides actionIconContentColor,
                    content = actions
                )
            }
        },
        modifier = modifier
    ) { measurables, constraints ->
        val navigationIconPlaceable =
            measurables.first { it.layoutId == "navigationIcon" }
                .measure(constraints.copy(minWidth = 0))
        val actionIconsPlaceable =
            measurables.first { it.layoutId == "actionIcons" }
                .measure(constraints.copy(minWidth = 0))

        val maxTitleWidth = if (constraints.maxWidth == Constraints.Infinity) {
            constraints.maxWidth
        } else {
            if (titleHorizontalArrangement == Arrangement.Center) {
                (constraints.maxWidth - maxOf(
                    navigationIconPlaceable.width,
                    actionIconsPlaceable.width
                ) * 2).coerceAtLeast(0)
            } else {
                (constraints.maxWidth - navigationIconPlaceable.width.coerceAtLeast(
                    TopAppBarTitleInset.roundToPx()
                ) - actionIconsPlaceable.width.coerceAtLeast(
                    TopAppBarTitleInset.roundToPx()
                )).coerceAtLeast(0)
            }

        }
        val titlePlaceable =
            measurables.first { it.layoutId == "title" }
                .measure(constraints.copy(minWidth = 0, maxWidth = maxTitleWidth))

        val titleBaseline =
            if (titlePlaceable[LastBaseline] != AlignmentLine.Unspecified) {
                titlePlaceable[LastBaseline]
            } else {
                0
            }

        val layoutHeight = heightPx.roundToInt()

        layout(constraints.maxWidth, layoutHeight) {
            navigationIconPlaceable.placeRelative(
                x = 0,
                y = (layoutHeight - navigationIconPlaceable.height) / 2
            )

            titlePlaceable.placeRelative(
                x = when (titleHorizontalArrangement) {
                    Arrangement.Center -> (constraints.maxWidth - titlePlaceable.width) / 2
                    Arrangement.End ->
                        constraints.maxWidth - titlePlaceable.width - actionIconsPlaceable.width
                    else -> max(TopAppBarTitleInset.roundToPx(), navigationIconPlaceable.width)
                },
                y = when (titleVerticalArrangement) {
                    Arrangement.Center -> (layoutHeight - titlePlaceable.height) / 2
                    Arrangement.Bottom ->
                        if (titleBottomPadding == 0) layoutHeight - titlePlaceable.height
                        else layoutHeight - titlePlaceable.height - max(
                            0,
                            titleBottomPadding - titlePlaceable.height + titleBaseline
                        )
                    else -> 0
                }
            )

            actionIconsPlaceable.placeRelative(
                x = constraints.maxWidth - actionIconsPlaceable.width,
                y = (layoutHeight - actionIconsPlaceable.height) / 2
            )
        }
    }
}


private val TopAppBarHorizontalPadding = 4.dp

private val TopAppBarTitleInset = 16.dp

private val TopAppBarSmallContainerHeight = 64.dp