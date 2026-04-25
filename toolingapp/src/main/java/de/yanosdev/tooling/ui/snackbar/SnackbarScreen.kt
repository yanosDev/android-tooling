@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.snackbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.divider.YDDivider
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.molecules.button.YDPrimaryButton
import de.yanosdev.styleguide.theme.components.molecules.button.YDSecondaryButton
import de.yanosdev.styleguide.theme.components.molecules.snackbar.YDSnackbarDuration
import de.yanosdev.styleguide.theme.components.molecules.snackbar.YDSnackbarHost
import de.yanosdev.styleguide.theme.components.molecules.snackbar.YDSnackbarHostState
import de.yanosdev.styleguide.theme.components.organisms.screen.YDDefaultScreen
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContent
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.tooling.ui.snackbar.model.SnackbarAction
import de.yanosdev.tooling.ui.snackbar.model.SnackbarScreenData
import kotlinx.coroutines.launch

@Composable
internal fun SnackbarScreen(
    navBack: @Composable () -> Unit,
    viewModel: SnackbarViewModel,
    modifier: Modifier = Modifier,
) {
    val snackbarState = remember { YDSnackbarHostState() }

    LaunchedEffect(viewModel.navEvents) {
        viewModel.navEvents.collect { navAction ->
            when (navAction) {
                else -> {}
            }
        }
    }

    YDDefaultScreen(
        modifier = modifier,
        navBack = navBack,
        title = "Snackbars",
        snackbarHost = { YDSnackbarHost(hostState = snackbarState) },
    ) { contentPadding ->
        YDUIContent(viewModel = viewModel) {
            Content(contentPadding = contentPadding, snackbarState = snackbarState)
        }
    }
}

@Composable
internal fun YDUIContentScope<SnackbarScreenData, SnackbarAction>.Content(
    contentPadding: PaddingValues,
    snackbarState: YDSnackbarHostState,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()

    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
    ) {
        item {
            YDText(
                text = "Basic",
                style = typography.smRegular,
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
            )
            Column(
                modifier = Modifier.padding(horizontal = spacings.large),
                verticalArrangement = Arrangement.spacedBy(space = spacings.small),
            ) {
                YDPrimaryButton(
                    text = "Show snackbar",
                    onClick = { scope.launch { snackbarState.showSnackbar(message = "File saved successfully") } },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        item { YDDivider(modifier = Modifier.padding(vertical = spacings.medium)) }

        item {
            YDText(
                text = "With action",
                style = typography.smRegular,
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
            )
            Column(
                modifier = Modifier.padding(horizontal = spacings.large),
                verticalArrangement = Arrangement.spacedBy(space = spacings.small),
            ) {
                YDPrimaryButton(
                    text = "Show with action",
                    onClick = {
                        scope.launch {
                            snackbarState.showSnackbar(
                                message = "Connection lost",
                                actionLabel = "Retry",
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                )
                YDSecondaryButton(
                    text = "Show with dismiss icon",
                    onClick = {
                        scope.launch {
                            snackbarState.showSnackbar(
                                message = "Item moved to trash",
                                withDismissAction = true,
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        item { YDDivider(modifier = Modifier.padding(vertical = spacings.medium)) }

        item {
            YDText(
                text = "Duration",
                style = typography.smRegular,
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
            )
            Column(
                modifier = Modifier.padding(horizontal = spacings.large),
                verticalArrangement = Arrangement.spacedBy(space = spacings.small),
            ) {
                YDSecondaryButton(
                    text = "Short (4 s)",
                    onClick = {
                        scope.launch {
                            snackbarState.showSnackbar(
                                message = "Short duration snackbar",
                                duration = YDSnackbarDuration.Short,
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                )
                YDSecondaryButton(
                    text = "Long (10 s)",
                    onClick = {
                        scope.launch {
                            snackbarState.showSnackbar(
                                message = "Long duration snackbar",
                                duration = YDSnackbarDuration.Long,
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                )
                YDSecondaryButton(
                    text = "Indefinite (dismiss manually)",
                    onClick = {
                        scope.launch {
                            snackbarState.showSnackbar(
                                message = "Tap × to dismiss",
                                duration = YDSnackbarDuration.Indefinite,
                                withDismissAction = true,
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@PhonePreview
@Composable
private fun Preview() = YDContentPreview(data = SnackbarScreenData()) {
    Content(contentPadding = PaddingValues(), snackbarState = remember { YDSnackbarHostState() })
}
