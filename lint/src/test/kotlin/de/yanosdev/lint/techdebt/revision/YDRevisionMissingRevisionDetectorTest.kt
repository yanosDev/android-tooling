package de.yanosdev.lint.techdebt.revision

import com.android.tools.lint.checks.infrastructure.TestFiles.kotlin
import de.yanosdev.lint.techdebt.util.ydlint
import org.junit.Test

class YDRevisionMissingRevisionDetectorTest {

    @Test
    fun `Annotation is missing`() {
        ydlint(
            issue = YDRevisionMissingRevisionDetector.issue,
            kotlin(
                """
                    package de.yanosdev.lint.techdebt.revision
                    
                    class NonDataClass(val b: Int, val a: Int)
                    """.trimIndent()
            ).indented()
        )
            .run()
            .expectContains("Please make sure,")
    }

    @Test
    fun `Annotation is present`() {
        ydlint(
            issue = YDRevisionMissingRevisionDetector.issue,
            kotlin(
                """
                    @file:YDRevisionIn(implementedAt = "2026-04-16", revisionAfterInDays = 365)
                    package de.yanosdev.lint.techdebt.revision
                    
                    class NonDataClass(val b: Int, val a: Int)
                    """.trimIndent()
            ).indented()
        )
            .run()
            .expectClean()
    }
}