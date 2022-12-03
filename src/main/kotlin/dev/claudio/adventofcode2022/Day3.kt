package dev.claudio.adventofcode2022

fun main() {
    Day3().main()
}

private class Day3 {
    fun main() {
        val inputList: List<String> = Support.readFileAsListString("2022/day3-input.txt")
        val sumOf = inputList
            .map { Pair(it.substring(0, (it.length / 2)), it.substring((it.length / 2), it.length)) }
            .map { commonLetter(it) }
            .map { calcLetterValue(it) }
            .sum()
        println(sumOf)
    }

    private fun calcLetterValue(it: Char): Int {
        return if (it.code > 96) {
            it.code - 96
        } else {
            it.code - 64 + 26
        }
    }

    private fun commonLetter(pair: Pair<String, String>): Char {
        val indexOfFirst: Int = pair.first.indexOfFirst { pair.second.contains(it) }
        return pair.first[indexOfFirst]
    }
}