package dev.claudio.adventofcode2022

fun main() {
    Day6Part2().main()
}

private class Day6Part2 {
    fun main() {
        val input: CharArray = Support.readFileAsListString("2022/day6-input.txt")[0].toCharArray()
        val wantedDistinctChars = 14
        for (i in 0..input.size) {
            val distinctChars = (0 until wantedDistinctChars).map { input[it + i] }.toSet().size
            if (distinctChars == wantedDistinctChars) {
                println(i + wantedDistinctChars)
                return
            }
        }
    }
}