package dev.claudio.adventofcode2022

fun main() {
    Day11().main()
}

private class Day11 {

    fun main() {
//        val input = Support.readFileAsListString("2022/day11-input.txt")
        val monkeysSample = listOf(
            Monkey(0, mutableListOf(79, 98), { it:Long -> it * 19}, 23, 2, 3),
            Monkey(1, mutableListOf(54, 65, 75, 74), { it:Long -> it + 6}, 19, 2, 0),
            Monkey(2, mutableListOf(79, 60, 97), { it:Long -> it * it}, 13, 1, 3),
            Monkey(3, mutableListOf(74), { it:Long -> it + 3}, 17, 0, 1),
        )
        val monkeys = listOf(
            Monkey(0, mutableListOf(99, 63, 76, 93, 54, 73), { it:Long -> it * 11}, 2, 7, 1),
            Monkey(1, mutableListOf(91, 60, 97, 54), { it:Long -> it + 1}, 17, 3, 2),
            Monkey(2, mutableListOf(65), { it:Long -> it + 7}, 7, 6, 5),
            Monkey(3, mutableListOf(84, 55), { it:Long -> it + 3}, 11, 2, 6),
            Monkey(4, mutableListOf(86, 63, 79, 54, 83), { it:Long -> it * it}, 19, 7, 0),
            Monkey(5, mutableListOf(96, 67, 56, 95, 64, 69, 96), { it:Long -> it + 4}, 5, 4, 0),
            Monkey(6, mutableListOf(66, 94, 70, 93, 72, 67, 88, 51), { it:Long -> it * 5}, 13, 4, 5),
            Monkey(7, mutableListOf(59, 59, 74), { it:Long -> it + 8}, 3, 1, 3),
        )
        for (i in 1..20) {
            monkeys.forEach {
                it.items.toList().forEach{ item ->
                    it.inspectionCount++
                    val newWorry = it.operation(item) / 3
                    monkeys[it.test(newWorry)].items.add(newWorry)
                    it.items.removeFirst()
                }
            }
        }
        println(monkeys)
        println(monkeys.map { it.inspectionCount })
        println(monkeys.map { it.inspectionCount }.sortedDescending().take(2).reduce {acc, it -> acc * it})
    }

    private data class Monkey(
        val number: Long,
        var items: MutableList<Long>,
        val operation: (Long) -> Long,
        val testDiv: Long,
        val testTrue: Int,
        val testFalse: Int,
        var inspectionCount: Long = 0L
    ) {
        fun test(worry: Long): Int {
            return if (worry % testDiv == 0L) { testTrue } else { testFalse }
        }
    }

}