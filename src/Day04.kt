fun main() {

    fun part1(input: List<String>): Int {
        return input
            .map {
                val split = it.trim().split(",")
                Pair(split.get(0).toRange(), split.get(1).toRange())
                    .inEachOther()
            }.count { it }
    }

    fun part2(input: List<String>): Int {
        return input
            .map {
                val split = it.trim().split(",")
                Pair(split.get(0).split("-").map { it.toInt() },
                    split.get(1).split("-").map { it.toInt() })
            }
            .map {
                when {
                    it.first.get(0) == it.second.get(0) -> true
                    it.first.get(0) > it.second.get(0) -> it.first.get(0) <= it.second.get(1)
                    it.first.get(0) < it.second.get(0) -> it.first.get(1) >= it.second.get(0)
                    else -> false
                }
            }.count{it}

    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))

}

private fun <A, B> Pair<List<A>, List<B>>.inEachOther(): Boolean {
    val pair = (this.toList().sortedBy { it.size })
    return pair.get(1).containsAll(pair.get(0))
}

private fun String.toRange(): List<Int> {
    val edges = this.split("-")
    return IntRange(edges.get(0).toInt(), edges.get(1).toInt()).toList()
}
