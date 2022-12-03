package dev.claudio.adventofcode2022

fun main() {
    Day3Part2().main()
}

private class Day3Part2 {
    fun main() {
        val inputList: List<String> = Support.readFileAsListString("2022/day3-input.txt")
        val accumulator = mutableListOf<String>()
        var sum = 0
        inputList.forEach {
            accumulator.add(it)
            if (accumulator.size == 3) {
                val commonLetter = commonLetter(accumulator)
                println(commonLetter)
                val letterValue = calcLetterValue(commonLetter)
                sum += letterValue
                accumulator.clear()
            }
        }
        println(sum)
    }

    private fun commonLetter(list: MutableList<String>): Char {
        val mutableSetOf = list[0].toCharArray().toMutableSet()
        mutableSetOf.retainAll(list[1].toCharArray().toSet())
        mutableSetOf.retainAll(list[2].toCharArray().toSet())
        return mutableSetOf.first()
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