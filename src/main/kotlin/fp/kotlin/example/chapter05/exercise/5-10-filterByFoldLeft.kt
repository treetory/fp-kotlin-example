package fp.kotlin.example.chapter05.exercise

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.appendTail
import fp.kotlin.example.chapter05.foldLeft
import fp.kotlin.example.chapter05.funListOf

/**
 *
 * 연습문제 5-10
 *
 * filter 함수를 foldLeft 함수를 사용해서 재작성 해보자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main() {
    val list = funListOf(1, 2, 3, 4, 5)
    require(list.filterByFoldLeft { it % 2 == 0 } == funListOf(2, 4))
}

fun <T> FunList<T>.filterByFoldLeft(p: (T) -> Boolean): FunList<T> = foldLeft(FunList.Nil) {
    acc: FunList<T>, cur: T ->
        if (p(cur)) {
            acc.appendTail(cur)
        } else {
            acc
        }
}
