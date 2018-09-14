import scala.annotation.tailrec

/**
  *
  * @author Lawrence
  * @since 2018. 2. 16.
  * @note https://www.hackerrank.com/challenges/equal/problem
  * @version
  */
object Equal {

  def equal(arr: Array[Int]): Int = {

    def calc(x: Int): Int = {
      val no5 = x / 5
      val no2 = (x - no5 * 5) / 2
      val no1 = x - no5 * 5 - no2 * 2
      no5 + no2 + no1
    }

    // 최적의 수 min을 찾는 알고리즘
    val min = arr.min

    val minimum = arr.map(num => calc(num - min)).sum
    val res1 = arr.map(num => calc(num - (min - 1))).sum
    val res2 = arr.map(num => calc(num - (min - 2))).sum
    val res3 = arr.map(num => calc(num - (min - 3))).sum
    val res4 = arr.map(num => calc(num - (min - 4))).sum

    minimum min res1 min res2 min res3 min res4
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    var t = sc.nextInt()
    var a0 = 0
    while (a0 < t) {
      var n = sc.nextInt()
      var arr = new Array[Int](n)
      for (arr_i <- 0 to n - 1) {
        arr(arr_i) = sc.nextInt()
      }
//      val result = equal2(arr)
        val result = equal(arr)
      println(result)
      a0 += 1
    }
  }

  def equal2(arr: Array[Int]): Int =  {
    def _solve(minimum: Int): Int = {
      arr.map { x =>
        val no5 = (x - minimum) / 5
        val no2 = (x - minimum - no5 * 5) / 2
        val no1 = x - minimum - no5 * 5 - no2 * 2
        no5 + no2 + no1
      }.sum
    }
    val minimum = arr.min
    _solve(minimum) min _solve(minimum - 1) min _solve(minimum - 2) min _solve(minimum - 3) min _solve(minimum - 4)
  }
}
