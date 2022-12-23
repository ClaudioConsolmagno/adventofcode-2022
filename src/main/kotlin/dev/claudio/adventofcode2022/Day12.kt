package dev.claudio.adventofcode2022

import dev.claudio.adventofcode2022.Support.Companion.printGrid
import dev.claudio.adventofcode2022.Support.Companion.printGridPointValue
import dev.claudio.adventofcode2022.Support.Companion.surroundingPoints4
import org.jgrapht.Graph
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.builder.GraphTypeBuilder
import java.awt.Point

fun main() {
    Day12().main()
}

private class Day12 {

    // https://jgrapht.org/guide/UserOverview#graph-algorithms
    val graph: Graph<PointValue<Char>, DefaultWeightedEdge> = GraphTypeBuilder
        .directed<PointValue<Char>, DefaultWeightedEdge>()
        .weighted(true)
        .edgeClass(DefaultWeightedEdge::class.java)
        .buildGraph()

    fun main() {
        val input = Support.readFileAsListString("2022/day12-input.txt")
        val ySize = input.size
        val xSize = input[0].length
        (0 until ySize).forEach { y ->
            (0 until xSize).forEach { x ->
                val value: Char = input[y][x]
                graph.addVertex(PointValue(x, y, value))
            }
        }
        graph.vertexSet().printGridPointValue()
        val maxPoint = Point(xSize-1, ySize-1)
        var startPoint = Point(0,0)
        var endPoint = Point(0,0)
        input.forEachIndexed{ y, str ->
            str.toList().forEachIndexed { x, weight ->
                val currentPoint = Point(x, y)
                var adjustedWeight = weight
                if (weight == 'S') {
                    startPoint = currentPoint
                    adjustedWeight = 'a'
                }
                else if (weight == 'E') {
                    endPoint = currentPoint
                    adjustedWeight = 'z'
                }
                currentPoint.surroundingPoints4(maxPoint).forEach {
                        println(it)
//                    graph.addEdge(currentPoint, it).apply { graph.setEdgeWeight(this, weight.digitToInt().toDouble()) }
                }
            }
        }
//        graph.ver
//        val dijkstraAlg = DijkstraShortestPath(graph).getPaths(maxPoint)
//        println(dijkstraAlg.getWeight(Point(0,0)))
    }
}