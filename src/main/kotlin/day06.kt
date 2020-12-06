fun main() {
    println("Result part 1: ${day06part1(readBlocks("/day06.txt", ""))}")
    println("Result part 2: ${day06Part2(readBlocks("/day06.txt"))}")
}

fun day06part1(input: List<String>): Int {
    return input
        .map { it.toCharArray().toSet().count() }
        .reduce { acc, i -> acc + i }
}

fun day06Part2(input: List<String>): Int {
    return input
        .map { group ->
            // Collect counts for each answer
            val answers = mutableMapOf<Char, Int>()
            group
                .replace(" ", "")
                .toCharArray()
                .sorted()
                .forEach {
                    val count = answers.getOrDefault(it, 0)
                    answers[it] = count + 1
                }

            // Remove all answers where the count is not equal to the total amount of people in the group
            val peopleCount = group.split(" ").count()
            answers
                .filterValues { it != peopleCount }
                .keys
                .forEach { answers.remove(it) }

            // Sum up amount of answers (which are just the ones *all* people answered
            answers.count()
        }
        .reduce { acc, i -> acc + i }
}
