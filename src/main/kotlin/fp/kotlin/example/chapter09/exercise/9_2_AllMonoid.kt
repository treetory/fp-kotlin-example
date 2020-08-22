package fp.kotlin.example.chapter09.exercise

import fp.kotlin.example.chapter09.Monoid
import fp.kotlin.example.chapter09.solution.AllMonoid

/**
 *
 * 연습문제 9-2
 *
 * ``&&`` 연산을 All 모노이드로 만들어보자.
 *
 */

fun main() {
    AllMonoid().run {
        require(mappend(true, mempty()))
        require(!mappend(false, mempty()))
    }
}

class AllMonoid : Monoid<Boolean> {
    override fun mempty(): Boolean {
        return true
    }

    override fun mappend(m1: Boolean, m2: Boolean): Boolean {
        return m1 && m2
    }

}
