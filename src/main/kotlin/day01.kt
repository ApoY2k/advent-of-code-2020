fun main() {
    val input = readInputTextFileInt("/day01.txt")
    println("Result part 1: ${day01part1(input)}")
    println("Result part 2: ${day01part1(input)}")
}

fun day01part1(input: List<Int>): Int {
    input.forEach { first ->
        val index = input.indexOf(first)
        val rest = input.subList(index, input.lastIndex)
        rest.forEach { second ->
            if (first + second == 2020) {
                return first * second
            }
        }
    }
    throw Exception("No result found")
}

fun day01part2(input: List<Int>): Int {
    input.forEach { first ->
        val firstIndex = input.indexOf(first)
        val firstRest = input.subList(firstIndex, input.lastIndex)
        firstRest.forEach { second ->
            val secondIndex = input.indexOf(second)
            val secondRest = input.subList(secondIndex, input.lastIndex)
            secondRest.forEach { third ->
                if (first + second + third == 2020) {
                    return first * second * third
                }
            }
        }
    }
    throw Exception("No result found")
}