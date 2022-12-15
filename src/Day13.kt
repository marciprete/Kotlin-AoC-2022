import kotlin.math.sign

typealias Signal = List<Any>
fun main() {
    fun parse(input: Iterator<Char>): List<Any> {
        val list = mutableListOf<Any>()

        while(input.hasNext()) {
            var c = input.next()
            if(c=='[') {
                list.add(parse(input))
            } else if(c.isDigit()) {
                var s = ""
                while((c.isDigit())) {
                    s += c.toString()
                    c = input.next()
                }
                list.add(s.toInt())
            }
            if(c==']') {
                return list
            }
        }
        return list
    }

    fun compareIn(left: Signal , right: Signal): Int {
        var rightOrder = 0
        val leftIterator = left.iterator()
        val rightIterator = right.iterator()
        while((leftIterator.hasNext() || rightIterator.hasNext()) && rightOrder == 0) {
            if(!leftIterator.hasNext() || !rightIterator.hasNext()) {
                rightOrder = (right.size - left.size).sign
            } else {
                val l = leftIterator.next()
                val r = rightIterator.next()
                val leftIsList = l is List<*>
                val rightIsList = r is List<*>
                if (!leftIsList && !rightIsList) {
                    rightOrder = r.toString().toInt().compareTo(l.toString().toInt())
                } else {
                    rightOrder = compareIn(
                        (if (leftIsList) l as Signal else listOf(l)) as Signal,
                        (if (rightIsList) r as Signal else listOf(r)) as Signal
                    )
                }
            }
        }
        if(rightOrder==0) {
            rightOrder = (right.size - left.size).sign
        }
        return rightOrder
    }

    fun compare(it: Pair<Any, Any>): Int {
        val left = it.first //as List<Any>
        val right = it.second //as List<Any>
        return compareIn(left as Signal, right as Signal)
    }

    fun part1(input: List<String>): Int {
        val pairs = input.windowed(2,3).map {
            Pair(parse(it[0].iterator())[0], parse(it[1].iterator())[0])
        }
        return pairs.mapIndexed{ idx, it ->
            val compare = compare(it)
            when(compare >0) {
                true -> idx+1
                else -> 0
            }
        }.sum()
    }

    fun part2(input: List<String>): Int {
        val dividerPkt1 = parse("[[2]]".iterator())[0]
        val dividerPkt2 = parse("[[6]]".iterator())[0]

        val pairs = input.windowed(2,3).map {
            Pair(parse(it[0].iterator())[0], parse(it[1].iterator())[0])
        }
                as MutableList
        pairs.add(Pair(dividerPkt1, dividerPkt2))
        val signals = mutableListOf<Any>()
        pairs.forEach { signals.addAll(it.toList())}

        signals.sortWith(Comparator { x, y -> compareIn(y as Signal,x as Signal)} )
        val so = signals.mapIndexed{ index, item ->
            when {
                item==dividerPkt1 || item == dividerPkt2 -> index+1
                else -> 1
            }
        }.reduce{tot, elem -> tot*elem}
        return so
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day13_test")
    check(part1(testInput) == 13)
    val test2Input = readInput("Day13_test")
    check(part2(test2Input) == 140)

    val input = readInput("Day13")
    println(part1(input))
    println(part2(input))


}

