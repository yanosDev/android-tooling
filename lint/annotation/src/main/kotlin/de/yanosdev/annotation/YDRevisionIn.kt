package de.yanosdev.annotation

@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class YDRevisionIn(val implementedAt: String = "", val revisionAfterInDays: Long = 0L)
