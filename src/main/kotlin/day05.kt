fun main() {
    val input = readInputTextFile("/day05.txt")
    println("Result part 1: ${part1(input)}")
    println("Result part 1: ${part2(input)}")
}

fun part1(input: List<String>): Int {
    return input
        .map { it.getSeatNumber() }
        .reduce { acc, i -> acc.coerceAtLeast(i) }
}

fun part2(input: List<String>): Int {
    val seatNumbers = input.map { it.getSeatNumber() }.sorted()
    val gaps = seatNumbers.filterIndexed { index, seatNumber ->
        index > 0 && index < seatNumbers.lastIndex && seatNumber - seatNumbers[index - 1] > 1
    }
    return gaps[0] - 1
}

fun String.getSeatNumber(): Int {
    val row = (0..127).applyCode(this.take(7))
    val col = (0..7).applyCode(this.takeLast(3))
    return row * 8 + col;
}

fun IntRange.applyCode(code: String): Int {
    var result = this.toList()

    code.forEach {
        when (it) {
            'F', 'L' -> result = result.subList(0, result.size / 2)
            'B', 'R' -> result = result.subList(result.size / 2, result.size)
        }
    }


    return result[0]
}
