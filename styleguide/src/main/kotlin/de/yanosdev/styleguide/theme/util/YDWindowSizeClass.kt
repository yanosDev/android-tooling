@file:YDRevisionIn(implementedAt = "2026-04-16", revisionAfterInDays = 365)
@file:Suppress("unused")
@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package de.yanosdev.styleguide.theme.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn

internal val LocalYDWindowSizeClass = compositionLocalOf<WindowSizeClass> {
    throw IllegalStateException("WindowSizeClass must be set")
}

private data class YDLimitHeightElement(
    private val maxHeight: Dp,
    private val shouldLimit: Boolean
) : ModifierNodeElement<YDLimitHeightNode>() {
    override fun create(): YDLimitHeightNode =
        YDLimitHeightNode(shouldLimit = shouldLimit, maxHeight = maxHeight)

    override fun update(node: YDLimitHeightNode) {
        node.shouldLimit = shouldLimit
        node.maxHeight = maxHeight
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "limitHeight"
        properties["shouldLimit"] = shouldLimit
        properties["maxHeight"] = maxHeight
    }
}

private data class YDLimitWidthElement(
    private val maxWidth: Dp,
    private val shouldLimit: Boolean
) : ModifierNodeElement<YDLimitWidthNode>() {
    override fun create(): YDLimitWidthNode =
        YDLimitWidthNode(shouldLimit = shouldLimit, maxWidth = maxWidth)

    override fun update(node: YDLimitWidthNode) {
        node.shouldLimit = shouldLimit
        node.maxWidth = maxWidth
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "limitWidth"
        properties["shouldLimit"] = shouldLimit
        properties["maxWidth"] = maxWidth
    }
}

private data class YDLimitHeightNode(
    var maxHeight: Dp,
    var shouldLimit: Boolean
) : LayoutModifierNode, Modifier.Node() {
    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints,
    ): MeasureResult {
        val maxHeightPx = maxHeight.roundToPx()
        val constrained = when {
            // Limit to given Value
            shouldLimit -> {
                val newMaxHeight = constraints.maxHeight.coerceAtMost(maximumValue = maxHeightPx)
                val newMinHeight = constraints.minHeight.coerceAtMost(maximumValue = newMaxHeight)

                constraints.copy(
                    maxHeight = newMaxHeight,
                    minHeight = newMinHeight
                )
            }
            // FillMaxHeight
            constraints.hasBoundedHeight -> constraints.copy(minHeight = constraints.maxHeight)
            // Do nothing
            else -> constraints
        }
        val placeable = measurable.measure(constraints = constrained)
        return layout(width = placeable.width, height = placeable.height) {
            placeable.place(x = 0, y = 0)
        }
    }
}

private data class YDLimitWidthNode(
    var maxWidth: Dp,
    var shouldLimit: Boolean
) : LayoutModifierNode, Modifier.Node() {
    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints,
    ): MeasureResult {
        val maxWidthPx = maxWidth.roundToPx()
        val constrained = when {
            // Limit to given Value
            shouldLimit -> {
                val newMaxWidth = constraints.maxWidth.coerceAtMost(maximumValue = maxWidthPx)
                val newMinWidth = constraints.minWidth.coerceAtMost(maximumValue = newMaxWidth)
                constraints.copy(
                    maxWidth = newMaxWidth,
                    minWidth = newMinWidth
                )
            }
            // FillMaxWidth
            constraints.hasBoundedWidth -> constraints.copy(minWidth = constraints.maxWidth)
            // Do nothing
            else -> constraints
        }
        val placeable = measurable.measure(constraints = constrained)
        return layout(width = placeable.width, height = placeable.height) {
            placeable.place(x = 0, y = 0)
        }
    }
}

internal fun Modifier.limitWidth(shouldLimit: Boolean, maxWidth: Dp): Modifier =
    this then YDLimitWidthElement(shouldLimit = shouldLimit, maxWidth = maxWidth)

internal fun Modifier.limitHeight(shouldLimit: Boolean, maxHeight: Dp): Modifier =
    this then YDLimitHeightElement(shouldLimit = shouldLimit, maxHeight = maxHeight)

/**
 * Modifier used to limit the width of popups on non-compact screens.
 *
 * Should only be used for popups, not for regular screen content.
 *
 * @param insets window insets that need to be applied and consumed if the popup width is limited
 */
@Composable
internal fun Modifier.limitNonCompactPopupWidth(insets: WindowInsets): Modifier {
    val shouldLimit = LocalYDWindowSizeClass.current.widthSizeClass != WindowWidthSizeClass.Compact
    return if (shouldLimit) {
        windowInsetsPadding(insets = insets.only(sides = WindowInsetsSides.Horizontal))
    } else {
        this
    }.limitWidth(shouldLimit = shouldLimit, maxWidth = NonCompactPopupWidth)
}

/**
 * Limits the width of a component to [limit] if [LocalYDWindowSizeClass]' current
 * [WindowSizeClass.widthSizeClass] is not [WindowWidthSizeClass.Compact].
 */
