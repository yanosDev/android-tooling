@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.tabs

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastFold
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastForEachIndexed
import androidx.compose.ui.util.fastMap
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.YDSurface
import de.yanosdev.styleguide.theme.components.atoms.divider.YDDivider
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.molecules.tabs.YDTabRowDefaults.tabIndicatorOffset
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun YDTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    containerColor: Color = YDTabRowDefaults.containerColor,
    contentColor: Color = YDTabRowDefaults.contentColor,
    divider: @Composable () -> Unit = { YDDivider() },
    indicator: @Composable TabIndicatorScope.() -> Unit = @Composable {
        YDTabRowDefaults.Indicator(
            modifier = Modifier.tabIndicatorOffset(
                selectedTabIndex = selectedTabIndex,
                matchContentSize = false
            )
        )
    },
    content: @Composable () -> Unit
) {
    YDSurface(
        modifier = modifier.selectableGroup(),
        color = containerColor,
        contentColor = contentColor
    ) {
        val scope = remember {
            object : TabIndicatorScope, TabPositionsHolder {

                val tabPositions = mutableStateOf<(List<YDTabPosition>)>(listOf())

                override fun Modifier.tabIndicatorLayout(
                    measure: MeasureScope.(
                        Measurable,
                        Constraints,
                        List<YDTabPosition>,
                    ) -> MeasureResult
                ): Modifier =
                    this.layout { measurable: Measurable, constraints: Constraints ->
                        measure(
                            measurable,
                            constraints,
                            tabPositions.value,
                        )
                    }

                override fun Modifier.tabIndicatorOffset(
                    selectedTabIndex: Int,
                    matchContentSize: Boolean
                ): Modifier =
                    this.then(
                        TabIndicatorModifier(
                            matchContentSize,
                            selectedTabIndex,
                            tabPositions,
                        )
                    )

                override fun setTabPositions(positions: List<YDTabPosition>) {
                    tabPositions.value = positions
                }
            }
        }

        Layout(
            modifier = Modifier.fillMaxWidth(),
            contents = listOf(
                content,
                divider,
                { scope.indicator() },
            )
        ) { (tabMeasurables, dividerMeasurables, indicatorMeasurables), constraints ->
            val tabRowWidth = constraints.maxWidth
            val tabCount = tabMeasurables.size
            var tabWidth = 0
            if (tabCount > 0) {
                tabWidth = (tabRowWidth / tabCount)
            }
            val tabRowHeight = tabMeasurables.fastFold(initial = 0) { max, curr ->
                maxOf(curr.maxIntrinsicHeight(tabWidth), max)
            }

            scope.setTabPositions(List(tabCount) { index ->
                var contentWidth =
                    minOf(
                        tabMeasurables[index].maxIntrinsicWidth(tabRowHeight),
                        tabWidth
                    ).toDp()
                contentWidth -= HorizontalTextPadding * 2
                // Enforce minimum touch target of 24.dp
                val indicatorWidth = maxOf(contentWidth, 24.dp)

                YDTabPosition(tabWidth.toDp() * index, tabWidth.toDp(), indicatorWidth)
            })

            val tabPlaceables = tabMeasurables.fastMap {
                it.measure(
                    constraints.copy(
                        minWidth = tabWidth,
                        maxWidth = tabWidth,
                        minHeight = tabRowHeight,
                        maxHeight = tabRowHeight,
                    )
                )
            }

            val dividerPlaceables = dividerMeasurables.fastMap {
                it.measure(constraints.copy(minHeight = 0))
            }

            val indicatorPlaceables = indicatorMeasurables.fastMap {
                it.measure(
                    constraints.copy(
                        minWidth = tabWidth,
                        maxWidth = tabWidth,
                        maxHeight = tabRowHeight
                    )
                )
            }

            layout(tabRowWidth, tabRowHeight) {
                tabPlaceables.fastForEachIndexed { index, placeable ->
                    placeable.placeRelative(index * tabWidth, 0)
                }

                dividerPlaceables.fastForEach { placeable ->
                    placeable.placeRelative(0, tabRowHeight - placeable.height)
                }

                indicatorPlaceables.fastForEach {
                    it.placeRelative(0, tabRowHeight - it.height)
                }
            }
        }
    }
}

