package dev.claudio.adventofcode2022

fun main() {
    Day8().main()
}

private class Day8 {

    fun main() {
        val input = Support.readFileAsListString("2022/day8-input.txt")
        val inputArray: List<List<Int>> = input.map { it.toCharArray().map { it2 -> it2.digitToInt() } }
        var isSeen = (inputArray.size * 4) - 4
        for (i in 1 until inputArray.size - 1) {
            for (j in 1 until inputArray[i].size - 1) {
                println("${inputArray[i][j]}")
                if (canBeSeen(inputArray, i, j)) {
//                    println("can be seen ${inputArray[i][j]}")
                    isSeen++
                }
            }
        }
        println(isSeen)
    }

    private fun canBeSeen(inputArray: List<List<Int>>, i: Int, j: Int): Boolean {
        var xLeft = j-1
        while (xLeft >= 0) {
            if (inputArray[i][j] > inputArray[i][xLeft]) {
                xLeft--
            } else {
                break
            }
        }
        if (xLeft < 0) {
//            println("can be seen from left: ($i,$j)- ${inputArray[i][j]}")
            return true
        }
        var xRight = j+1
        while (xRight <= inputArray.size-1) {
            if (inputArray[i][j] > inputArray[i][xRight]) {
                xRight++
            } else {
                break
            }
        }
        if (xRight == inputArray.size) {
//            println("can be seen from right: ($i,$j)- ${inputArray[i][j]}")
            return true
        }
        var yLeft = i-1
        while (yLeft >= 0) {
            if (inputArray[i][j] > inputArray[yLeft][j]) {
                yLeft--
            } else {
                break
            }
        }
        if (yLeft < 0) {
//            println("can be seen from top: ($i,$j)- ${inputArray[i][j]}")
            return true
        }
        var yRight = i+1
        while (yRight <= inputArray.size-1) {
            if (inputArray[i][j] > inputArray[yRight][j]) {
                yRight++
            } else {
                break
            }
        }
        if (yRight == inputArray.size) {
//            println("can be seen from bottom: ($i,$j)- ${inputArray[i][j]}")
            return true
        }
        return false
    }
}