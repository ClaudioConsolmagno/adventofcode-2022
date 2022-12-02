package dev.claudio.adventofcode2022

fun main() {
    Day2Part2().main()
}

private class Day2Part2 {
    fun main() {
        val inputList: List<String> = Support.readFileAsListString("2022/day2-input.txt")
        val sumOf = inputList.map { Round(it.split(" ")[0], it.split(" ")[1]) }
            .map { println(it.calcResult()); it }
            .sumOf { it.calcResult() }
        println(sumOf)
    }

    private data class Round(val left: String, val right: String) {
        val shapeScores = mapOf(
            "A" to 1, // rock
            "B" to 2, // paper
            "C" to 3, // scissors
            "X" to 1, // rock
            "Y" to 2, // peper
            "Z" to 3, // scissors
        )

        val permutationScores = mapOf(
            Pair("A", "Z") to 3,
            Pair("B", "X") to 1,
            Pair("C", "Y") to 2,
            Pair("C", "X") to 7,
            Pair("A", "Y") to 8,
            Pair("B", "Z") to 9,
        )
        val replacements = mapOf(
            Pair("A", "X") to "Z",
            Pair("A", "Y") to "X",
            Pair("A", "Z") to "Y",
            Pair("B", "X") to "X",
            Pair("B", "Y") to "Y",
            Pair("B", "Z") to "Z",
            Pair("C", "X") to "Y",
            Pair("C", "Y") to "Z",
            Pair("C", "Z") to "X",
        )
        fun calcResult() : Int {
            return permutationScores[Pair(left,replacements[Pair(left, right)])] ?: ((shapeScores[replacements[Pair(left, right)]] ?: 0) + 3)
        }
    }
}