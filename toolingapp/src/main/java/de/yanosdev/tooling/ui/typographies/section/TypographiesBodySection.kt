@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.typographies.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.foundations.semantics.YDTypography
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.tooling.ui.typographies.model.TypographiesAction
import de.yanosdev.tooling.ui.typographies.model.TypographiesScreenData

@Composable
internal fun YDUIContentScope<TypographiesScreenData, TypographiesAction>.TypographiesBodySection(
    modifier: Modifier = Modifier,
) {
    val typography = typography
    val styles = remember(typography) { typography.mapToKeyValue() }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacings.small),
    ) {
        items(items = styles, key = { it.first }) { (name, style) ->
            TypographyRow(name = name, style = style)
        }
    }
}


@Composable
private fun TypographyRow(
    modifier: Modifier = Modifier,
    name: String,
    style: TextStyle,
) = Column(
    modifier = modifier
        .fillMaxWidth()
        .padding(vertical = spacings.small, horizontal = spacings.medium),
    verticalArrangement = Arrangement.spacedBy(spacings.tiny),
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        YDText(
            modifier = Modifier.weight(1f),
            text = name,
            style = typography.xsRegular,
            color = colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        YDText(
            text = style.formatMetadata(),
            style = typography.xsRegular,
            color = colorScheme.onSurfaceVariant,
            maxLines = 1,
        )
    }

    YDText(
        text = SAMPLE_TEXT,
        style = style,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
    )
}

private const val SAMPLE_TEXT = "The quick brown fox jumps over the lazy dog"

internal fun YDTypography.mapToKeyValue(): List<Pair<String, TextStyle>> = listOf(
    "h1" to h1,
    "h2" to h2,
    "h3" to h3,
    "h4" to h4,
    "h5" to h5,
    "lgMediumBold" to lgMediumBold,
    "lgRegular" to lgRegular,
    "mdMediumBold" to mdMediumBold,
    "mdRegular" to mdRegular,
    "smMediumBold" to smMediumBold,
    "smRegular" to smRegular,
    "xsMediumBold" to xsMediumBold,
    "xsRegular" to xsRegular,
)

private fun TextStyle.formatMetadata(): String {
    val size = fontSize.takeIf { it.isSp }?.value?.toInt()?.let { "${it}sp" }
    val weight = fontWeight?.weight
    val italic = "italic".takeIf { fontStyle == FontStyle.Italic }
    return listOfNotNull(size, weight, italic).joinToString(" · ")
}

@PhonePreview
@Composable
private fun Preview() = YDContentPreview(data = TypographiesScreenData()) { TypographiesBodySection() }