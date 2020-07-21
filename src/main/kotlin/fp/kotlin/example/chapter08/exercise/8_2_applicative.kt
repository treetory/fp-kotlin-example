package fp.kotlin.example.chapter08.exercise

import fp.kotlin.example.chapter08.Applicative
import fp.kotlin.example.chapter08.exercise.AFunList.Companion.pure


/**
 *
 * 연습문제 8-2
 *
 * 7장에서 만든 리스트 펑터를 Applicative의 인스턴스로 만들고 테스트해보자.
 *
 */

sealed class AFunList<out A> : Applicative<A> {

    companion object {
        // 밑의 override 된 pure 가 호출 되는 데, 고
        // ACons 를 생성 하여 반환 하려면 tail 에 ANil 이 필요 하므로,
        // ANil 에 pure 함수를 적용 하여 value 가 head 이고 tail 이 ANil 인 ACons 를 만들기 위함
        fun <V> pure(value: V): ACons<V> = ANil.pure(value)
    }

    abstract override fun <B> fmap(f: (A) -> B): AFunList<B>
    override fun <V> pure(value: V): ACons<V> = ACons(value, ANil)
    abstract override fun <B> apply(ff: Applicative<(A) -> B>): AFunList<B>
    abstract fun first(): A
    abstract fun size(): Int
}

object ANil : AFunList<Nothing>() {
    // ANil 에 함수를 mapping 해도 결과는 ANil
    override fun <B> fmap(f: (Nothing) -> B): AFunList<B> = ANil
    // ANil 에 함수를 적용 해도 결과는 ANil
    override fun <B> apply(ff: Applicative<(Nothing) -> B>): AFunList<B> = ANil
    // 첫번째 것을 찾아봐야 없으니 Exception
    override fun first(): Nothing = throw NoSuchElementException()
    // 없는 놈의 크기는 0
    override fun size(): Int = 0
}

data class ACons<A>(val head: A, val tail: AFunList<A>) : AFunList<A>() {
    // head 에 f 함수를 매핑한 결과가 ACons 의 head 에 들어 가고
    // tail 은 꼬리재귀 형태로 함수가 적용될 수 있게 tail 에 f 함수를 매핑한다.
    override fun <B> fmap(f: (A) -> B): AFunList<B> = ACons(f(head), tail.fmap(f))

    // 책에 나온 것 (8-5 Just 값 생성자) 처럼 패턴매칭을 활용하여 적용 결과를 반환
    override fun <B> apply(ff: Applicative<(A) -> B>): AFunList<B> = when (ff) {
        // 함수를 적용한 결과가 ACons 이면, ACons 객체를 반환하는데
        // head 에 applicative functor 의 결과를 씌우고,
        // tail 은 꼬리재귀 형태로 applicative functor 가 적용될 수 있게 tail 에 ff 를 적용한다.
        is ACons -> ACons(ff.head(head), tail.apply(ff))
        else -> ANil
    }

    override fun first() = head

    override fun size(): Int = 1 + tail.size()
}


fun main() {

    require(pure(1) == ACons(1, ANil))

    require(pure(1).fmap { it * 10 } == ACons(10, ANil))
    require(ANil.fmap { a: Int -> a * 10 } == ANil)

    require(pure(1).fmap { it * 10 } == ACons(10, ANil))
    require(ANil.fmap { a: Int -> a * 10 } == ANil)

    require(pure(1) apply pure { x: Int -> x * 10 } == ACons(10, ANil))
    require(ANil apply pure({ x: Int -> x * 10 }) == ANil)

    require(ACons(1, ACons(2, ACons(3, ACons(4, ANil)))) apply pure { x: Int -> x * 10 } ==
        ACons(10, ACons(20, ACons(30, ACons(40, ANil)))))
}
