@file:YDRevisionIn(implementedAt = "${YEAR}-${MONTH}-${DAY}", revisionAfterInDays = 365)

#if (${PACKAGE_NAME_VM} && ${PACKAGE_NAME_VM} != "")package ${PACKAGE_NAME_VM}

#end
import de.yanosdev.annotation.YDRevisionIn
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ${PACKAGE_NAME_VM}.${VIEWMODEL}

import ${PACKAGE_NAME_MODEL}.${ACTION}
import ${PACKAGE_NAME_MODEL}.${STATE}

#parse("File Header.java")
internal class ${VIEWMODEL}Impl : ViewModel(), ${VIEWMODEL} {
    #if (${STATE})
    private val _state = MutableStateFlow<${STATE}>(${STATE}.Loading)
    override val state = _state.asStateFlow()
    #end

    override fun on${NAME}Action(action: ${NAME}Action) {

    }
}