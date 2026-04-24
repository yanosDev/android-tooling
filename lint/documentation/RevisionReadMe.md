# Revision Lint Rules

Detectors that enforce the revision annotation workflow, ensuring all files are periodically reviewed.

## YDRevisionMissing — WARNING

Every `.kt` file must have a `@file:YDRevisionIn` annotation at the top.

```kotlin
@file:YDRevisionIn(implementedAt = "2026-01-01", revisionAfterInDays = 365)
```

Add this to any `.kt` file that is missing it. Use today's date for `implementedAt`.

Import: `de.yanosdev.annotation.YDRevisionIn`

---

## YDRevisionDue — WARNING

Fires when the revision date (`implementedAt + revisionAfterInDays`) has passed.

When triggered, review the file and update `implementedAt` to today's date. Do not update the date without actually
reviewing the file.