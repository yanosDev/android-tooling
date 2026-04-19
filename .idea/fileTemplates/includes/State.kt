@file:YDRevisionIn(implementedAt = "${YEAR}-${MONTH}-${DAY}", revisionAfterInDays = 365)

#if (${PACKAGE_NAME_MODEL} && ${PACKAGE_NAME_MODEL} != "")package ${PACKAGE_NAME_MODEL}

#end
import de.yanosdev.annotation.YDRevisionIn

#parse("File Header.java")
sealed interface ${STATE} {
    data object Loading: ${STATE}
    data class Error(val failure: Exception): ${STATE}
    data class Content(val data: ${DATA_NAME}): ${STATE}
}