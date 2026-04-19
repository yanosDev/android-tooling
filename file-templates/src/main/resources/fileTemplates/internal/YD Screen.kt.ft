@file:YDRevisionIn(implementedAt = "${YEAR}-${MONTH}-${DAY}", revisionAfterInDays = 365)

#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME}.${NAME.toLowerCase()}

#end
import de.yanosdev.annotation.YDRevisionIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle

#if (${ACTION})import ${PACKAGE_NAME_MODEL}.${ACTION}
#end
#if (${DATA_NAME})import ${PACKAGE_NAME_MODEL}.${DATA_NAME}
#end
#if (${STATE})import ${PACKAGE_NAME_MODEL}.${STATE}
#end
#if (${VIEWMODEL})
import ${PACKAGE_NAME_VM}.${VIEWMODEL}
import ${PACKAGE_NAME_VM}.${VIEWMODEL}Impl
import de.yanosdev.styleguide.theme.util.core.ScreenWithViewModel
#end

#parse("File Header.java")
@Composable
internal fun ${NAME}Screen(
#if (${VIEWMODEL})
viewModel: ${VIEWMODEL},
#end
navBack: @Composable () -> Unit,
modifier: Modifier = Modifier,
)#if (${VIEWMODEL}) = ScreenWithViewModel<${VIEWMODEL}Impl, ${VIEWMODEL}>(
      viewModel = viewModel
  ) {
  viewModel ->
  #end
    #if (${STATE})
    val state by viewModel.state.collectAsStateWithLifecycle()
    #end
    Content(
        #if (${STATE})
    state = state,
    #end
    #if (${ACTION})
    onAction = { action ->},
    #end
    )
}

@Composable
private fun Content(
    #if (${STATE})
state: ${STATE},
#end
#if (${ACTION})
onAction: (${ACTION}) -> Unit,
#end
modifier: Modifier = Modifier,
) {

}

@Preview
@Composable
private fun Preview() {
    Content(
        state = ${STATE}.Content(data = ${DATA_NAME}()),
        onAction = {}
    )
}