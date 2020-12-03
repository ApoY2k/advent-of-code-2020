import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

fun main() {
    val input = readInputTextFile("/day03.txt")
    println("Result part 1: ${day03part1(input)}")
    println("Result part 2: ${day03part2(input)}")
}

fun day03part1(input: List<String>): Long {
    var colIdx = 0
    var result = 0L

    input.asRows().forEach { row ->
        if (row.hasTreeAt(colIdx)) {
            result++
        }

        colIdx += 3
    }

    return result
}

fun day03part2(input: List<String>): Long? {
    val rows = input.asRows()
    var result: Long? = null

    val slope1Result = GlobalScope.async {
        var colIdx = 0
        var result = 0L

        rows.forEach { row ->
            if (row.hasTreeAt(colIdx)) {
                result++
            }

            colIdx += 1
        }

        return@async result
    }

    val slope2Result = GlobalScope.async {
        var colIdx = 0
        var result = 0L

        rows.forEach { row ->
            if (row.hasTreeAt(colIdx)) {
                result++
            }

            colIdx += 3
        }

        return@async result
    }

    val slope3Result = GlobalScope.async {
        var colIdx = 0
        var result = 0L

        rows.forEach { row ->
            if (row.hasTreeAt(colIdx)) {
                result++
            }

            colIdx += 5
        }

        return@async result
    }

    val slope4Result = GlobalScope.async {
        var colIdx = 0
        var result = 0L

        rows.forEach { row ->
            if (row.hasTreeAt(colIdx)) {
                result++
            }

            colIdx += 7
        }

        return@async result
    }

    val slope5Result = GlobalScope.async {
        var colIdx = 0
        var result = 0L

        rows.filterIndexed { idx, _ -> idx % 2 == 0 }
            .forEach { row ->
                if (row.hasTreeAt(colIdx)) {
                    result++
                }

                colIdx += 1
            }

        return@async result
    }

    runBlocking {
        result = awaitAll(
            slope1Result,
            slope2Result,
            slope3Result,
            slope4Result,
            slope5Result
        ).reduce { acc, i ->
            acc * i
        }
    }

    return result
}

fun List<String>.asRows(): List<Row> {
    return this.map { Row(it) }
}

data class Row(val input: String) {
    fun hasTreeAt(index: Int): Boolean {
        var idx = index

        if (idx > input.length - 1) {
            idx %= input.length
        }

        return input[idx] == '#'
    }
}