/**
 * Scrollable tab row.
 *
 * Used when a set of tabs cannot fit on screen simultaneously.
 *
 * Derived from Material3 ScrollableTabRow.
 */
@Composable
fun YDScrollableTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    containerColor: Color = YDTabRowDefaults.containerColor,
    contentColor: Color = YDTabRowDefaults.contentColor,
    edgePadding: Dp = ScrollableTabRowPadding,
    indicator: @Composable (tabPositions: List<YDTabPosition>) -> Unit = @Composable { tabPositions ->
        YDTabRowDefaults.Indicator(
            Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
        )
    },
    divider: @Composable () -> Unit = {},
    tabs: @Composable () -> Unit
) {
    YDSurface(
        modifier = modifier,
        color = containerColor,
        contentColor = contentColor
    ) {
        val scrollState = rememberScrollState()
        val coroutineScope = rememberCoroutineScope()
        val scrollableTabData = remember(scrollState, coroutineScope) {
            ScrollableTabData(
                scrollState = scrollState,
                coroutineScope = coroutineScope
            )
        }
        SubcomposeLayout(
            Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.CenterStart)
                .horizontalScroll(scrollState)
                .selectableGroup()
                .clipToBounds()
        ) { constraints ->
            val minTabWidth = ScrollableTabRowMinimumTabWidth.roundToPx()
            val padding = edgePadding.roundToPx()

            val tabMeasurables = subcompose(YDTabSlots.Tabs, tabs)

            val layoutHeight = tabMeasurables.fastFold(initial = 0) { curr, measurable ->
                maxOf(curr, measurable.maxIntrinsicHeight(Constraints.Infinity))
            }

            val tabConstraints = constraints.copy(
                minWidth = minTabWidth,
                minHeight = layoutHeight,
                maxHeight = layoutHeight,
            )

            val tabPlaceables = mutableListOf<Placeable>()
            val tabContentWidths = mutableListOf<Dp>()
            tabMeasurables.fastForEach {
                val placeable = it.measure(tabConstraints)
                var contentWidth =
                    minOf(
                        it.maxIntrinsicWidth(placeable.height),
                        placeable.width
                    ).toDp()
                contentWidth -= HorizontalTextPadding * 2
                tabPlaceables.add(placeable)
                tabContentWidths.add(contentWidth)
            }

            val layoutWidth = tabPlaceables.fastFold(initial = padding * 2) { curr, measurable ->
                curr + measurable.width
            }

            // Position the children.
            layout(layoutWidth, layoutHeight) {
                // Place the tabs
                val tabPositions = mutableListOf<YDTabPosition>()
                var left = padding
                tabPlaceables.fastForEachIndexed { index, placeable ->
                    placeable.placeRelative(left, 0)
                    tabPositions.add(
                        YDTabPosition(
                            left = left.toDp(),
                            width = placeable.width.toDp(),
                            contentWidth = tabContentWidths[index]
                        )
                    )
                    left += placeable.width
                }

                // The divider is measured with its own height, and width equal to the total width
                // of the tab row, and then placed on top of the tabs.
                subcompose(YDTabSlots.Divider, divider).fastForEach {
                    val placeable = it.measure(
                        constraints.copy(
                            minHeight = 0,
                            minWidth = layoutWidth,
                            maxWidth = layoutWidth
                        )
                    )
                    placeable.placeRelative(0, layoutHeight - placeable.height)
                }

                // The indicator container is measured to fill the entire space occupied by the tab
                // row, and then placed on top of the divider.
                subcompose(YDTabSlots.Indicator) {
                    indicator(tabPositions)
                }.fastForEach {
                    it.measure(Constraints.fixed(layoutWidth, layoutHeight)).placeRelative(0, 0)
                }

                scrollableTabData.onLaidOut(
                    density = this@SubcomposeLayout,
                    edgeOffset = padding,
                    tabPositions = tabPositions,
                    selectedTab = selectedTabIndex
                )
            }
        }
    }
}

@PhonePreview
@Composable
private fun Preview() = YDPreview {
    YDTabRow(selectedTabIndex = 0) {
        List(3) { index -> index }.forEach { index ->
            YDTab(
                text = { YDText("Tab $index") },
                selected = index == 0,
                onClick = {}
            )
        }
    }
}

