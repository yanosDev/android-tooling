@file:YDRevisionIn(implementedAt = "2026-04-23", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.organisms.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.molecules.scaffold.YDScaffold
import de.yanosdev.styleguide.theme.components.organisms.topbar.YDTopAppBar

@Composable
fun YDDefaultScreen(
    navBack: @Composable () -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    content: @Composable (contentPadding: PaddingValues) -> Unit
) = YDScaffold(
    modifier = modifier,
    topBar = {
        YDTopAppBar(
            title = title,
            navigationIcon = navBack,
            actions = actions
        )
    },
    snackbarHost = snackbarHost,
    content = content
)