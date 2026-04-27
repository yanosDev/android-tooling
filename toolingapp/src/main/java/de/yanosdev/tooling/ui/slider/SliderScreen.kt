@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.slider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.divider.YDDivider
import de.yanosdev.styleguide.theme.components.atoms.slider.YDSlider
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.organisms.screen.YDDefaultScreen
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContent
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.tooling.ui.slider.model.SliderAction
import de.yanosdev.tooling.ui.slider.model.SliderScreenData
import kotlin.math.roundToInt

@Composable
internal fun SliderScreen(
    navBack: @Composable () -> Unit,
    viewModel: SliderViewModel,
    modifier: Modifier = Modifier,
) = YDDefaultScreen(
    modifier = modifier,
    navBack = navBack,
    title = "Sliders",
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
internal fun YDUIContentScope<SliderScreenData, SliderAction>.Content(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    var continuous by remember { mutableFloatStateOf(value = 0.5f) }
    var ranged by remember { mutableFloatStateOf(value = 30f) }
    var discrete by remember { mutableFloatStateOf(value = 0f) }
    var steps3 by remember { mutableFloatStateOf(value = 0f) }

    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
    ) {
        item {
            YDText(
                text = "Continuous",
                style = typography.smRegular,
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
            )
            Column(modifier = Modifier.padding(horizontal = spacings.large)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    YDText(text = "Volume", style = typography.mdRegular)
                    YDText(
                        text = "${(continuous * 100).roundToInt()}%",
                        style = typography.mdMediumBold,
                    )
                }
                YDSlider(
                    value = continuous,
                    onValueChange = { continuous = it },
                )
            }
        }

        item { YDDivider(modifier = Modifier.padding(vertical = spacings.medium)) }

        item {
            YDText(
                text = "Custom range",
                style = typography.smRegular,
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
            )
            Column(modifier = Modifier.padding(horizontal = spacings.large)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    YDText(text = "Price limit", style = typography.mdRegular)
                    YDText(
                        text = "€${ranged.roundToInt()}",
                        style = typography.mdMediumBold,
                    )
                }
                YDSlider(
                    value = ranged,
                    onValueChange = { ranged = it },
                    valueRange = 0f..100f,
                )
            }
        }

        item { YDDivider(modifier = Modifier.padding(vertical = spacings.medium)) }

        item {
            YDText(
                text = "Discrete (4 steps)",
                style = typography.smRegular,
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
            )
            Column(modifier = Modifier.padding(horizontal = spacings.large)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    YDText(text = "Quality", style = typography.mdRegular)
                    YDText(
                        text = "${(discrete * 100).roundToInt()}%",
                        style = typography.mdMediumBold,
                    )
                }
                YDSlider(
                    value = discrete,
                    onValueChange = { discrete = it },
                    steps = 4,
                )
            }
        }

        item { YDDivider(modifier = Modifier.padding(vertical = spacings.medium)) }

        item {
            YDText(
                text = "Discrete — rating (1–5)",
                style = typography.smRegular,
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
            )
            Column(modifier = Modifier.padding(horizontal = spacings.large)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    YDText(text = "Rating", style = typography.mdRegular)
                    YDText(
                        text = steps3.roundToInt().toString(),
                        style = typography.mdMediumBold,
                    )
                }
                YDSlider(
                    value = steps3,
                    onValueChange = { steps3 = it },
                    valueRange = 1f..5f,
                    steps = 3,
                )
            }
        }

        item { YDDivider(modifier = Modifier.padding(vertical = spacings.medium)) }

        item {
            YDText(
                text = "Disabled",
                style = typography.smRegular,
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
            )
            Column(modifier = Modifier.padding(horizontal = spacings.large)) {
                YDSlider(value = 0.6f, onValueChange = {}, enabled = false)
                YDSlider(value = 0.6f, onValueChange = {}, enabled = false, steps = 3)
            }
        }
    }
}

@PhonePreview
@Composable
private fun Preview() = YDContentPreview(data = SliderScreenData()) {
    Content(contentPadding = PaddingValues())
}
