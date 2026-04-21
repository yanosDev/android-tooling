@file:YDRevisionIn(implementedAt = "${YEAR}-${MONTH}-${DAY}", revisionAfterInDays = 365)

package ${PACKAGE_NAME_SECTION}

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.util.core.viewmodel.YDUIContentScope
import de.yanosdev.tooling.ui.home.model.HomeAction
import de.yanosdev.tooling.ui.home.model.HomeScreenData

#parse("File Header.java")
@Composable
internal fun YDUIContentScope<${SCREEN}ScreenData, ${SCREEN}Action>.${NAME}(
    modifier: Modifier = Modifier,
) {
    YDText(text = "TODO: This is the ${NAME}")
}

@PhonePreview
@Composable
private fun Preview() = YDContentPreview(data = ${SCREEN}ScreenData()) { ${NAME}() }