package fp.algorithm

import fp.kotlin.example.head
import fp.kotlin.example.tail

fun main() {
    val answer = make(arrayOf(1,5,2,6,3,7,4), arrayOf(arrayOf(2,5,3), arrayOf(4,4,1), arrayOf(1,7,3)), listOf())
    answer.forEach { e -> println(e) }
}

// 자르고
private fun cut(start: Int, end: Int, arr: Array<Int>): List<Int> {
    return arr.copyOfRange(start-1, end).asList()
}

// 정렬하고
private fun quicksort(list: List<Int>): List<Int> = when {
    list.isEmpty() -> list
    else -> {
        val pivot = list.head()
        val (small, bigger) = list.tail().partition { it < pivot }
        quicksort(small) + listOf(pivot) + quicksort(bigger)
    }
}

// 뽑아서 결과에 붙임 (acc 에 누적)
private fun make(arr: Array<Int>, commands: Array<Array<Int>>, acc: List<Int> = listOf()): Array<Int> = when {
    commands.isEmpty() -> acc.toTypedArray()
    else -> {
        val temp = commands[0]
        val sorted = quicksort(cut(temp[0], temp[1], arr))
        val next = commands.drop(1)
        if (next.isEmpty()) {
            make(arr, arrayOf(), acc.plus(sorted[temp[2]-1]))
        } else {
            make(arr, next.toTypedArray(), acc.plus(sorted[temp[2]-1]))
        }
    }
}
