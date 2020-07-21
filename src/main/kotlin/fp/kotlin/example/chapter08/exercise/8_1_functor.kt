package fp.kotlin.example.chapter08.exercise

import fp.kotlin.example.chapter04.solution.curried
import fp.kotlin.example.chapter07.exercise.Cons
import fp.kotlin.example.chapter07.exercise.FunList
import fp.kotlin.example.chapter07.exercise.Nil

/**
 *
 * 연습문제 8-1
 *
 * 7장에서 만든 리스트 펑터를 사용해서 ``product`` 함수에 [1, 2, 3, 4]가 적용된 부분 적용 함수의 리스트 만들어보자.
 * 만들어진 리스트에 여러가지 값을 넣어서 테스트해보자. 예를들어 5를 넣으면 [5, 10, 15, 20]이 되어야 한다.
 *
 */

fun main() {

    val product: (Int, Int) -> Int = { x: Int, y: Int -> x * y }
    val curriedProduct: (Int) -> (Int) -> Int = product.curried()
    val list = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))

    val productWithList: (Int) -> FunList<Int> = {
        x -> list.fmap(curriedProduct).fmap { it(x) }
    }

    require(productWithList(5) == Cons(5, Cons(10, Cons(15, Cons(20, Nil)))))
    require(productWithList(10) == Cons(10, Cons(20, Cons(30, Cons(40, Nil)))))
}
