/**
  *
  * @author Lawrence
  * @since 2018. 2. 19.
  * @note https://www.hackerrank.com/challenges/the-grid-search/problem
  * @version
  */
object TheGridSearch {

  def gridSearch(G: Array[String], P: Array[String]): String = {
    // G가 큰 Array, P가 작은 Array
    val isExist = (0 to G.length - P.length).exists(line => {
      (0 to G(line).length - P(0).length).exists(index =>
        P.indices.forall(x => G(line + x).startsWith(P(x), index))
      )
    })

    if (isExist) "YES" else "NO"
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    var t = sc.nextInt()
    var a0 = 0
    while (a0 < t) {
      var R = sc.nextInt()
      var C = sc.nextInt()
      var G = new Array[String](R)
      for (g_i <- 0 to R - 1) {
        G(g_i) = sc.next()
      }
      var r = sc.nextInt()
      var c = sc.nextInt()
      var P = new Array[String](r)
      for (p_i <- 0 to r - 1) {
        P(p_i) = sc.next()
      }
      val result = gridSearch(G, P)
      println(result)
      a0 += 1
    }
  }


}
