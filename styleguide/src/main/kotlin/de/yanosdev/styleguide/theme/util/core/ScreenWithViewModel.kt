@file:YDRevisionIn(implementedAt = "2026-04-18", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.util.core

import androidx.compose.runtime.Composable
import de.yanosdev.annotation.YDRevisionIn

@Composable
fun <T, Z> ScreenWithViewModel(
    viewModel: Z,
    content: @Composable (T) -> Unit
) {
    @Suppress("UNCHECKED_CAST")
    content(viewModel as T)
}