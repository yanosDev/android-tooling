package de.yanosdev.lint.techdebt.codestyle

import com.android.tools.lint.checks.infrastructure.TestFiles.kotlin
import de.yanosdev.lint.techdebt.util.ydlint
import org.junit.Test

class YDComposableParameterCodeStyleDetectorTest {
    @Test
    fun `No issue reports - correct order`() {
        ydlint(
            issue = YDComposableParameterCodeStyleDetector.issue,
            kotlin(
                """
                    package de.yanosdev.lint.sample
                    
                    import androidx.compose.runtime.Composable
                    import androidx.compose.ui.Modifier
                    
                    @Composable
                    fun MyComposable(
                        navTo: () -> Unit,
                        viewModel: MyViewModel,
                        text: String,
                        onClick: () -> Unit,                        
                        modifier: Modifier = Modifier,
                        content: @Composable () -> Unit
                    ) {
                        // no-op
                    }
                    """.trimIndent()
            ).indented()
        )
            .run()
            .expectClean()
    }

    @Test
    fun `Issue reports - wrong order`() {
        ydlint(
            issue = YDComposableParameterCodeStyleDetector.issue,
            kotlin(
                """
                    package de.yanosdev.lint.sample
                    
                    import androidx.compose.runtime.Composable
                    import androidx.compose.ui.Modifier
                    
                    @Composable
                    fun MyComposable(
                        modifier: Modifier = Modifier,
                        text: String,
                        onClick: () -> Unit,
                        navTo: () -> Unit,
                        viewModel: MyViewModel,
                        content: @Composable () -> Unit
                    ) {
                        // no-op
                    }
                    """.trimIndent()
            ).indented()
        )
            .run()
            .expectContains("The sorting of the parameters is wrong.")
    }

    @Test
    fun `Issue reports - multiple modifiers`() {
        ydlint(
            issue = YDComposableParameterCodeStyleDetector.issue,
            kotlin(
                """
                    package de.yanosdev.lint.sample
                    
                    import androidx.compose.runtime.Composable
                    import androidx.compose.ui.Modifier
                    
                    @Composable
                    fun MyComposable(
                        modifier: Modifier = Modifier,
                        modifier2: Modifier = Modifier
                    ) {
                        // no-op
                    }
                    """.trimIndent()
            ).indented()
        )
            .run()
            .expectContains("There should be only one Modifier.")
    }

    @Test
    fun `Issue reports - required modifier`() {
        ydlint(
            issue = YDComposableParameterCodeStyleDetector.issue,
            kotlin(
                """
                    package de.yanosdev.lint.sample
                    
                    import androidx.compose.runtime.Composable
                    import androidx.compose.ui.Modifier
                    
                    @Composable
                    fun MyComposable(
                        modifier: Modifier
                    ) {
                        // no-op
                    }
                    """.trimIndent()
            ).indented()
        )
            .run()
            .expectContains("")
    }

    @Test
    fun `Issue reports - content lambda not last`() {
        ydlint(
            issue = YDComposableParameterCodeStyleDetector.issue,
            kotlin(
                """
                    package de.yanosdev.lint.sample
                    
                    import androidx.compose.runtime.Composable
                    import androidx.compose.ui.Modifier
                    
                    @Composable
                    fun MyComposable(
                        content: @Composable () -> Unit,
                        modifier: Modifier = Modifier
                    ) {
                        // no-op
                    }
                    """.trimIndent()
            ).indented()
        )
            .run()
            .expectContains("Content Lambda should be last parameter.")
    }

    @Test
    fun `Issue reports - invalid lambda name`() {
        ydlint(
            issue = YDComposableParameterCodeStyleDetector.issue,
            kotlin(
                """
                    package de.yanosdev.lint.sample
                    
                    import androidx.compose.runtime.Composable
                    
                    @Composable
                    fun MyComposable(
                        myLambda: () -> Unit
                    ) {
                        // no-op
                    }
                    """.trimIndent()
            ).indented()
        )
            .run()
            .expectContains("needs to be renamed or turn it into a Composable parameter")
    }
}