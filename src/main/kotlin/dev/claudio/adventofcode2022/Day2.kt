package dev.claudio.adventofcode2022

fun main() {
    Day2().main()
}

private class Day2 {
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
        fun calcResult() : Int {
            return permutationScores[Pair(left, right)] ?: ((shapeScores[right] ?: 0) + 3)
        }
    }
}