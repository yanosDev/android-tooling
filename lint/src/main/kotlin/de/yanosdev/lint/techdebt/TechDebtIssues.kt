package de.yanosdev.lint.techdebt

import de.yanosdev.lint.techdebt.codestyle.DataClassParameterSortingCodeStyleDetector
import de.yanosdev.lint.techdebt.codestyle.NamedArgumentCodeStyleDetector
import de.yanosdev.lint.techdebt.codestyle.YDComposableParameterCodeStyleDetector
import de.yanosdev.lint.techdebt.revision.YDRevisionDueRevisionDetector
import de.yanosdev.lint.techdebt.revision.YDRevisionMissingRevisionDetector

internal val techDebtIssues = buildList {
    add(DataClassParameterSortingCodeStyleDetector.issue)
    add(YDRevisionMissingRevisionDetector.issue)
    add(YDRevisionDueRevisionDetector.issue)
    add(YDComposableParameterCodeStyleDetector.issue)
    add(NamedArgumentCodeStyleDetector.issue)
}