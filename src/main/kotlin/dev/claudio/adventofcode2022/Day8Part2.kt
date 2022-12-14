package dev.claudio.adventofcode2022

fun main() {
    Day8Part2().main()
}

private class Day8Part2 {

    fun main() {
        val input = Support.readFileAsListString("2022/day8-input.txt")
        val inputArray: List<List<Int>> = input.map { it.toCharArray().map { it2 -> it2.digitToInt() } }
        var curHighest = 0L
        for (i in 0 until inputArray.size) {
            for (j in 0 until inputArray[i].size) {
                val score = calcScore(inputArray, i, j)
                println("Calculated Score: $score - Current Highest: $curHighest")
                if (curHighest < score) {
                    curHighest = score
                }
            }
        }
        println("Highest Score: $curHighest")
    }

    private fun calcScore(inputArray: List<List<Int>>, i: Int, j: Int): Long {
        var xLeft = j-1
        var xLeftScore = 0L
        while (xLeft >= 0) {
            xLeftScore++
            if (inputArray[i][j] > inputArray[i][xLeft]) {
                xLeft--
            } else {
                break
            }
        }
        var xRight = j+1
        var xRightScore = 0L
        while (xRight <= inputArray.size-1) {
            xRightScore++
            if (inputArray[i][j] > inputArray[i][xRight]) {
                xRight++
            } else {
                break
            }
        }
        var yLeft = i-1
        var yLeftScore = 0L
        while (yLeft >= 0) {
            yLeftScore++
            if (inputArray[i][j] > inputArray[yLeft][j]) {
                yLeft--
            } else {
                break
            }
        }
        var yRight = i+1
        var yRightScore = 0L
        while (yRight <= inputArray.size-1) {
            yRightScore++
            if (inputArray[i][j] > inputArray[yRight][j]) {
                yRight++
            } else {
                break
            }
        }
        return xLeftScore * xRightScore * yLeftScore * yRightScore
    }
}