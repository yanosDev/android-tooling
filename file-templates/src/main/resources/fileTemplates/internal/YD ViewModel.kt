#if (${ PACKAGE_NAME } && ${ PACKAGE_NAME } != "")package ${ PACKAGE_NAME }

#end
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class $ {NAME }ViewModel : ViewModel() {

    private val _uiState = MutableStateFlow($ { NAME } UiState ())
    val uiState: StateFlow<${ NAME } UiState > = _uiState.asStateFlow()

    // TODO: Add events and business logic
}

data class $ {NAME }UiState(
val isLoading: Boolean = false,
val error: String? = null,
)
