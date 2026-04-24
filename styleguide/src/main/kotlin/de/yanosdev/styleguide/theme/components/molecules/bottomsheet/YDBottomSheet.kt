@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.bottomsheet

import android.os.Build
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.AnchoredDraggableDefaults
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.MutableWindowInsets
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.onConsumedWindowInsetsChanged
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.window.SecureFlagPolicy
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.YDSurface
import de.yanosdev.styleguide.theme.components.atoms.scrim.YDScrim
import de.yanosdev.styleguide.theme.components.molecules.bottomsheet.model.YDBottomSheetBackEvent
import de.yanosdev.styleguide.theme.components.molecules.bottomsheet.model.YDSheetState
import de.yanosdev.styleguide.theme.components.molecules.bottomsheet.model.YDSheetValue
import de.yanosdev.styleguide.theme.components.molecules.bottomsheet.model.dismissedEvents
import de.yanosdev.styleguide.theme.components.molecules.bottomsheet.model.rememberYDSheetState
import de.yanosdev.styleguide.theme.components.organisms.navigation.YDNavigationCloseIcon
import de.yanosdev.styleguide.theme.foundations.semantics.YDShadow
import de.yanosdev.styleguide.theme.foundations.semantics.YDTonal
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.themes.contentColorFor
import de.yanosdev.styleguide.theme.util.YDPredictiveBack
import de.yanosdev.styleguide.theme.util.limitNonCompactPopupWidth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

@Composable
internal fun YDNavHostBottomSheet(
    predictiveBackProgress: Float,
    sheetState: YDSheetState,
    onBack: () -> Unit,
    onBackEvent: (YDBottomSheetBackEvent) -> Unit,
    modifier: Modifier = Modifier,
    properties: YDBottomSheetProperties = YDBottomSheetDefaults.Properties,
    content: @Composable YDBottomSheetScope.(contentPadding: PaddingValues) -> Unit
) {
    val scope = rememberCoroutineScope()

    YDBottomSheetDialog(
        onDismissRequest = onBack,
        properties = properties,
        onBackEvent = onBackEvent
    ) {
        YDBottomSheetWrapper(
            predictiveBackProgress = predictiveBackProgress,
            scope = scope,
            modifier = modifier,
            sheetState = sheetState,
            properties = properties,
            content = content,
        )
    }
}


@Composable
internal fun YDBottomSheetScope.YDDefaultBottomSheetScreen(
    navigationIcon: @Composable () -> Unit,
    title: String,
    content: @Composable YDBottomSheetScope.() -> Unit
) = YDSurface(color = colorScheme.surface) {
    Column {
        YDBottomSheetDefaults.TopAppBar(
            title = title,
            navigationIcon = navigationIcon,
        )
        this@YDDefaultBottomSheetScreen.content()
    }
}

@Composable
internal fun YDBottomSheet(
    title: String,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = YDBottomSheetDefaults.ContainerColor,
    contentColor: Color = contentColorFor(backgroundColor = containerColor),
    padding: PaddingValues = YDBottomSheetDefaults.Padding,
    properties: YDBottomSheetProperties = YDBottomSheetDefaults.Properties,
    scrimColor: Color = YDBottomSheetDefaults.ScrimColor,
    shadowElevation: YDShadow = YDBottomSheetDefaults.ShadowElevation,
    shape: Shape = YDBottomSheetDefaults.Shape,
    sheetState: YDSheetState = rememberYDSheetState(),
    tonalElevation: YDTonal = YDBottomSheetDefaults.TonalElevation,
    contentWindowInsets: @Composable () -> WindowInsets = { YDBottomSheetDefaults.ContentInsets },
    surfaceWindowInsets: @Composable () -> WindowInsets = { YDBottomSheetDefaults.SurfaceInsets },
    content: @Composable YDBottomSheetScope.(contentPadding: PaddingValues) -> Unit,
) = YDBottomSheet(
    onDismissRequest = onDismissRequest,
    modifier = modifier,
    sheetState = sheetState,
    shape = shape,
    containerColor = containerColor,
    contentColor = contentColor,
    shadowElevation = shadowElevation,
    tonalElevation = tonalElevation,
    padding = padding,
    scrimColor = scrimColor,
    surfaceWindowInsets = surfaceWindowInsets,
    contentWindowInsets = contentWindowInsets,
    properties = properties,
) { contentPadding ->
    YDBottomSheetDefaults.TopAppBar(title = title) {
        YDNavigationCloseIcon(navUp = { animateToDismiss() })
    }
    content(contentPadding)
}

