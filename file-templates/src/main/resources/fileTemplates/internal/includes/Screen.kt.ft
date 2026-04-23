@file:YDRevisionIn(implementedAt = "${YEAR}-${MONTH}-${DAY}", revisionAfterInDays = 365)

package ${PACKAGE_NAME}.${NAME.toLowerCase()}

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.core.component.screen.YDScreen
import de.yanosdev.core.component.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.components.organisms.screen.YDDefaultScreen
import de.yanosdev.styleguide.theme.components.molecules.scaffold.YDScaffold
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import ${PACKAGE_NAME}.${NAME.toLowerCase()}.model.${NAME}Action
import ${PACKAGE_NAME}.${NAME.toLowerCase()}.model.${NAME}ScreenData

#parse("File Header.java")
@Composable
internal fun ${NAME}Screen(
    navBack: @Composable () -> Unit,
    viewModel: ${NAME}ViewModel,
    modifier: Modifier = Modifier
) = YDDefaultScreen(
      modifier = modifier,
      navBack = navBack,
      title =  "This is the ${NAME}Screen"
  ) { contentPadding ->
    LaunchedEffect(viewModel.navEvents) {
        viewModel.navEvents.collect { navAction ->
            when (navAction) {
                else -> {}
            }
        }
    }

    YDScreen(viewModel = viewModel) {
        Content(
            contentPadding = contentPadding,
        )
    }
}

@Composable
internal fun YDUIContentScope<${NAME}ScreenData, ${NAME}Action>.Content(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    YDText(text = "This is the ${NAME}Screen")
}

@PhonePreview
@Composable
private fun Preview() = YDContentPreview(data = ${NAME}ScreenData()) {
    Content(
        contentPadding = PaddingValues(),
    )
}