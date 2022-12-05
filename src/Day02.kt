fun main() {
    val circularArray = setOf<Char>('A', 'B', 'C', 'X', 'Y', 'Z')

    fun score(a: Char, b: Char): Int {
        val idA = circularArray.indexOf(a) % 3
        val idB = circularArray.indexOf(b) % 3
        return when ((idB - idA + 2) % 3) {
            2 -> 4      //Draw
            1 -> 7      //Win
            else -> 1   //Lose
        } + idA
    }

    fun cheat(a: Char, b: Char): Int {
        return when (circularArray.indexOf(b) % 3) {
            0 -> 1 + (circularArray.indexOf(a) + 2) % 3     //x = lose
            1 -> 4 + circularArray.indexOf(a)               //y = draw
            else -> 7 + (circularArray.indexOf(a) + 1) % 3  //z = win
        }
    }

    fun part1(input: List<String>): Int {
        return input.map {
            it.split(" ").let {
                (score(it.get(1)[0], it.get(0)[0]))
            }
        }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.map {
            it.split(" ").let {
                cheat(it.get(0)[0], it.get(1)[0])
            }
        }.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))

}
