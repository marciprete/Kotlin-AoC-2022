fun main() {
    fun getCalories(input: List<String>): List<Int> {
        return input.foldIndexed(ArrayList<ArrayList<Int>>(1)) { index, acc, item ->
            if(index==0 || item.isEmpty()) {
                acc.add(ArrayList())
            } else {
                acc.last().add(item.toInt())
            }
            acc
        }.map { it.sum() }
    }

    fun part1(input: List<String>): Int {
        return getCalories(input).max()
    }

    fun part2(input: List<String>): Int {
        return getCalories(input).sortedDescending().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
