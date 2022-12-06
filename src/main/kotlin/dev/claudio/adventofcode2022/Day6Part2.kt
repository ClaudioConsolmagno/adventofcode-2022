package dev.claudio.adventofcode2022

fun main() {
    Day6Part2().main()
}

private class Day6Part2 {
    fun main() {
        val input: String = Support.readFileAsListString("2022/day6-input.txt")[0]
        val wantedDistinctChars = 14
        val result = input.asSequence()
            .windowed(wantedDistinctChars, 1)
            .withIndex()
            .find { it.value.distinct().size == wantedDistinctChars }!!.index + wantedDistinctChars
        println(result)
    }
}