import java.io.File

val dummy = {}.javaClass

fun readInput(path: String): String {
    return File(dummy.getResource(path).toURI()).readText()
}

fun readInputTextFile(path: String): List<String> {
    return File(dummy.getResource(path).toURI()).readLines()
}

fun readInputTextFileInt(path: String): List<Int> {
    return readInputTextFile(path).map { it.toInt() }
}