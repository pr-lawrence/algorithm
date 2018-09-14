object main {
 
  def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
 
  def solve(n: BigInt, pd: Int, pg: Int): Boolean = {
    if ((0 == pg && 0 < pd) || 100 == pg && 100 > pd) {
      false
    } else {
      n >= 100 / gcd(pd, 100)
    }
  }
 
  def main(args: Array[String]) {
    val writer = new java.io.PrintWriter("a-large.out")
    try {
      process(io.Source.fromFile("A-large-practice (2).in").getLines)(writer.println)
    } finally {
      writer.flush()
      writer.close()
    }
  }
 
  def process(lineIn: Iterator[String])(lineOut: String => Unit) = {
 
    for (i <- 1 to lineIn.next().toInt) {
      val Array(n, pd, pg) = lineIn.next().split(" ")
 
      lineOut(s"Case #$i: ${if (solve(BigInt.apply(n), pd.toInt, pg.toInt)) "Possible" else "Broken"}")
    }
  }
}