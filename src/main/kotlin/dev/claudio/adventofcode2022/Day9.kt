package dev.claudio.adventofcode2022

import dev.claudio.adventofcode2022.Support.Companion.printGrid
import java.awt.Point
import kotlin.math.sign

fun main() {
    Day9().main()
}

private class Day9 {

    fun main() {
        val input = Support.readFileAsListString("2022/day9-input.txt")
        val motions: List<Pair<String, Int>> = input
            .map { Pair(it.split(" ")[0], it.split(" ")[1].toInt()) }
        val h_CurrentAt = Point(0,0)
        val t_CurrentAt = Point(0,0)
        val distinct = mutableSetOf(Point(t_CurrentAt.x, t_CurrentAt.y))
        motions.forEach{ motion ->
            var x = 0
            var y = 0
            when(motion.first) {
                "L" -> x--
                "R" -> x++
                "U" -> y++
                "D" -> y--
            }
            for (i in 1..motion.second) {
                h_CurrentAt.translate(x, y)
                if (h_CurrentAt.distance(t_CurrentAt) >= 2) {
                    t_CurrentAt.translate((h_CurrentAt.x - t_CurrentAt.x).sign,(h_CurrentAt.y - t_CurrentAt.y).sign)
                    distinct.add(Point(t_CurrentAt.x, t_CurrentAt.y))
                }
            }
        }
        distinct.printGrid()
        println(distinct.size)
    }

}