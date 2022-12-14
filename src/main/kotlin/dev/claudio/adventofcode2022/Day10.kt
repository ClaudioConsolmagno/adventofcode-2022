package dev.claudio.adventofcode2022

fun main() {
    Day10().main()
}

private class Day10 {

    fun main() {
        val input = Support.readFileAsListString("2022/day10-input.txt")
        var counter = 1L
        var cycles = mutableListOf(0L, 0L)
        val commands: List<Pair<String, Long>> = input.map {
            val split = it.split(" ")
            if (split.size > 1) {
                Pair(split[0], split[1].toLong())
            } else {
                Pair(split[0], 0)
            }
        }
        var cyclesCounter = 0
        val wantedCycles = setOf(20, 60, 100, 140, 180, 220)
        var total = 0L
        commands.forEachIndexed { idx, it ->
            if (cyclesCounter in wantedCycles) {
                println("$cyclesCounter counter: $counter - addx ${cycles[0]}")
                total += cyclesCounter * counter
            }
            cyclesCounter++
            counter += cycles[0]
            cycles[0] = cycles[1]
            cycles[1] = it.second
            if (it.first == "addx") {
                if (cyclesCounter in wantedCycles) {
                    println("$cyclesCounter counter: $counter - addx ${cycles[0]}")
                    total += cyclesCounter * counter
                }
                cyclesCounter++
                counter += cycles[0]
                cycles[0] = cycles[1]
                cycles[1] = 0
            }
        }
        println(total)
    }

}