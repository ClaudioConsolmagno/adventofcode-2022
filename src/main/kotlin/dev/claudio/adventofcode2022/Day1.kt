package dev.claudio.adventofcode2022

fun main() {
    Day1().main()
}

private class Day1 {
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
        println(summedUpCals.maxOrNull())
    }

}