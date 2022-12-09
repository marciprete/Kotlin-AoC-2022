import kotlin.math.max
import kotlin.math.sign
import kotlin.math.abs

data class Position(var x: Int, var y: Int)
data class Rope(var head: Position, var tail: Position) {
    private var path = mutableSetOf<Position>()

    fun getPath(): MutableSet<Position> {
        return this.path
    }

    fun move(offset: Position) {
        head.x+=offset.x
        head.y+=offset.y
        if(abs(head.x-tail.x) > 1 || abs(head.y-tail.y)>1) {
            follow(head)
        }
    }


    private fun follow(position: Position) {
        val distX = position.x - tail.x
        val distY = position.y - tail.y

        var offsetX = (distX.sign)
        var offsetY = (distY.sign)
        for(i in 1 until (max(abs(distX), abs(distY)))) {
            tail = Position(tail.x+offsetX, tail.y+(offsetY))
            if(abs(head.x-tail.x)<=1){
                offsetX=0
            }
            if(abs(head.y-tail.y)<=1){
                offsetY=0
            }
            path.add(tail)
        }
    }

}
fun main() {


    fun getPositionOffset(it: List<String>): Position {
        var x = 0
        var y = 0
        when (it.get(0)) {
            "L" -> x = -it.get(1).toInt()
            "R" -> x = it.get(1).toInt()
            "U" -> y = it.get(1).toInt()
            else -> y = -it.get(1).toInt()
        }
        return Position(x,y)
    }

    fun part1(input: List<String>): Int {
        var rope = Rope( Position(0,0),  Position(0,0))

        input.map{
            getPositionOffset(it.split(" "))
        }.forEach {
            rope.move(it)
        }
        println(rope)
        println(rope.getPath().size)
        return 0
    }


    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09")
    check(part1(testInput) == 0)
//    check(part2(testInput) == 4)

    val input = readInput("Day09")
//    println(part1(input))
//    println(part2(input))


}
