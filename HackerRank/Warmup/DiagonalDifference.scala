/**
  *
  * @author Lawrence
  * @since 2018. 2. 21.
  * @note https://www.hackerrank.com/challenges/diagonal-difference/problem
  * @version
  */
object DiagonalDifference {
  def diagonalDifference(a: Array[Array[Int]]): Int =  {
    // Complete this function

    val diagonal = for {
      y <- 0 until a.length
    } yield {
      val z = a.length - y - 1
      (a(y)(y), a(z)(y))
    }

    val (x, y) = diagonal.reduce((x, y) => (x._1 + y._1, x._2 + y._2))
    (x - y).abs
  }

  def main(args: Array[String]) {
//    val sc = new java.util.Scanner (System.in);
    val sc = new java.util.Scanner ("""3
                                      |11 2 4
                                      |4 5 6
                                      |10 8 -12""".stripMargin)

    var n = sc.nextInt()
    var a = Array.ofDim[Int](n,n)
    for(a_i <- 0 to n-1) {
      for(a_j <- 0 to n-1){
        a(a_i)(a_j) = sc.nextInt()
      }
    }
    val result = diagonalDifference(a)
    println(result)
  }
}
