import java.io.PrintWriter

object Main {
  def main(args: Array[String]) {

    val writer = new PrintWriter("large.out")
    //    val writer = new PrintWriter("sample.out")
    try {
      process(io.Source.fromFile("large.in").getLines)(writer.println)
      //      process(io.Source.fromFile("sample.in").getLines)(writer.println)
    } finally {
      writer.flush();
      writer.close()
    }

  }

  def solve(audiences: List[Int], level: Int, standing: Int, acc: Int): Int = {
    //    Console println audience
    println(level, standing, acc, audiences.mkString(" "))
    if (audiences.isEmpty) acc
    else if (audiences.head == 0) solve(audiences.tail, level + 1, standing, acc)
    else {
      if (level > standing) {
        val calc = level - standing
        solve(audiences.tail, level + 1, audiences.head + standing + calc, acc + calc)
      } else {
        solve(audiences.tail, level + 1, audiences.head + standing, acc)
      }
    }
  }

  def process(lineIn: Iterator[String])(lineOut: String => Unit) = {
    for (i <- 1 to lineIn.next().toInt) {
      val lists = lineIn.next().split(" ")(1).split("").map(_.toInt).toList
      lineOut(s"Case #$i: ${solve(lists, 0, 0, 0)}")
    }
  }
}