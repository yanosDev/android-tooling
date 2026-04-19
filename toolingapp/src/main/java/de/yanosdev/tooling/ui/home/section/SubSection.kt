@file:YDRevisionIn(implementedAt = "2026-04-20", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home.section

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.tooling.ui.home.model.SubSectionData


@Composable
internal fun SubSection(
    data: SubSectionData = SubSectionData(),
    onSubAction: () -> Unit = {},
    modifier: Modifier = Modifier,
) {

}

@Preview
@Composable
private fun Preview() {
    SubSection(
        data = SubSectionData(),
        onSubAction = {}
    )
}