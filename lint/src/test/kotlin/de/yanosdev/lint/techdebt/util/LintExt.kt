package de.yanosdev.lint.techdebt.util

import com.android.tools.lint.checks.infrastructure.TestFile
import com.android.tools.lint.checks.infrastructure.TestLintTask.lint
import com.android.tools.lint.checks.infrastructure.TestMode
import com.android.tools.lint.detector.api.Issue
import com.chrono24.mobile.lint.util.stubs.clickableStub
import com.chrono24.mobile.lint.util.stubs.composableStub
import com.chrono24.mobile.lint.util.stubs.modifierStub
import com.chrono24.mobile.lint.util.stubs.paddingValuesStub
import com.chrono24.mobile.lint.util.stubs.serializableStub

fun ydlint(issue: Issue, vararg files: TestFile) =
    lint()
        .files(
            serializableStub(),
            modifierStub(),
            clickableStub(),
            composableStub(),
            paddingValuesStub(),
            *files
        )
        .skipTestModes(
            TestMode.JVM_OVERLOADS,
            TestMode.REORDER_ARGUMENTS,
            TestMode.WHITESPACE,
        )
        .allowDuplicates()
        .issues(issue)