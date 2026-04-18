#if (${PACKAGE_NAME_VM} && ${PACKAGE_NAME_VM} != "")package ${PACKAGE_NAME_VM}

#end

#if (${STATE})
import ${PACKAGE_NAME_MODEL}.${STATE}
import kotlinx.coroutines.flow.StateFlow
#end

#parse("File Header.java")
interface ${VIEWMODEL} {
    #if (${STATE})
    val state: StateFlow<${STATE}>
    #end
}