@file:YDRevisionIn(implementedAt = "${YEAR}-${MONTH}-${DAY}", revisionAfterInDays = 365)

package ${PACKAGE_NAME}.${NAME.toLowerCase()}

import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.core.component.screen.YDViewModel
import de.yanosdev.core.component.screen.model.UIState
import ${PACKAGE_NAME}.${NAME.toLowerCase()}.model.${NAME}Action
import ${PACKAGE_NAME}.${NAME.toLowerCase()}.model.${NAME}ScreenData

#parse("File Header.java")
internal interface ${NAME}ViewModel : YDViewModel<UIState<${NAME}ScreenData>, ${NAME}Action>