@Composable
internal fun YDBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: YDSheetState = rememberYDSheetState(),
    shape: Shape = YDBottomSheetDefaults.Shape,
    containerColor: Color = YDBottomSheetDefaults.ContainerColor,
    contentColor: Color = contentColorFor(backgroundColor = containerColor),
    shadowElevation: YDShadow = YDBottomSheetDefaults.ShadowElevation,
    tonalElevation: YDTonal = YDBottomSheetDefaults.TonalElevation,
    padding: PaddingValues = YDBottomSheetDefaults.Padding,
    scrimColor: Color = YDBottomSheetDefaults.ScrimColor,
    surfaceWindowInsets: @Composable () -> WindowInsets = { YDBottomSheetDefaults.SurfaceInsets },
    contentWindowInsets: @Composable () -> WindowInsets = { YDBottomSheetDefaults.ContentInsets },
    properties: YDBottomSheetProperties = YDBottomSheetDefaults.Properties,
    content: @Composable YDBottomSheetScope.(contentPadding: PaddingValues) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val predictiveBackProgress = remember { Animatable(initialValue = 0f) }

    YDBottomSheetDialog(
        onDismissRequest = { scope.launch { sheetState.hide() } },
        onBackEvent = { event ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                when (event) {
                    is YDBottomSheetBackEvent.OnBackStarted -> {
                        scope.launch {
                            predictiveBackProgress.snapTo(
                                targetValue = YDPredictiveBack.transform(progress = event.backEvent.progress)
                            )
                        }
                    }

                    is YDBottomSheetBackEvent.OnBackProgressed -> {
                        scope.launch {
                            predictiveBackProgress.snapTo(
                                targetValue = YDPredictiveBack.transform(progress = event.backEvent.progress)
                            )
                        }
                    }

                    YDBottomSheetBackEvent.OnBackInvoked -> Unit

                    YDBottomSheetBackEvent.OnBackCancelled -> {
                        scope.launch {
                            predictiveBackProgress.animateTo(targetValue = 0f)
                        }
                    }
                }
            }
        },
        properties = properties,
    ) {
        YDBottomSheetWrapper(
            predictiveBackProgress = predictiveBackProgress.value,
            scope = scope,
            modifier = modifier,
            sheetState = sheetState,
            shape = shape,
            containerColor = containerColor,
            contentColor = contentColor,
            shadowElevation = shadowElevation,
            tonalElevation = tonalElevation,
            padding = padding,
            scrimColor = scrimColor,
            surfaceWindowInsets = surfaceWindowInsets,
            contentWindowInsets = contentWindowInsets,
            properties = properties,
            content = content,
        )
    }

    LaunchedEffect(sheetState) {
        sheetState.show()
    }

    LaunchedEffect(sheetState) {
        sheetState.dismissedEvents().collectLatest { onDismissRequest() }
    }
}

