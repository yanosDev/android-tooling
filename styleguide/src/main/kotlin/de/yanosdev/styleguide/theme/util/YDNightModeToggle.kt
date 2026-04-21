@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.util

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lightbulb
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.core.util.NightMode
import de.yanosdev.core.util.setNightMode
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcon
import de.yanosdev.styleguide.theme.components.molecules.button.icon.YDIconButtonDefaults
import de.yanosdev.styleguide.theme.components.molecules.button.icon.YDIconToggleButton
import de.yanosdev.styleguide.theme.foundations.token.YDColorTokens

@Composable
fun YDNightModeToggle() {
    val context = LocalContext.current
    val darkModeEnabled = isSystemInDarkTheme()

    YDIconToggleButton(
        checked = darkModeEnabled,
        colors = YDIconButtonDefaults.iconToggleButtonColors(checkedContentColor = YDColorTokens.Orange80),
        onCheckedChange = { checked -> context.setNightMode(if (checked) NightMode.No else NightMode.Yes) },
    ) {
        YDIcon(
            imageVector = Icons.Rounded.Lightbulb,
            contentDescription = null
        )
    }
}