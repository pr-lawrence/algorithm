import java.io.PrintWriter

/**
 * Created by Naver on 15. 2. 3..
 */
object Main {

  def solve(num: String): String = {

    val strList = 0 :: num.toCharArray.toList.map(_.toInt - 48)
    val l = strList.splitAt(strList.zip(strList.tail).lastIndexWhere((x) => x._1 < x._2))
    var ll = l._1 ::: List(l._2.updated(0, l._2.reverse.find(_ > l._2.head).get).head) ::: l._2.updated(l._2.lastIndexWhere(_ > l._2.head), l._2.head).tail.sorted

    ll match {
      case x if x.head == 0 => x.tail.mkString("")
      case x => x.mkString("")
    }
  }

  def main(args: Array[String]) {
    val writer = new PrintWriter("large.out")
    try {
      process(io.Source.fromFile("large.in").getLines)(writer.println)
    } finally {
      writer.flush();
      writer.close()
    }
  }

  def process(lineIn: Iterator[String])(lineOut: String => Unit) = {
    for (i <- 1 to lineIn.next().toInt) {
      lineOut(s"Case #$i: ${solve(lineIn.next())}")
    }
  }
}
