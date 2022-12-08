fun main() {

    fun getColumn(matrix: Array<IntArray>, col: Int): IntArray {
        val nums = IntArray(matrix.size)
        for (i in nums.indices) {
            nums[i] = matrix[i][col]
        }
        return nums
    }

    fun getMatrix(input: List<String>): Array<IntArray> {
        val matrix = Array(input.get(0).length) { IntArray(input.get(0).length) }
        input.forEachIndexed { row, line ->
            line.forEachIndexed { col, num ->
                matrix[row][col] = num.digitToInt()
            }
        }
        return matrix
    }

    fun part1(input: List<String>): Int {
        val matrix = getMatrix(input)

        var sum = matrix.size + 2 * (matrix.size - 1)

        for (x in 1..matrix.size - 2) {
            var left = -1
            for (y in 0..matrix[0].size - 2) {
                val current = matrix[x][y]
                val top = getColumn(matrix, y).take(x).max()
                val right = matrix[x].takeLast(matrix.size - y - 1).max()
                val bottom = getColumn(matrix, y).takeLast(matrix.size - 1 - x).max()
                if (current > top || current > left || current > right || current > bottom) {
                    left = maxOf(left, current)
                    sum++
                }
            }
        }
        return sum
    }

    fun sight(array: List<Int>, top: Int): Int {
        var count = 0
        for (n in array) {
            if (top > n) {
                count++
            } else if (top == n) {
                count++
                break
            } else {
                break
            }
        }
        return count
    }

    fun part2(input: List<String>): Int {
        val results = mutableListOf<Int>()
        val matrix = getMatrix(input)
        for (x in 1..matrix.size - 2) {
            for (y in 1..matrix[0].size - 2) {
                val top = getColumn(matrix, y).take(x)
                val current = matrix[x][y]
                val left = matrix[x].take(y)
                val right = matrix[x].takeLast(matrix.size - (y + 1))
                val bottom = getColumn(matrix, y).takeLast(matrix.size - (x + 1))
                results.add(
                    sight(top.asReversed(), current) *
                        sight(left.asReversed(), current) *
                        sight(right, current) *
                        sight(bottom, current)
                )
            }
        }
        return results.max()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 4)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))


}
