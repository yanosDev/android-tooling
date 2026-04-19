@file:YDRevisionIn(implementedAt = "${YEAR}-${MONTH}-${DAY}", revisionAfterInDays = 365)

#if (${PACKAGE_NAME_VM} && ${PACKAGE_NAME_VM} != "")package ${PACKAGE_NAME_VM}

#end
import de.yanosdev.annotation.YDRevisionIn
import ${PACKAGE_NAME_MODEL}.${ACTION}
import ${PACKAGE_NAME_MODEL}.${STATE}

#parse("File Header.java")
interface ${VIEWMODEL} {
    val state: StateFlow<${STATE}>
    fun on${NAME}Action(action: ${NAME}Action)
}