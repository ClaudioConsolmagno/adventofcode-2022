package dev.claudio.adventofcode2022

import dev.claudio.adventofcode2022.Support.Companion.printGrid
import java.awt.Point
import kotlin.math.sign

fun main() {
    Day9Part2().main()
}

private class Day9Part2 {

    fun main() {
        val input = Support.readFileAsListString("2022/day9-input.txt")
        val motions: List<Pair<String, Int>> = input
            .map { Pair(it.split(" ")[0], it.split(" ")[1].toInt()) }
        val h_CurrentAt = Point(0,0)
        val t_CurrentAt = (1..9).map { Point(0,0) }
        val distinct = mutableSetOf(Point(0,0))
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
                var nextTail = 0
                var currPoint = Point(h_CurrentAt.x, h_CurrentAt.y)
                while(nextTail < 9 && currPoint.distance(t_CurrentAt[nextTail]) >= 2) {
                    t_CurrentAt[nextTail].translate(
                        (currPoint.x - t_CurrentAt[nextTail].x).sign,
                        (currPoint.y - t_CurrentAt[nextTail].y).sign
                    )
                    currPoint = Point(t_CurrentAt[nextTail].x, t_CurrentAt[nextTail].y)
                    nextTail++
                }
                distinct.add(Point(t_CurrentAt[8].x, t_CurrentAt[8].y))
            }
        }
        println(distinct.size)
    }

}