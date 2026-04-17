package de.yanosdev.lint.techdebt.codestyle

import com.android.tools.lint.checks.infrastructure.TestFiles
import com.android.tools.lint.checks.infrastructure.TestLintTask
import org.junit.Test

class DataClassParameterSortingCodeStyleDetectorTest {

    @Test
    fun `Non data class processing`() {
        TestLintTask.lint()
            .files(
                TestFiles.kotlin(
                    """
                    package de.yanosdev.lint.techdebt.codestyle
                    
                    class NonDataClass(val b: Int, val a: Int)
                    """
                ).indented()
            )
            .issues(DataClassParameterSortingCodeStyleDetector.issue)
            .run()
            .expectClean()
    }

    @Test
    fun `Data class with no constructors`() {
        TestLintTask.lint()
            .files(
                TestFiles.kotlin(
                    """
                    package de.yanosdev.lint.techdebt.codestyle
                    
                    data class MyDataClass
                    """
                ).indented()
            )
            .issues(DataClassParameterSortingCodeStyleDetector.issue)
            .run()
            .expectClean()
    }

    @Test
    fun `Data class with single constructor  no parameters`() {
        TestLintTask.lint()
            .files(
                TestFiles.kotlin(
                    """
                    package de.yanosdev.lint.techdebt.codestyle
                    
                    data class MyDataClass()
                    """
                ).indented()
            )
            .issues(DataClassParameterSortingCodeStyleDetector.issue)
            .run()
            .expectClean()
    }

    @Test
    fun `Data class with single constructor  one parameter`() {
        TestLintTask.lint()
            .files(
                TestFiles.kotlin(
                    """
                    package de.yanosdev.lint.techdebt.codestyle
                    
                    data class MyDataClass(val a: Int)
                    """
                ).indented()
            )
            .issues(DataClassParameterSortingCodeStyleDetector.issue)
            .run()
            .expectClean()
    }

    @Test
    fun `Data class with single constructor  one default parameter`() {
        TestLintTask.lint()
            .files(
                TestFiles.kotlin(
                    """
                    package de.yanosdev.lint.techdebt.codestyle
                    
                    data class MyDataClass(val a: Int = 0)
                    """
                ).indented()
            )
            .issues(DataClassParameterSortingCodeStyleDetector.issue)
            .run()
            .expectClean()
    }

    @Test
    fun `Data class with multiple constructors  including duplicated overloads`() {
        TestLintTask.lint()
            .files(
                TestFiles.kotlin(
                    """
                    package de.yanosdev.lint.techdebt.codestyle
                    
                    data class MyDataClass(val a: Int, val b: Int) {
                        constructor(a: Int) : this(a, 0)
                    }
                    """
                ).indented()
            )
            .issues(DataClassParameterSortingCodeStyleDetector.issue)
            .run()
            .expectClean()
    }

    @Test
    fun `Data class with correctly sorted non default parameters`() {
        TestLintTask.lint()
            .files(
                TestFiles.kotlin(
                    """
                    package de.yanosdev.lint.techdebt.codestyle
                    
                    data class MyDataClass(val a: Int, val b: Int)
                    """
                ).indented()
            )
            .issues(DataClassParameterSortingCodeStyleDetector.issue)
            .run()
            .expectClean()
    }

    @Test
    fun `Data class with incorrectly sorted non default parameters`() {
        TestLintTask.lint()
            .files(
                TestFiles.kotlin(
                    """
                    package de.yanosdev.lint.techdebt.codestyle
                    
                    data class MyDataClass(val b: Int, val a: Int)
                    """
                ).indented()
            )
            .issues(DataClassParameterSortingCodeStyleDetector.issue)
            .run()
            .expectContains(
                """
                is out of alphabetical order. [DataClassParameterSorting]
                """.trimIndent()
            )
    }

    @Test
    fun `Data class with correctly sorted default parameters`() {
        TestLintTask.lint()
            .files(
                TestFiles.kotlin(
                    """
                    package de.yanosdev.lint.techdebt.codestyle
                    
                    data class MyDataClass(val a: Int = 0, val b: Int = 0)
                    """
                ).indented()
            )
            .issues(DataClassParameterSortingCodeStyleDetector.issue)
            .run()
            .expectClean()
    }

    @Test
    fun `Data class with incorrectly sorted default parameters`() {
        TestLintTask.lint()
            .files(
                TestFiles.kotlin(
                    """
                    package de.yanosdev.lint.techdebt.codestyle
                    
                    data class MyDataClass(val b: Int = 0, val a: Int = 0)
                    """
                ).indented()
            )
            .issues(DataClassParameterSortingCodeStyleDetector.issue)
            .run()
            .expectContains(
                """
                is out of alphabetical order. [DataClassParameterSorting]
                """.trimIndent()
            )
    }

    @Test
    fun `Data class with correctly sorted mixed parameters`() {
        TestLintTask.lint()
            .files(
                TestFiles.kotlin(
                    """
                    package de.yanosdev.lint.techdebt.codestyle
                    
                    data class MyDataClass(val a: Int, val b: Int, val c: Int = 0, val d: Int = 0)
                    """
                ).indented()
            )
            .issues(DataClassParameterSortingCodeStyleDetector.issue)
            .run()
            .expectClean()
    }

    @Test
    fun `Data class with mixed parameters  non default group incorrectly sorted`() {
        TestLintTask.lint()
            .files(
                TestFiles.kotlin(
                    """
                    package de.yanosdev.lint.techdebt.codestyle
                    
                    data class MyDataClass(val b: Int, val a: Int, val c: Int = 0, val d: Int = 0)
                    """
                ).indented()
            )
            .issues(DataClassParameterSortingCodeStyleDetector.issue)
            .run()
            .expectContains(
                """
                is out of alphabetical order. [DataClassParameterSorting]
                """.trimIndent()
            )
    }

    @Test
    fun `Data class with mixed parameters  default group incorrectly sorted`() {
        TestLintTask.lint()
            .files(
                TestFiles.kotlin(
                    """
                    package de.yanosdev.lint.techdebt.codestyle
                    
                    data class MyDataClass(val a: Int, val b: Int, val d: Int = 0, val c: Int = 0)
                    """
                ).indented()
            )
            .issues(DataClassParameterSortingCodeStyleDetector.issue)
            .run()
            .expectContains(
                """
                is out of alphabetical order. [DataClassParameterSorting]
                """.trimIndent()
            )
    }

    @Test
    fun `Data class with mixed parameters  default parameter before non default`() {
        TestLintTask.lint()
            .files(
                TestFiles.kotlin(
                    """
                    package de.yanosdev.lint.techdebt.codestyle
                    
                    data class MyDataClass(val b: String = "", val a: Int)
                    """
                ).indented()
            )
            .issues(DataClassParameterSortingCodeStyleDetector.issue)
            .run()
            .expectClean()
    }
}