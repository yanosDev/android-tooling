package de.yanosdev.lint.techdebt.util

import com.android.tools.lint.checks.infrastructure.TestFile
import com.android.tools.lint.checks.infrastructure.TestLintTask.lint
import com.android.tools.lint.checks.infrastructure.TestMode
import com.android.tools.lint.detector.api.Issue

fun ydlint(issue: Issue, vararg files: TestFile) =
    lint()
        .files(
            *files
        )
        .skipTestModes(
            TestMode.JVM_OVERLOADS,
            TestMode.REORDER_ARGUMENTS,
            TestMode.WHITESPACE
        )
        .allowDuplicates()
        .issues(issue)