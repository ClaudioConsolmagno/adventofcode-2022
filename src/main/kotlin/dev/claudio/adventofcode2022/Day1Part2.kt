package dev.claudio.adventofcode2022

fun main() {
    Day1Part2().main()
}

private class Day1Part2 {
    fun main() {
        val inputList: List<String> = Support.readFileAsListString("2022/day1-input.txt")
        val summedUpCals: MutableList<Int> = mutableListOf(0)
        var counter = 0
        inputList.forEach {
            if (it.isBlank()) {
                counter++
                summedUpCals.add(0)
            } else {
                summedUpCals[counter] += Integer.valueOf(it)
            }
        }
        println(summedUpCals)
        println(summedUpCals.sorted().takeLast(3).sum())
    }

}
