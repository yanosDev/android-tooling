@file:YDRevisionIn(implementedAt = "${YEAR}-${MONTH}-${DAY}", revisionAfterInDays = 365)

#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME}.section

#end
import de.yanosdev.annotation.YDRevisionIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

#if (${ACTION})import ${PACKAGE_NAME}.model.${ACTION}
#end

#if (${DATA_NAME})import ${PACKAGE_NAME}.model.${DATA_NAME}
#end


#parse("File Header.java")
@Composable
internal fun ${NAME}Section(
    ${NAME.toLowerCase()}: ${DATA_NAME},
    on${NAME}Action: () -> Unit,
    modifier: Modifier = Modifier,
) {
  
}

@Preview
@Composable
private fun Preview() {
    ${NAME}Section(
        ${NAME.toLowerCase()} = ${DATA_NAME}(),
        on${NAME}Action = {}
    )
}