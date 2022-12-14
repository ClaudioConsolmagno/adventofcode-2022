package dev.claudio.adventofcode2022

import dev.claudio.adventofcode2022.Support.Companion.printGrid
import dev.claudio.adventofcode2022.Support.Companion.printGridSimple
import java.awt.Point

fun main() {
    Day10Part2().main()
}

private class Day10Part2 {

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
        val grid = mutableListOf<Point>()
        var currentRow = 0
        commands.forEachIndexed { idx, it ->
            println("$cyclesCounter counter: $counter - addx ${cycles[0]}")
            cyclesCounter++
            counter += cycles[0]
            cycles[0] = cycles[1]
            cycles[1] = it.second
            val mod = cyclesCounter % 40
            if (mod == 0) currentRow++
            if (mod-1 in counter-1..counter+1) {
                grid.add(Point(mod-1, currentRow))
            }
            if (it.first == "addx") {
                println("$cyclesCounter counter: $counter - addx ${cycles[0]}")
                cyclesCounter++
                counter += cycles[0]
                cycles[0] = cycles[1]
                cycles[1] = 0
                val mod = cyclesCounter % 40
                if (mod == 0) currentRow++
                if (mod-1 in counter-1..counter+1) {
                    grid.add(Point(mod-1, currentRow))
                }
            }
        }
        grid.printGrid()
    }

}