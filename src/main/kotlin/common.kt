import java.io.File

val dummy = {}.javaClass

fun readInput(path: String): String {
    return File(dummy.getResource(path).toURI()).readText().trim()
}

fun readInputTextFile(path: String): List<String> {
    return File(dummy.getResource(path).toURI()).readLines()
}

fun readInputTextFileInt(path: String): List<Int> {
    return readInputTextFile(path).map { it.toInt() }
}

/**
 * Read in blocks separated by two newlines, and concatenate the lines inside each block
 * by replacing it with a new string
 */
fun readBlocks(path: String, newlineReplace: String = " "): List<String> {
    return readInput(path)
        .split(Regex("(\\r\\n){2}"))
        .map { it.replace(Regex("\\r\\n"), newlineReplace) }
}
