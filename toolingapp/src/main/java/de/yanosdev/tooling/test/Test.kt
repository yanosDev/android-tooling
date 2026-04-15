package de.yanosdev.tooling.test

data class Test(
    val a: Long,
    val b: Long
)

data class TestB(
    val d: Long,
    val a: Long = d,
    val b: Long = a,
    val c: Long = d
)