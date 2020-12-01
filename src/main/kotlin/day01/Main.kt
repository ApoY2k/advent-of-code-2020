package day01

import common.readInputTextFileInt

fun main() {
    val input = readInputTextFileInt("/day01/input.txt")
    var result: Int? = null
    input.forEach { first ->
        val firstIndex = input.indexOf(first)
        val firstRest = input.subList(firstIndex, input.lastIndex)
        firstRest.forEach { second ->
            val secondIndex = input.indexOf(second)
            val secondRest = input.subList(secondIndex, input.lastIndex)
            secondRest.forEach { third ->
                if (first + second + third == 2020) {
                    result = first * second * third
                    return@forEach
                }
            }
        }
    }
    println("Result: $result")
}
