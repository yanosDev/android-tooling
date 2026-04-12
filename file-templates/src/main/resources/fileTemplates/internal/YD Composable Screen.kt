#if (${ PACKAGE_NAME } && ${ PACKAGE_NAME } != "")package ${ PACKAGE_NAME }

#end
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import de.yanosdev.styleguide.YanosDevTheme

@Composable
fun $ {
    NAME
}Screen() {
    // TODO: Implement screen content
}

@Preview(showBackground = true)
@Composable
private fun $ {
    NAME
}ScreenPreview() {
    YanosDevTheme {
        ${ NAME } Screen ()
    }
}
