package dev.claudio.adventofcode2022

fun main() {
    Day4Part2().main()
}

private class Day4Part2 {
    fun main() {
        val inputList: List<String> = Support.readFileAsListString("2022/day4-input.txt")
        val sumOf = inputList
            .map { Pair(it.split(",")[0], it.split(",")[1]) }
            .apply { println(this) }
            .map { Pair(
                    IntRange(Integer.valueOf(it.first.split("-")[0]), Integer.valueOf(it.first.split("-")[1])),
                    IntRange(Integer.valueOf(it.second.split("-")[0]), Integer.valueOf(it.second.split("-")[1])),
                )
            }
            .count { pairAnyContains(it) }
        println(sumOf)
    }

    private fun pairFullyContains(it: Pair<IntRange, IntRange>): Boolean {
        return (it.first - it.second).isEmpty() || (it.second - it.first).isEmpty()
    }

    private fun pairAnyContains(it: Pair<IntRange, IntRange>): Boolean {
        return (it.first.intersect(it.second)).isNotEmpty()
    }
}