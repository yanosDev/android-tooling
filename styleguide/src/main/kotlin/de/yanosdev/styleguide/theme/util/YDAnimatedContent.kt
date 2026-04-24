@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.util


import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn

@Composable
internal fun <T : Any> YDAnimatedContent(
    targetState: T,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    label: String = "YDAnimatedScreenContent",
    onContentKey: (targetState: T) -> Any? = { it.javaClass.simpleName },
    onTransitionSpec: AnimatedContentTransitionScope<T>.() -> ContentTransform =
        C24AnimatedScreenContentDefaults.transitionSpec(),
    content: @Composable() AnimatedContentScope.(targetState: T) -> Unit
) {
    AnimatedContent(
        targetState = targetState,
        modifier = modifier,
        transitionSpec = onTransitionSpec,
        contentAlignment = contentAlignment,
        label = label,
        contentKey = onContentKey,
        content = content
    )
}

internal object C24AnimatedScreenContentDefaults {
    fun <T> transitionSpec(): AnimatedContentTransitionScope<T>.() -> ContentTransform = {
        fadeIn() togetherWith fadeOut()
    }
}