@Composable
internal fun Modifier.limitNonCompactContentWidth(limit: Dp = NonCompactContentWidth) = limitWidth(
    shouldLimit = LocalYDWindowSizeClass.current.widthSizeClass != WindowWidthSizeClass.Compact,
    maxWidth = limit
)

/**
 * Limits the height of a component to [limit] if [LocalYDWindowSizeClass]' current
 * [WindowSizeClass.heightSizeClass] is not [WindowHeightSizeClass.Compact].
 */
@Composable
internal fun Modifier.limitNonCompactHeight(limit: Dp) = limitHeight(
    shouldLimit = LocalYDWindowSizeClass.current.heightSizeClass != WindowHeightSizeClass.Compact,
    maxHeight = limit
)

/**
 * Limits the height of a component to [limit] if [LocalYDWindowSizeClass]' current
 * [WindowSizeClass.widthSizeClass] is [WindowWidthSizeClass.Expanded].
 */
@Composable
internal fun Modifier.limitExpandedWidth(limit: Dp) =
    limitWidth(
        shouldLimit = LocalYDWindowSizeClass.current.widthSizeClass == WindowWidthSizeClass.Expanded,
        maxWidth = limit
    )

/**
 * Limits the height of a component to [limit] if [LocalYDWindowSizeClass]' current
 * [WindowSizeClass.heightSizeClass] is [WindowHeightSizeClass.Expanded].
 */
@Composable
internal fun Modifier.limitExpandedHeight(limit: Dp) = limitHeight(
    shouldLimit = LocalYDWindowSizeClass.current.heightSizeClass == WindowHeightSizeClass.Expanded,
    maxHeight = limit
)

/**
 * Width limit for popups (alert dialogs, sheets, toasts, etc.) on non-compact screens.
 */
val NonCompactPopupWidth = 560.dp

/**
 * Width limit for screen content that does not have a tablet layout on non-compact screens.
 *
 * Should be used sparingly. Generally, it is preferred to implement a proper tablet layout.
 */
val NonCompactContentWidth = 560.dp

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
private fun CompactPopUpPreview() {
    val insets = WindowInsets(left = 10.dp, top = 10.dp, right = 10.dp, bottom = 10.dp)
    PreviewBase(
        width = 360.dp,
        height = 640.dp,
        modifier = { Modifier.limitNonCompactPopupWidth(insets = insets) }
    )
}

@Preview(showBackground = true, widthDp = 720, heightDp = 1280)
@Composable
private fun MediumPopUpPreview() {
    val insets = WindowInsets(left = 10.dp, top = 10.dp, right = 10.dp, bottom = 10.dp)
    PreviewBase(
        width = 720.dp,
        height = 1280.dp,
        modifier = { Modifier.limitNonCompactPopupWidth(insets = insets) }
    )
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
private fun CompactWidthPreview() =
    PreviewBase(
        width = 360.dp,
        height = 640.dp,
        modifier = { Modifier.limitNonCompactContentWidth() }
    )

@Preview(showBackground = true, widthDp = 720, heightDp = 1280)
@Composable
private fun NonCompactWidthPreview() = PreviewBase(
    width = 720.dp,
    height = 1280.dp,
    modifier = { Modifier.limitNonCompactContentWidth() }
)

@Preview(
    showBackground = true,
    widthDp = 1280,
    heightDp = 800,
)
@Composable
private fun ExpandedWidthPreview() = PreviewBase(
    width = 1280.dp,
    height = 800.dp,
    modifier = { Modifier.limitExpandedWidth(300.dp) }
)

@Preview(showBackground = true, widthDp = 1280, heightDp = 1280)
@Composable
private fun ExpandedWidthAndHeightPreview() = PreviewBase(
    width = 1280.dp,
    height = 1280.dp,
    modifier = {
        Modifier
            .limitExpandedWidth(1280.dp)
            .limitExpandedHeight(80.dp)
    }
)

@Preview(
    showBackground = true,
    widthDp = 1280,
    heightDp = 800,
)
@Composable
private fun IgnoreExpandedWidthPreview() = PreviewBase(
    width = 600.dp,
    height = 800.dp,
    modifier = { Modifier.limitExpandedWidth(200.dp) }
)

@Preview(showBackground = true, widthDp = 1280, heightDp = 1280)
@Composable
private fun IgnoreExpandedWidthAndHeightPreview() = PreviewBase(
    width = 600.dp,
    height = 800.dp,
    modifier = {
        Modifier
            .limitExpandedWidth(200.dp)
            .limitExpandedHeight(40.dp)
    }
)

@Composable
private fun PreviewBase(width: Dp, height: Dp, modifier: @Composable () -> Modifier) {
    val size = DpSize(width = width, height = height)
    val windowSizeClass = WindowSizeClass.calculateFromSize(size = size)

    CompositionLocalProvider(value = LocalYDWindowSizeClass provides windowSizeClass) {
        Column(
            modifier = Modifier.background(color = Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = modifier()
                    .background(color = Color.White)
                    .size(width = 600.dp, height = 100.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "width: ${windowSizeClass.widthSizeClass} \n" +
                            "height: ${windowSizeClass.heightSizeClass}"
                )
            }
        }
    }
}