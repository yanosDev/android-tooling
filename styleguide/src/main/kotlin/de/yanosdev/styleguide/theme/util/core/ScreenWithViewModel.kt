package de.yanosdev.styleguide.theme.util.core

import androidx.compose.runtime.Composable

@Composable
fun <T, Z> ScreenWithViewModel(
    viewModel: Z,
    content: @Composable (T) -> Unit
) {
    @Suppress("UNCHECKED_CAST")
    content(viewModel as T)
}