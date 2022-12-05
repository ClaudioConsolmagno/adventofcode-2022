package dev.claudio.adventofcode2022

import java.util.Stack

fun main() {
    Day5Part2().main()
}

private class Day5Part2 {
    fun main() {
        val inputList: List<String> = Support.readFileAsListString("2022/day5-input.txt")
        val separator = inputList.indexOf("")
        val cranes = inputList.subList(0, separator)
        val procedures = inputList.subList(separator+1, inputList.size)
//        println(cranes)
//        println(procedures)
        val stacks : MutableList<Stack<String>> = findStacks(cranes)
        procedures.forEach {
            val split: List<String> = it.split(" ")
            val count = Integer.valueOf(split[1])
            val from = Integer.valueOf(split[3]) -1
            val to = Integer.valueOf(split[5]) -1
//            println("" + count + " " + from + " to " + to)
            (0 until count)
                .map { stacks[from].pop() }
                .reversed()
                .forEach { pop ->
                    stacks[to].push(pop)
                }
        }
        println(stacks.map { it.pop() }.joinToString(""))
    }

    private fun findStacks(cranes: List<String>): MutableList<Stack<String>> {
        val stackSize = Integer.valueOf(cranes[cranes.size-1].split(" ").last())
        val stack : MutableList<Stack<String>> = mutableListOf()
        (0 until stackSize).forEach{ _ -> stack.add(Stack<String>()) }
        val cranesPadding = cranes.map{ it.length }.apply { println(this) }.maxOf { it }
        cranes.map { it.padEnd(cranesPadding) }.reversed().subList(1, cranes.size).forEach {
            var valueIndex = 0
            it.toCharArray().drop(1).forEachIndexed{ index, letter ->
                if (index == valueIndex * 4) {
//                    println(letter)
                    if (letter != ' ') {
                        stack[valueIndex].add(letter.toString())
                    }
                    valueIndex += 1
                }
            }
        }
        println(stack)
        return stack
    }

}