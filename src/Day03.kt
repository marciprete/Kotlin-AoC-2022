fun main() {

    fun priority(char: Char): Int {
        return when (char.isUpperCase()) {
            true -> char.code - 38
            else -> char.code - 96
        }
    }

    fun part1(input: List<String>): Int {
        return input.map { it.trim().toCharArray() }
            .map {
                val pair = Pair(
                    it.copyOfRange(0, (it.size + 1) / 2).toHashSet(),
                    it.copyOfRange((it.size + 1) / 2, it.size).toHashSet()
                )
                pair.first.retainAll(pair.second)
                priority(pair.first.first())
            }
            .sum()
    }

    fun part2(input: List<String>): Int {
        return input.map { it.toCharArray().toHashSet() }
            .windowed(3, 3) {
                it.get(1).retainAll(it.get(2))
                it.get(0).retainAll(it.get(1))
                priority(it.get(0).first())
            }.sum()

    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))

}
