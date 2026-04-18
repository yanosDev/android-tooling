#if (${PACKAGE_NAME_VM} && ${PACKAGE_NAME_VM} != "")package ${PACKAGE_NAME_VM}

#end

#if (${STATE})import ${PACKAGE_NAME_MODEL}.${STATE} #end

#parse("File Header.java")
interface ${VIEWMODEL} {
    #if (${STATE})
    internal val state: StateFlow<${STATE}>
    #end
}