#if (${ PACKAGE_NAME } && ${ PACKAGE_NAME } != "")package ${ PACKAGE_NAME }

#end
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import de.yanosdev.styleguide.YanosDevTheme

#parse("File Header.java")
@Composable
fun $ {
    NAME
}(
modifier: Modifier = Modifier
) {

}

@PhonePreview
@Composable
private fun Preview() = Preview {
    ${ NAME }()
}