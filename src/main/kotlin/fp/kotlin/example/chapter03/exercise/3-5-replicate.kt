package fp.kotlin.example.chapter03.exercise

/**
 * 연습문제 3-5
 *
 * 숫자를 두 개 입력받은 후 두 번째 숫자를 첫 번째 숫자만큼 가지고 있는 리스트를 반환하는 함수를 만들어 보자. 예를 들어 ``replicate(3, 5)``를 입력하면
 * 5가 3개 있는 리스트 [5, 5, 5]를 반환한다.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 * fun replicate(n: Int, element: Int): List<Int>
 */

fun main() {
    require(listOf(5, 5, 5) == replicate(3, 5))
    require(listOf(1, 1, 1, 1, 1) == replicate(5, 1))
}

private fun replicate(n: Int, element: Int): List<Int> = when {
    n <= 0 -> listOf()
    else -> listOf(element) + replicate(n-1, element)
}

private fun replicate2(n: Int, element: Int, acc: List<Int>): List<Int> {
    return when(n) {
        0 -> acc
        else -> {
            val temp = acc + element
            return replicate2(n-1, element, temp)
        }
    }
}