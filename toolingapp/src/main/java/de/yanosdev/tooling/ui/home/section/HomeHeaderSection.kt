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
import androidx.compose.ui.tooling.preview.Preview
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.YDSurface
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.themes.YDTheme.sizes
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.YDNightModeToggle
import de.yanosdev.tooling.ui.home.model.HomeHeaderSectionData


@Composable
internal fun HomeHeaderSection(
    data: HomeHeaderSectionData,
    onHomeHeaderAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    YDSurface(color = colorScheme.primary) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = spacings.b16)
                .height(sizes.s72),
            verticalArrangement = Arrangement.spacedBy(spacings.b16)
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

@Preview
@Composable
private fun Preview() {
    HomeHeaderSection(
        data = HomeHeaderSectionData(),
        onHomeHeaderAction = {}
    )
}