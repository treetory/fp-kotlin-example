package fp.kotlin.example.chapter05.exercise

import fp.kotlin.example.chapter05.FunList

/**
 *
 * 연습문제 5-1
 *
 * 구현한 List를 사용해 (1,2,3,4,5)를 갖는 intList를 생성하자.
 *
 */

fun main() {
    val intList: FunList<Int> = FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Cons(4, FunList.Cons(5, FunList.Nil)))))
}
