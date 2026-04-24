package de.yanosdev.lint.techdebt.revision

import com.android.tools.lint.checks.infrastructure.TestFiles.kotlin
import de.yanosdev.lint.techdebt.util.ydlint
import org.junit.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class YDRevisionDueRevisionDetectorTest {
    @Test
    fun `YDRevisionIn is due`() {
        ydlint(
            issue = YDRevisionDueRevisionDetector.issue,
            kotlin(
                """
                    @file:de.yanosdev.annotation.YDRevisionIn(implementedAt = "2020-04-16", revisionAfterInDays = 365)
                    package de.yanosdev.lint.techdebt.revision
                    
                    class NonDataClass(val b: Int, val a: Int)
                    """.trimIndent()
            ).indented()
        )
            .run()
            .expectContains("This file needs revision.")
    }

    @Test
    fun `YDRevisionIn is further in the future`() {
        val today = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
        ydlint(
            issue = YDRevisionDueRevisionDetector.issue,
            kotlin(
                """
                    @file:YDRevisionIn(implementedAt = "$today", revisionAfterInDays = 365)
                    package de.yanosdev.lint.techdebt.revision
                    
                    class NonDataClass(val b: Int, val a: Int)
                    """.trimIndent()
            ).indented()
        )
            .run()
            .expectClean()
    }
}