@Immutable
class YDTabPosition internal constructor(val left: Dp, val width: Dp, val contentWidth: Dp) {
    val right: Dp get() = left + width

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is YDTabPosition) return false

        if (left != other.left) return false
        if (width != other.width) return false
        if (contentWidth != other.contentWidth) return false
        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + width.hashCode()
        result = 31 * result + contentWidth.hashCode()
        return result
    }

    override fun toString(): String {
        return "TabPosition(left=$left, right=$right, width=$width, contentWidth=$contentWidth)"
    }
}

object YDTabRowDefaults {
    val containerColor @Composable get() = colorScheme.surfaceContainerDefault

    val contentColor @Composable get() = colorScheme.onSurface

    @Composable
    fun Indicator(
        modifier: Modifier = Modifier,
        color: Color = colorScheme.primary,
        height: Dp = ActiveTabIndicatorHeight
    ) {
        Box(
            modifier
                .fillMaxWidth()
                .height(height)
                .background(color = color)
        )
    }

    /**
     * [Modifier] that takes up all the available width inside the [YDTabRow], and then animates
     * the offset of the indicator it is applied to, depending on the [currentTabPosition].
     *
     * @param currentTabPosition [YDTabPosition] of the currently selected tab. This is used to
     * calculate the offset of the indicator this modifier is applied to, as well as its width.
     */
    fun Modifier.tabIndicatorOffset(
        currentTabPosition: YDTabPosition
    ): Modifier = composed(
        inspectorInfo = debugInspectorInfo {
            name = "tabIndicatorOffset"
            value = currentTabPosition
        }
    ) {
        val currentTabWidth by animateDpAsState(
            targetValue = currentTabPosition.width,
            animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
            label = "Tab width"
        )
        val indicatorOffset by animateDpAsState(
            targetValue = currentTabPosition.left,
            animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
            label = "Indicator offset"
        )
        fillMaxWidth()
            .wrapContentSize(Alignment.BottomStart)
            .offset {
                IntOffset(x = indicatorOffset.roundToPx(), y = 0)
            }
            .width(currentTabWidth)
    }
}

private enum class YDTabSlots {
    Tabs,
    Divider,
    Indicator
}

private class ScrollableTabData(
    private val scrollState: ScrollState,
    private val coroutineScope: CoroutineScope
) {
    private var selectedTab: Int? = null

    fun onLaidOut(
        density: Density,
        edgeOffset: Int,
        tabPositions: List<YDTabPosition>,
        selectedTab: Int
    ) {
        // Animate if the new tab is different from the old tab, or this is called for the first
        // time (i.e selectedTab is `null`).
        if (this.selectedTab != selectedTab) {
            this.selectedTab = selectedTab
            tabPositions.getOrNull(selectedTab)?.let {
                // Scrolls to the tab with [tabPosition], trying to place it in the center of the
                // screen or as close to the center as possible.
                val calculatedOffset = it.calculateTabOffset(density, edgeOffset, tabPositions)
                if (scrollState.value != calculatedOffset) {
                    coroutineScope.launch {
                        scrollState.animateScrollTo(
                            calculatedOffset,
                            animationSpec = ScrollableTabRowScrollSpec
                        )
                    }
                }
            }
        }
    }

    /**
     * @return the offset required to horizontally center the tab inside this TabRow.
     * If the tab is at the start / end, and there is not enough space to fully centre the tab, this
     * will just clamp to the min / max position given the max width.
     */
    private fun YDTabPosition.calculateTabOffset(
        density: Density,
        edgeOffset: Int,
        tabPositions: List<YDTabPosition>
    ): Int = with(density) {
        val totalTabRowWidth = tabPositions.last().right.roundToPx() + edgeOffset
        val visibleWidth = totalTabRowWidth - scrollState.maxValue
        val tabOffset = left.roundToPx()
        val scrollerCenter = visibleWidth / 2
        val tabWidth = width.roundToPx()
        val centeredTabOffset = tabOffset - (scrollerCenter - tabWidth / 2)
        // How much space we have to scroll. If the visible width is <= to the total width, then
        // we have no space to scroll as everything is always visible.
        val availableSpace = (totalTabRowWidth - visibleWidth).coerceAtLeast(0)
        return centeredTabOffset.coerceIn(0, availableSpace)
    }
}

