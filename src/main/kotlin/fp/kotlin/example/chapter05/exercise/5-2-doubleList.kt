package fp.kotlin.example.chapter05.exercise

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.funListOf

/**
 *
 * 연습문제 5-2
 *
 * 구현한 List를 사용해 (1.0, 2.0, 3.0, 4.0, 5.0)를 갖는 doubleList를 생성하자.
 *
 */

fun main() {
    val doubleList: FunList<Double> = FunList.Cons(1.0, FunList.Cons(2.0, FunList.Cons(3.0, FunList.Cons(4.0, FunList.Cons(5.0, FunList.Nil)))))
    val doubleList2: FunList<Double> = funListOf(1.0, 2.0, 3.0, 4.0, 5.0)
}