@Composable
private fun YDBottomSheetWrapper(
    predictiveBackProgress: Float,
    scope: CoroutineScope,
    modifier: Modifier = Modifier,
    sheetState: YDSheetState = rememberYDSheetState(),
    shape: Shape = YDBottomSheetDefaults.Shape,
    containerColor: Color = YDBottomSheetDefaults.ContainerColor,
    contentColor: Color = contentColorFor(backgroundColor = containerColor),
    shadowElevation: YDShadow = YDBottomSheetDefaults.ShadowElevation,
    tonalElevation: YDTonal = YDBottomSheetDefaults.TonalElevation,
    padding: PaddingValues = YDBottomSheetDefaults.Padding,
    scrimColor: Color = YDBottomSheetDefaults.ScrimColor,
    surfaceWindowInsets: @Composable () -> WindowInsets = { YDBottomSheetDefaults.SurfaceInsets },
    contentWindowInsets: @Composable () -> WindowInsets = { YDBottomSheetDefaults.ContentInsets },
    properties: YDBottomSheetProperties = YDBottomSheetDefaults.Properties,
    content: @Composable YDBottomSheetScope.(contentPadding: PaddingValues) -> Unit,
) {
    val positionalThreshold = with(LocalDensity.current) {
        FlingPositionalThreshold.toPx()
    }

    val flingBehavior = AnchoredDraggableDefaults.flingBehavior(
        state = sheetState.anchoredDraggableState,
        positionalThreshold = { positionalThreshold },
    )

    val onFling: (Float) -> Unit = { velocity ->
        scope.launch {
            sheetState.anchoredDraggableState.anchoredDrag {
                val scrollScope = object : ScrollScope {
                    override fun scrollBy(pixels: Float): Float {
                        dragTo(newOffset = sheetState.anchoredDraggableState.requireOffset() + pixels)
                        return pixels
                    }
                }

                with(flingBehavior) {
                    scrollScope.performFling(initialVelocity = velocity)
                }
            }
        }
    }

    fun animateToDismiss(onCompletion: () -> Unit = {}) {
        scope.launch {
            sheetState.hide()
        }.invokeOnCompletion {
            if (!sheetState.isVisible) {
                onCompletion()
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        YDScrim(
            color = scrimColor,
            onDismissRequest = {
                if (properties.shouldDismissOnScrimClick) {
                    animateToDismiss()
                }
            },
            visible = sheetState.targetValue != YDSheetValue.Hidden
        )
        YDBottomSheetContent(
            predictiveBackProgress = predictiveBackProgress,
            flingBehavior = flingBehavior,
            onFling = onFling,
            onAnimateToDismiss = ::animateToDismiss,
            modifier = modifier,
            sheetState = sheetState,
            shape = shape,
            containerColor = containerColor,
            contentColor = contentColor,
            shadowElevation = shadowElevation,
            tonalElevation = tonalElevation,
            padding = padding,
            surfaceWindowInsets = surfaceWindowInsets,
            contentWindowInsets = contentWindowInsets,
            content = content,
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BoxScope.YDBottomSheetContent(
    predictiveBackProgress: Float,
    flingBehavior: FlingBehavior,
    onFling: (Float) -> Unit,
    onAnimateToDismiss: (() -> Unit) -> Unit,
    modifier: Modifier = Modifier,
    sheetState: YDSheetState = rememberYDSheetState(),
    shape: Shape = YDBottomSheetDefaults.Shape,
    containerColor: Color = YDBottomSheetDefaults.ContainerColor,
    contentColor: Color = contentColorFor(containerColor),
    shadowElevation: YDShadow = YDBottomSheetDefaults.ShadowElevation,
    tonalElevation: YDTonal = YDBottomSheetDefaults.TonalElevation,
    padding: PaddingValues = YDBottomSheetDefaults.Padding,
    surfaceWindowInsets: @Composable () -> WindowInsets = { YDBottomSheetDefaults.SurfaceInsets },
    contentWindowInsets: @Composable () -> WindowInsets = { YDBottomSheetDefaults.ContentInsets },
    content: @Composable YDBottomSheetScope.(contentPadding: PaddingValues) -> Unit,
) {
    val contentInsets = contentWindowInsets()
    val safeInsets = remember(contentInsets) {
        MutableWindowInsets(contentInsets)
    }

    YDSurface(
        modifier = modifier
            .align(Alignment.TopCenter)
            .limitNonCompactPopupWidth(insets = contentWindowInsets())
            .windowInsetsPadding(insets = surfaceWindowInsets())
            .onConsumedWindowInsetsChanged { consumedInsets ->
                safeInsets.insets = contentInsets.exclude(consumedInsets)
            }
            .padding(paddingValues = padding)
            .fillMaxWidth()
            .nestedScroll(
                connection = remember(sheetState) {
                    YDBottomSheetScrollConnection(
                        sheetState = sheetState,
                        orientation = Orientation.Vertical,
                        onFling = onFling,
                    )
                }
            )
            .draggableAnchors(
                state = sheetState.anchoredDraggableState,
                orientation = Orientation.Vertical,
            ) { sheetSize, constraints ->
                val fullHeight = constraints.maxHeight.toFloat()

                val newAnchors = DraggableAnchors {
                    YDSheetValue.Hidden at fullHeight
                    if (sheetSize.height != 0) {
                        YDSheetValue.Expanded at max(0f, fullHeight - sheetSize.height)
                    }
                }

                newAnchors to sheetState.anchoredDraggableState.targetValue
            }
            .anchoredDraggable(
                state = sheetState.anchoredDraggableState,
                orientation = Orientation.Vertical,
                enabled = sheetState.isVisible,
                flingBehavior = flingBehavior,
            )
            .graphicsLayer {
                val sheetOffset = sheetState.anchoredDraggableState.offset
                val sheetHeight = size.height
                if (!sheetOffset.isNaN() && !sheetHeight.isNaN() && sheetHeight != 0f) {
                    val progress = predictiveBackProgress
                    scaleX = calculatePredictiveBackScaleX(progress)
                    scaleY = calculatePredictiveBackScaleY(progress)
                    transformOrigin = TransformOrigin(
                        pivotFractionX = 0.5f,
                        pivotFractionY = (sheetOffset + sheetHeight) / sheetHeight
                    )
                }
            },
        shape = shape,
        color = containerColor,
        contentColor = contentColor,
        shadowElevation = shadowElevation,
        tonalElevation = tonalElevation,
    ) {
        Column(
            modifier = Modifier
                .imePadding()
                .fillMaxWidth()
                .graphicsLayer {
                    val predictiveBackScaleX = calculatePredictiveBackScaleX(predictiveBackProgress)
                    val predictiveBackScaleY = calculatePredictiveBackScaleY(predictiveBackProgress)

                    // Preserve the original aspect ratio and alignment of the child content.
                    scaleY = when {
                        predictiveBackScaleY != 0f -> predictiveBackScaleX / predictiveBackScaleY
                        else -> 1f
                    }
                    transformOrigin = PredictiveBackChildTransformOrigin
                }
        ) {
            val scope = remember {
                YDBottomSheetScopeImpl(columnScope = this, onAnimateToDismiss = onAnimateToDismiss)
            }

            scope.content(safeInsets.asPaddingValues())
        }
    }
}

private fun GraphicsLayerScope.calculatePredictiveBackScaleX(progress: Float): Float {
    val width = size.width
    return if (width.isNaN() || width == 0f) {
        1f
    } else {
        1f - lerp(
            start = 0f,
            stop = min(PredictiveBackMaxScaleXDistance.toPx(), width),
            fraction = progress,
        ) / width
    }
}

private fun GraphicsLayerScope.calculatePredictiveBackScaleY(progress: Float): Float {
    val height = size.height
    return if (height.isNaN() || height == 0f) {
        1f
    } else {
        1f - lerp(
            start = 0f,
            stop = min(PredictiveBackMaxScaleYDistance.toPx(), height),
            fraction = progress,
        ) / height
    }
}

internal data class YDBottomSheetProperties(
    val shouldDismissOnBackPress: Boolean = true,
    val shouldDismissOnScrimClick: Boolean = true,
    val securePolicy: SecureFlagPolicy = SecureFlagPolicy.Inherit,
)

private val FlingPositionalThreshold = 56.dp

private val PredictiveBackMaxScaleXDistance = 48.dp
private val PredictiveBackMaxScaleYDistance = 24.dp
private val PredictiveBackChildTransformOrigin = TransformOrigin(
    pivotFractionX = 0.5f,
    pivotFractionY = 0f,
)