@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.molecules.button.YDPrimaryButton
import de.yanosdev.styleguide.theme.components.molecules.dialog.YDAlertDialog
import de.yanosdev.styleguide.theme.components.organisms.screen.YDDefaultScreen
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContent
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.tooling.ui.dialog.model.DialogAction
import de.yanosdev.tooling.ui.dialog.model.DialogScreenData

@Composable
internal fun DialogScreen(
    navBack: @Composable () -> Unit,
    viewModel: DialogViewModel,
    modifier: Modifier = Modifier,
) = YDDefaultScreen(
    modifier = modifier,
    navBack = navBack,
    title = "Alert Dialog",
) { contentPadding ->
    LaunchedEffect(viewModel.navEvents) {
        viewModel.navEvents.collect { navAction ->
            when (navAction) {
                else -> {}
            }
        }
    }

    YDUIContent(viewModel = viewModel) {
        Content(contentPadding = contentPadding)
    }
}

@Composable
internal fun YDUIContentScope<DialogScreenData, DialogAction>.Content(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.padding(contentPadding),
        contentPadding = PaddingValues(all = spacings.large),
        verticalArrangement = Arrangement.spacedBy(spacings.large),
    ) {
        item { ConfirmOnlyDemo() }
        item { ConfirmDismissDemo() }
        item { WithTitleDemo() }
        item { FullDemo() }
    }
}

@Composable
private fun ConfirmOnlyDemo(modifier: Modifier = Modifier) {
    var showDialog by remember { mutableStateOf(value = false) }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(spacings.small)) {
        YDText(text = "Confirm only", style = typography.lgMediumBold)
        YDPrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Open Dialog",
            onClick = { showDialog = true },
        )
    }

    if (showDialog) {
        YDAlertDialog(
            confirmText = "OK",
            onConfirmClick = { showDialog = false },
            onDismissRequest = { showDialog = false },
        )
    }
}

@Composable
private fun ConfirmDismissDemo(modifier: Modifier = Modifier) {
    var showDialog by remember { mutableStateOf(value = false) }
    var result by remember { mutableStateOf(value = "") }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(spacings.small)) {
        YDText(text = "Confirm + Dismiss", style = typography.lgMediumBold)
        if (result.isNotEmpty()) {
            YDText(text = "Last tapped: $result", style = typography.mdRegular)
        }
        YDPrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Open Dialog",
            onClick = { showDialog = true },
        )
    }

    if (showDialog) {
        YDAlertDialog(
            confirmText = "Confirm",
            onConfirmClick = {
                result = "Confirm"
                showDialog = false
            },
            onDismissRequest = { showDialog = false },
            dismissText = "Cancel",
            onDismissClick = {
                result = "Cancel"
                showDialog = false
            },
        )
    }
}

@Composable
private fun WithTitleDemo(modifier: Modifier = Modifier) {
    var showDialog by remember { mutableStateOf(value = false) }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(spacings.small)) {
        YDText(text = "With title", style = typography.lgMediumBold)
        YDPrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Open Dialog",
            onClick = { showDialog = true },
        )
    }

    if (showDialog) {
        YDAlertDialog(
            confirmText = "OK",
            onConfirmClick = { showDialog = false },
            onDismissRequest = { showDialog = false },
            dismissText = "Cancel",
            title = "Are you sure?",
        )
    }
}

@Composable
private fun FullDemo(modifier: Modifier = Modifier) {
    var showDialog by remember { mutableStateOf(value = false) }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(spacings.small)) {
        YDText(text = "Title + body text", style = typography.lgMediumBold)
        YDPrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Open Dialog",
            onClick = { showDialog = true },
        )
    }

    if (showDialog) {
        YDAlertDialog(
            confirmText = "Delete",
            onConfirmClick = { showDialog = false },
            onDismissRequest = { showDialog = false },
            dismissText = "Cancel",
            title = "Delete item?",
            text = "This action cannot be undone. The item will be permanently removed.",
        )
    }
}

@PhonePreview
@Composable
private fun Preview() = YDContentPreview(data = DialogScreenData()) {
    Content(contentPadding = PaddingValues())
}
