import java.util.regex.Pattern

fun main() {
    val input = readInputTextFile("/day02.txt")
    println("Result part 1: ${day02part1(input)}")
    println("Result part 2: ${day02part2(input)}")
}

fun day02part1(input: List<String>): Int {
    var result = 0

    parseInput(input).forEach { el ->
        val count = el.password.count { it == el.letter }
        if (el.a <= count && count <= el.b) {
            result++
        }
    }

    return result
}

fun day02part2(input: List<String>): Int {
    var result = 0

    parseInput(input).forEach { el ->
        val posA = el.password[el.a - 1]
        val posB = el.password[el.b - 1]

        if (posA == el.letter && posB == el.letter || posA != el.letter && posB != el.letter) {
            return@forEach
        }

        result++
    }

    return result
}

fun parseInput(lines: List<String>): Sequence<Input> {
    val pattern = Pattern.compile("(?<a>\\d+)-(?<b>\\d+) (?<letter>[a-z]): (?<password>[a-z]+)")

    return sequence {
        lines.forEach {
            val matcher = pattern.matcher(it)
            if (!matcher.find()) {
                throw Exception("Could not match input [$it]")
            }

            val a = matcher.group("a").toInt()
            val b = matcher.group("b").toInt()
            val letter = matcher.group("letter").toCharArray()[0]
            val password = matcher.group("password")

            yield(Input(a, b, letter, password))
        }
    }
}

data class Input(val a: Int, val b: Int, val letter: Char, val password: String)
