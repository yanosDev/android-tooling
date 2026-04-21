@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.YDSurface
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.themes.YDTheme.sizes
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDNightModeToggle

@Composable
internal fun HomeHeaderSection(
    modifier: Modifier = Modifier,
) {
    YDSurface(color = colorScheme.primary) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = spacings.large)
                .height(sizes.huge),
            verticalArrangement = Arrangement.spacedBy(spacings.large)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                YDText(text = "StyleGuide", style = typography.h1)
                YDNightModeToggle()
            }
        }
    }
}


@PhonePreview
@Composable
private fun Preview() = HomeHeaderSection()