interface TabIndicatorScope {

    /**
     * A layout modifier that provides tab positions, this can be used to animate and layout
     * a TabIndicator depending on size, position, and content size of each Tab.
     */
    fun Modifier.tabIndicatorLayout(
        measure: MeasureScope.(
            Measurable,
            Constraints,
            List<YDTabPosition>,
        ) -> MeasureResult
    ): Modifier

    /**
     * A Modifier that follows the default offset and animation
     *
     * @param selectedTabIndex the index of the current selected tab
     * @param matchContentSize this modifier can also animate the width of the indicator \
     * to match the content size of the tab.
     */
    fun Modifier.tabIndicatorOffset(
        selectedTabIndex: Int,
        matchContentSize: Boolean = false
    ): Modifier
}


internal data class TabIndicatorModifier(
    val followContentSize: Boolean,
    val selectedTabIndex: Int,
    val tabPositionsState: State<List<YDTabPosition>>
) : ModifierNodeElement<YDTabIndicatorOffsetNode>() {

    override fun create(): YDTabIndicatorOffsetNode {
        return YDTabIndicatorOffsetNode(
            tabPositionsState = tabPositionsState,
            selectedTabIndex = selectedTabIndex,
            followContentSize = followContentSize,
        )
    }

    override fun update(node: YDTabIndicatorOffsetNode) {
        node.tabPositionsState = tabPositionsState
        node.selectedTabIndex = selectedTabIndex
        node.followContentSize = followContentSize
    }

    override fun InspectorInfo.inspectableProperties() {
        // Show nothing in the inspector.
    }
}

internal class YDTabIndicatorOffsetNode(
    var tabPositionsState: State<List<YDTabPosition>>,
    var selectedTabIndex: Int,
    var followContentSize: Boolean
) : Modifier.Node(), LayoutModifierNode {

    private var offsetAnimatable: Animatable<Dp, AnimationVector1D>? = null
    private var widthAnimatable: Animatable<Dp, AnimationVector1D>? = null
    private var initialOffset: Dp? = null
    private var initialWidth: Dp? = null

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        if (tabPositionsState.value.isEmpty()) {
            return layout(0, 0) { }
        }

        val currentTabWidth = tabPositionsState.value[selectedTabIndex].contentWidth
        if (followContentSize) {
            if (initialWidth != null) {
                val widthAnim =
                    widthAnimatable ?: Animatable(initialWidth!!, Dp.VectorConverter).also {
                        widthAnimatable = it
                    }

                if (currentTabWidth != widthAnim.targetValue) {
                    coroutineScope.launch { widthAnim.animateTo(currentTabWidth) }
                }
            } else {
                initialWidth = currentTabWidth
            }
        }

        val indicatorOffset = tabPositionsState.value[selectedTabIndex].left

        if (initialOffset != null) {
            val offsetAnim =
                offsetAnimatable ?: Animatable(initialOffset!!, Dp.VectorConverter).also {
                    offsetAnimatable = it
                }

            if (indicatorOffset != offsetAnim.targetValue) {
                coroutineScope.launch { offsetAnim.animateTo(indicatorOffset) }
            }
        } else {
            initialOffset = indicatorOffset
        }

        val offset = offsetAnimatable?.value ?: indicatorOffset

        val placeable = measurable.measure(
            if (followContentSize) {
                val width = widthAnimatable?.value ?: currentTabWidth
                constraints.copy(minWidth = width.roundToPx(), maxWidth = width.roundToPx())
            } else {
                constraints
            }
        )

        return layout(placeable.width, constraints.maxHeight) {
            placeable.place(offset.roundToPx(), constraints.maxHeight - placeable.height)
        }
    }
}

internal interface TabPositionsHolder {

    fun setTabPositions(positions: List<YDTabPosition>)
}

private val ScrollableTabRowMinimumTabWidth = 90.dp

/**
 * The default padding from the starting edge before a tab in a [YDScrollableTabRow].
 */
private val ScrollableTabRowPadding = 0.dp

/**
 * [AnimationSpec] used when scrolling to a tab that is not fully visible.
 */
private val ScrollableTabRowScrollSpec: AnimationSpec<Float> = tween(
    durationMillis = 250,
    easing = FastOutSlowInEasing
)