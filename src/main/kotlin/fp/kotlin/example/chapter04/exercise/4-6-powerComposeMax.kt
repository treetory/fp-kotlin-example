package fp.kotlin.example.chapter04.exercise

import fp.kotlin.example.chapter04.compose

/**
 * 연습문제 4-6
 *
 * 연습문제 4-5에서 작성한 함수를 ``compose`` 함수를 사용해서 재작성해보자.
 */

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7)
    val list2 = listOf(10, 2, 13, 4, 0, 6, 1)

    require(maxComposePower(list) == 49)
    require(maxComposePower(list2) == 169)

    println(maxComposePower2(list))
    println(maxComposePower2(list2))
}

fun maxComposePower(list: List<Int>): Int = (power compose max)(list)

fun maxComposePower2(list: List<Int>): Int = ({ v: Int -> v.times(v) } compose { list: List<Int> -> list.max()!! })(list)
