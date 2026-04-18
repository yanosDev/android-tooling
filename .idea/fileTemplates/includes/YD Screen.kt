#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME}.${NAME.toLowerCase()}

#end
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle

#if (${ACTION})import ${PACKAGE_NAME_MODEL}.${ACTION}
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
navBack: @Composable () -> Unit,
#if (${VIEWMODEL})
viewModel: ${VIEWMODEL},
#end
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
        state = HomeState.Loading,
        onAction = {}
    )
}