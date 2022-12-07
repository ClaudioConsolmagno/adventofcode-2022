package dev.claudio.adventofcode2022

fun main() {
    Day7Part2().main()
}

private class Day7Part2 {
    fun main() {
        val input = Support.readFileAsListString("2022/day7-input.txt")
        val tree : Directory = buildTree(input)
        val fsSize = 70000000L
        val curUsedSize = tree.calcSize()
        val targetSize = fsSize - curUsedSize

        val resultDirectory: Directory = minDirWithTarget(tree, 30000000L - targetSize)!!
        println(resultDirectory.calcSize()) // NOT 34772236
    }

    private fun minDirWithTarget(tree: Directory, targetSize: Long): Directory? {
        val candidates = mutableListOf<Directory>()
        tree.dirs.forEach{
            val calcSize = it.calcSize()
            if (calcSize >= targetSize) candidates.add(it)
            candidates.addAll(it.dirs.mapNotNull { innerDir -> minDirWithTarget(innerDir, targetSize) })
        }
        val calcSize = tree.calcSize()
        if (calcSize >= targetSize) candidates.add(tree)
        return candidates.minByOrNull { it.calcSize() }
    }

    private fun calcResult(tree: Directory): Long {
        var result: Long = 0
        val maxSize = 100000L
        tree.dirs.forEach{
            val calcSize = it.calcSize()
            if (calcSize <= maxSize) result += calcSize
            result += it.dirs.sumOf { innerDir -> calcResult(innerDir) }
        }

        val calcSize = tree.calcSize()
        if (calcSize <= maxSize) result += calcSize
        return result
    }

    private fun buildTree(input: List<String>): Directory {
        var curDir = Directory("/", null)
        val root: Directory = curDir
        input
            .drop(1)
            .map { it.split(" ") }
            .forEach{
                if (it[0] == "$" && it[1] == "cd") {
                    // change dir
                    curDir = if (it[2] == "..") {
                        curDir.parentDir!!
                    } else {
                        curDir.dirs.find { node -> node.name == it[2] } as Directory
                    }
                } else if (it[0] == "$" && it[1] == "ls") {
                    // list dir
                } else {
                    val newNode: Node
                    if (it[0].startsWith("dir")) {
                        newNode = Directory(it[1], curDir)
                        curDir.dirs.add(newNode)
                    } else {
                        newNode = File(it[1], it[0].toLong())
                        curDir.files.add(newNode)
                    }
                }
            }
        println(root)
        return root
    }

    abstract class Node (val name: String) {
        abstract fun calcSize() : Long
    }
    data class File(val fileName: String, val fileSize: Long) : Node(fileName) {
        override fun calcSize(): Long {
            return fileSize
        }
    }
    data class Directory(
        val dirName: String,
        val parentDir: Directory?,
        val dirs: MutableList<Directory> = mutableListOf(),
        val files: MutableList<File> = mutableListOf(),
    ) : Node(dirName) {
        override fun calcSize(): Long {
            return dirs.sumOf { it.calcSize() } + files.sumOf { it.calcSize() }
        }
        override fun toString(): String {
            return "Directory(dirName='$dirName', parentDir=${parentDir?.name}, directories=$dirs, files=$files)"
        }
    }
}