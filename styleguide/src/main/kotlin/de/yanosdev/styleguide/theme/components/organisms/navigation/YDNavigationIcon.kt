@file:YDRevisionIn(implementedAt = "2026-04-23", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.organisms.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcon
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcons
import de.yanosdev.styleguide.theme.components.molecules.button.icon.YDIconButton
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview

@Composable
fun YDNavigationCloseIcon(
    navUp: () -> Unit,
    modifier: Modifier = Modifier
) = YDIconButton(onClick = navUp, modifier = modifier) {
    YDIcon(
        imageVector = YDIcons.Close,
        contentDescription = null
    )
}

@Composable
fun YDNavigationUpIcon(
    navUp: () -> Unit,
    modifier: Modifier = Modifier
) = YDIconButton(onClick = navUp, modifier = modifier) {
    YDIcon(
        imageVector = YDIcons.ArrowLeft,
        contentDescription = null
    )
}

@PhonePreview
@Composable
private fun Preview() = YDPreview {
    Column(
        modifier = Modifier.padding(spacings.small),
        verticalArrangement = Arrangement.spacedBy(spacings.small)
    ) {
        YDNavigationUpIcon(navUp = {})
        YDNavigationCloseIcon(navUp = {})
    }
}