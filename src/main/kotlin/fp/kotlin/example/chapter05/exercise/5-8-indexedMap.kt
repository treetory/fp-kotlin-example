package fp.kotlin.example.chapter05.exercise

import fp.kotlin.example.chapter05.*
import fp.kotlin.example.chapter05.FunList.Cons
import fp.kotlin.example.chapter05.FunList.Nil

/**
 *
 * 연습문제 5-8
 *
 * 앞서 작성한 map 함수에서 고차함수가 값들의 순서값(인덱스)값도 같이 받아 올수 있는 indexedMap 함수를 만들자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main() {

    val intList = Cons(1, Cons(2, Cons(3, Nil)))
    require(intList.indexedMap { index, elm -> index * elm } == funListOf(0, 2, 6))
}

tailrec fun <T, R> FunList<T>.indexedMap(index: Int = 0, acc: FunList<R> = Nil, f: (Int, T) -> R): FunList<R> = when (this) {
    FunList.Nil -> acc.reverse()
    is FunList.Cons -> {
        val idx = index + 1
        this.getTail().indexedMap(idx, acc.addHead(f(index, this.getHead())), f)
    }
}
