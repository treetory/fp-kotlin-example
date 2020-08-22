package fp.kotlin.example.chapter09.exercise

/**
 *
 * 연습문제 9-12
 *
 * ``foldMap`` 함수를 사용하여 폴더블 리스트에 ``contains`` 함수를 구현해 보자.
 */
fun main() {

    val funList = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil)))))
    val funList2 = Cons('a', Cons('b', Cons('c', Cons('d', Cons('e', Nil)))))

    require(funList.contains(1))
    require(!funList.contains(6))

    require(funList2.contains('c'))
    require(!funList2.contains('x'))
}

fun <T> FunList<T>.contains(value: T): Boolean = foldMap({ it == value }, AnyMonoid())
