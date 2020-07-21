package fp.kotlin.example.chapter05.exercise

import fp.kotlin.example.chapter05.FunStream
import fp.kotlin.example.chapter05.foldLeft
import fp.kotlin.example.chapter05.funStreamOf

/**
 *
 * 연습문제 5-18
 *
 * FunList에서 작성했던 product 함수를 FunStream에도 추가하자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main() {
    require(funStreamOf(1, 2, 3, 4, 5).product() == 1 * 2 * 3 * 4 * 5)
}

fun FunStream<Int>.product(): Int = this.foldLeft(0) { acc, value -> acc * value}
