#if (${ PACKAGE_NAME_VM } && ${ PACKAGE_NAME_VM } != "")package ${ PACKAGE_NAME_VM }

#end
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ${PACKAGE_NAME_VM}.${VIEWMODEL}

#if (${ STATE })import ${PACKAGE_NAME_MODEL}.${STATE} #end

#parse("File Header.java")
class ${VIEWMODEL}Impl : ViewModel(), ${VIEWMODEL} {
    #if (${STATE})
    private val _state = MutableStateFlow<${STATE}>(${STATE}.Loading)
    internal val state = _state.asStateFlow()
    #end
}