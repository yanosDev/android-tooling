@file:YDRevisionIn(implementedAt = "${YEAR}-${MONTH}-${DAY}", revisionAfterInDays = 365)

package ${PACKAGE_NAME}.${NAME.toLowerCase()}.model

import de.yanosdev.annotation.YDRevisionIn
import androidx.compose.runtime.Immutable

@Immutable
internal data class ${NAME}ScreenData(val isLoading: Boolean = false)