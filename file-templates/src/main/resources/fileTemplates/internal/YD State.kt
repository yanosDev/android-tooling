#if (${ PACKAGE_NAME_MODEL } && ${ PACKAGE_NAME_MODEL } != "")package ${ PACKAGE_NAME_MODEL }

#end
import kotlin.Result

#parse("File Header.java")
internal sealed interface ${STATE} {
    data object Loading: ${STATE}
    data class Error(val failure: Result.Failure): ${STATE}
    data class Content(val data: Unit): ${STATE}
}