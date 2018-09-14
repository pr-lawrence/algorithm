import java.io.PrintWriter

object Main {
  def main(args: Array[String]) {

    val writer = new PrintWriter("large.out")
    try {
      process(io.Source.fromFile("large.in").getLines)(writer.println)
    } finally {
      writer.flush();
      writer.close()
    }
  }

  def solve(plates: List[Int]): Int = {
    def splitOneElement(lists: List[Int], option: Int, iter: Int): List[Int] = {
      if (iter == 0) lists
      else {
        val halfOfHead = lists.head / 2
        splitOneElement(lists.tail ::: List(halfOfHead, if (lists.head % 2 == 0) halfOfHead else halfOfHead + 1).sortWith(_ > _), option, iter - 1)
      }
    }

    val calcResults = for (i <- 0 until 100) yield {
      i + splitOneElement(plates, 0, i).max
    }
    calcResults.min
  }

  def process(lineIn: Iterator[String])(lineOut: String => Unit) = {
    for (i <- 1 to lineIn.next().toInt) {
      lineIn.next()
      val lists = lineIn.next.split(" ").map(_.toInt).toList
      lineOut(s"Case #$i: ${solve(lists.sortWith(_ > _))}")
    }
  }
}
