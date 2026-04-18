#if (${PACKAGE_NAME_MODEL} && ${PACKAGE_NAME_MODEL} != "")package ${PACKAGE_NAME_MODEL}

#end
#parse("File Header.java")
internal sealed interface ${ACTION}{
}