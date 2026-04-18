#if (${PACKAGE_NAME_MODEL} && ${PACKAGE_NAME_MODEL} != "")package ${PACKAGE_NAME_MODEL}

#end

#parse("File Header.java")
internal sealed interface ${STATE} {
    data object Loading: ${STATE}
    data class Error(val failure: Exception): ${STATE}
    data class Content(val data: Unit): ${STATE}
}