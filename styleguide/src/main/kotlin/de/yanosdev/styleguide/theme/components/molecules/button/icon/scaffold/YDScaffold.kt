@file:OptIn(ExperimentalLayoutApi::class)

@file:YDRevisionIn(implementedAt = "2026-04-18", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.button.icon.scaffold

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.MutableWindowInsets
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.onConsumedWindowInsetsChanged
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.offset
import androidx.compose.ui.util.fastMap
import androidx.compose.ui.util.fastMaxBy
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.YDSurface
import de.yanosdev.styleguide.theme.components.atoms.fab.YDFabPlacement
import de.yanosdev.styleguide.theme.components.atoms.fab.YDFabPosition
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.contentColorFor

/**
 * Derived from Material3 Scaffold
 */
@Composable
fun YDScaffold(
    modifier: Modifier = Modifier,
    applyImePadding: Boolean = true,
    containerColor: Color = colorScheme.surface,
    contentColor: Color = contentColorFor(backgroundColor = containerColor),
    contentWindowInsets: WindowInsets = YDScaffoldDefaults.contentWindowInsets,
    floatingActionButtonPosition: YDFabPosition = YDFabPosition.End,
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    topBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    val imePaddingModifier = if (applyImePadding) Modifier.imePadding() else Modifier
    val safeInsets = remember(contentWindowInsets) {
        MutableWindowInsets(contentWindowInsets)
    }
    YDSurface(
        modifier = modifier
            .then(imePaddingModifier)
            .onConsumedWindowInsetsChanged { consumedWindowInsets ->
                safeInsets.insets = contentWindowInsets.exclude(consumedWindowInsets)
            },
        color = containerColor,
        contentColor = contentColor
    ) {
        ScaffoldLayout(
            snackbar = snackbarHost,
            fab = floatingActionButton,
            fabPosition = floatingActionButtonPosition,
            bottomBar = bottomBar,
            contentWindowInsets = safeInsets,
            topBar = topBar,
            content = content
        )
    }
}

@Composable
private fun ScaffoldLayout(
    contentWindowInsets: WindowInsets,
    fabPosition: YDFabPosition,
    bottomBar: @Composable () -> Unit,
    fab: @Composable () -> Unit,
    snackbar: @Composable () -> Unit,
    topBar: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val fabSpacing = spacings.large

    SubcomposeLayout { constraints ->
        val layoutWidth = constraints.maxWidth
        val layoutHeight = constraints.maxHeight

        val looseConstraints = constraints.copy(minWidth = 0, minHeight = 0)

        layout(layoutWidth, layoutHeight) {

            val topBarPlaceables = subcompose(YDScaffoldContent.TopBar, topBar).map {
                it.measure(looseConstraints)
            }

            val snackbarPlaceables =
                subcompose(YDScaffoldContent.Snackbar, snackbar).fastMap {
                    val leftInset = contentWindowInsets.getLeft(
                        density = this@SubcomposeLayout,
                        layoutDirection = layoutDirection
                    )
                    val rightInset =
                        contentWindowInsets.getRight(this@SubcomposeLayout, layoutDirection)
                    val bottomInset = contentWindowInsets.getBottom(this@SubcomposeLayout)
                    it.measure(looseConstraints.offset(-leftInset - rightInset, -bottomInset))
                }

            val fabPlaceable = subcompose(
                YDScaffoldContent.Fab,
                fab
            ).mapNotNull { measurable ->
                val leftInset = contentWindowInsets.getLeft(
                    this@SubcomposeLayout,
                    layoutDirection
                )
                val rightInset = contentWindowInsets.getRight(
                    this@SubcomposeLayout,
                    layoutDirection
                )
                val bottomInset = contentWindowInsets.getBottom(this@SubcomposeLayout)
                measurable.measure(
                    looseConstraints.offset(
                        horizontal = -leftInset - rightInset,
                        vertical = -bottomInset
                    )
                ).takeIf { it.height != 0 && it.width != 0 }
            }
            val fabPlacement = if (fabPlaceable.isNotEmpty()) {
                val fabWidth = fabPlaceable.maxByOrNull { it.width }!!.width
                val fabHeight = fabPlaceable.maxByOrNull { it.height }!!.height
                val fabLeftOffset = if (fabPosition == YDFabPosition.End) {
                    if (layoutDirection == LayoutDirection.Ltr) {
                        layoutWidth - fabSpacing.roundToPx() - fabWidth
                    } else {
                        fabSpacing.roundToPx()
                    }
                } else {
                    (layoutWidth - fabWidth) / 2
                }

                YDFabPlacement(
                    left = fabLeftOffset,
                    width = fabWidth,
                    height = fabHeight
                )
            } else null

            val bottomBarPlaceables = subcompose(
                YDScaffoldContent.BottomBar,
                bottomBar
            ).map { it.measure(looseConstraints) }


            val topBarHeight = topBarPlaceables.maxByOrNull { it.height }?.height ?: 0
            val snackbarHeight = snackbarPlaceables.fastMaxBy { it.height }?.height ?: 0
            val snackbarWidth = snackbarPlaceables.fastMaxBy { it.width }?.width ?: 0
            val bottomBarHeight = bottomBarPlaceables.maxByOrNull { it.height }?.height

            val fabOffsetFromBottom = fabPlacement?.let {
                if (bottomBarHeight == null) {
                    it.height + fabSpacing.roundToPx() + contentWindowInsets.getBottom(this@SubcomposeLayout)
                } else {
                    bottomBarHeight + it.height + fabSpacing.roundToPx()
                }
            }
            val snackbarOffsetFromBottom =
                if (snackbarHeight != 0) {
                    snackbarHeight +
                            (fabOffsetFromBottom
                                ?: bottomBarHeight
                                ?: contentWindowInsets.getBottom(this@SubcomposeLayout))
                } else {
                    0
                }

            val bodyContentPlaceables = subcompose(YDScaffoldContent.MainContent) {
                val insets = contentWindowInsets.asPaddingValues(this@SubcomposeLayout)
                val innerPadding = PaddingValues(
                    top = if (topBarPlaceables.isEmpty()) insets.calculateTopPadding() else topBarHeight.toDp(),
                    bottom = if (bottomBarPlaceables.isEmpty() || bottomBarHeight == null) {
                        insets.calculateBottomPadding()
                    } else bottomBarHeight.toDp(),
                    start = insets.calculateStartPadding((this@SubcomposeLayout).layoutDirection),
                    end = insets.calculateEndPadding((this@SubcomposeLayout).layoutDirection)
                )
                content(innerPadding)
            }.map { it.measure(looseConstraints) }

            bodyContentPlaceables.forEach {
                it.place(0, 0)
            }
            topBarPlaceables.forEach {
                it.place(0, 0)
            }
            snackbarPlaceables.forEach {
                it.place(
                    (layoutWidth - snackbarWidth) / 2 +
                            contentWindowInsets.getLeft(this@SubcomposeLayout, layoutDirection),
                    layoutHeight - snackbarOffsetFromBottom
                )
            }
            bottomBarPlaceables.forEach {
                it.place(0, layoutHeight - (bottomBarHeight ?: 0))
            }
            fabPlacement?.let { placement ->
                fabPlaceable.forEach {
                    it.place(placement.left, layoutHeight - fabOffsetFromBottom!!)
                }
            }
        }
    }
}


