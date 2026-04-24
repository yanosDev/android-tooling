@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.picker

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import kotlin.math.abs

@Composable
internal fun YDWheelPickerColumn(
    items: List<String>,
    selectedIndex: Int,
    onSelectedIndexChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    colors: YDPickerColors = YDPickerDefaults.pickerColors(),
    itemHeight: Dp = YDPickerDefaults.ItemHeight,
    showSelectionIndicator: Boolean = true,
    visibleItemCount: Int = YDPickerDefaults.VisibleItemCount,
) {
    val density = LocalDensity.current
    val itemHeightPx = remember(density, itemHeight) { with(density) { itemHeight.toPx() } }

    val startIndex = remember(items.size, selectedIndex) {
        val base = Int.MAX_VALUE / 2
        base - (base % items.size) + selectedIndex
    }

    val lazyListState = rememberLazyListState(initialFirstVisibleItemIndex = startIndex)
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

    val centerIndex by remember(itemHeightPx) {
        derivedStateOf {
            val offset = lazyListState.firstVisibleItemScrollOffset
            lazyListState.firstVisibleItemIndex + if (offset > itemHeightPx / 2f) 1 else 0
        }
    }

    LaunchedEffect(lazyListState.isScrollInProgress, centerIndex) {
        if (!lazyListState.isScrollInProgress) {
            onSelectedIndexChange(centerIndex % items.size)
        }
    }

    Box(modifier = modifier.height(itemHeight * visibleItemCount)) {
        LazyColumn(
            state = lazyListState,
            flingBehavior = snapBehavior,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = itemHeight * (visibleItemCount / 2)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(count = Int.MAX_VALUE) { index ->
                val isSelected = index == centerIndex
                Box(
                    modifier = Modifier
                        .height(itemHeight)
                        .fillMaxWidth()
                        .graphicsLayer {
                            val distance = abs(index - lazyListState.firstVisibleItemIndex)
                            alpha = when {
                                distance == 0 -> 1f
                                distance == 1 -> 0.5f
                                else -> 0.2f
                            }
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    YDText(
                        text = items[index % items.size],
                        color = if (isSelected) colors.selectedTextColor else colors.textColor,
                        style = if (isSelected) typography.mdMediumBold else typography.mdRegular,
                    )
                }
            }
        }

        if (showSelectionIndicator) {
            val indicatorColor = colors.selectionIndicatorColor
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .fillMaxWidth()
                    .height(height = itemHeight)
                    .drawBehind {
                        val strokeWidth = 1.dp.toPx()
                        drawLine(
                            color = indicatorColor,
                            start = Offset(x = 0f, y = 0f),
                            end = Offset(x = size.width, y = 0f),
                            strokeWidth = strokeWidth,
                        )
                        drawLine(
                            color = indicatorColor,
                            start = Offset(x = 0f, y = size.height),
                            end = Offset(x = size.width, y = size.height),
                            strokeWidth = strokeWidth,
                        )
                    },
            )
        }
    }
}
