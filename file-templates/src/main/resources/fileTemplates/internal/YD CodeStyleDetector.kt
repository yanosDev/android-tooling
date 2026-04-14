package fileTemplates.internal

#if (${ PACKAGE_NAME } && ${ PACKAGE_NAME } != "")package ${ PACKAGE_NAME }

#end
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner

#parse("File Header.java")
class $ {NAME }Detector : Detector(), SourceCodeScanner {

    companion object {
        val issue: Issue = Issue.create(
            id = "${NAME}",
            briefDescription = "${NAME.replaceAll('([A-Z])', ' $1').trim()}",
            explanation = """
                
            """,
            category = Category.CORRECTNESS,
            priority = 3,
            severity = Severity.WARNING,
            implementation = Implementation(
                $ { NAME } Detector ::class.java,
            Scope.JAVA_FILE_SCOPE
        )
        )
    }
}