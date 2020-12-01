package common

import java.io.File

val dummy = {}.javaClass

fun readInputTextFile(path: String): List<String> {
    return File(dummy.getResource(path).toURI()).readLines()
}

fun readInputTextFileInt(path: String): List<Int> {
    return readInputTextFile(path).map { it.toInt() }
}
