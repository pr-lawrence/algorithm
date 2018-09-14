import scala.io.Source
import java.io.PrintWriter
import java.io.File

object main {

  val in = Source.fromFile("large.in").getLines
  val printer = new PrintWriter(new File("large.out"))

  val pre = "double triple quadruple quintuple sextuple septuple octuple nonuple decuple".split(" ").toList
  val numbers = "zero one two three four five six seven eight nine".split(" ").toList

  type Segment = List[(Int, Int)]

  object Numbers {
    def apply(line: String): Numbers = {
      val Array(number, pattern) = line.split(" ")
      val idx = pattern.split("-").toList.map(_.toInt)
      val result = divide(number, idx)
      Numbers(result.map(transform(_)).toList)
    }
  }

  case class Numbers(ss: List[Segment]) {
    override def toString() = {
      val result = for (x <- ss; (num, count) <- x) yield {
        val numberString = numbers(num)
        if (count > 10) (1 to count).toList.map(a => numberString).mkString(" ")
        else if (count == 1) s"$numberString"
        else s"${pre(count - 2)} $numberString"
      }
      result.mkString(" ")
    }
  }

  def transform(str: String): Segment = {
    val tList = str.split("(?<=(.))(?!\\1)").toList
    tList.map(a => (a(0).toString.toInt, a.length))
  }

  def divide(str: String, idx: List[Int]): List[String] = {
    if (idx == Nil) Nil
    else str.take(idx.head) :: divide(str.drop(idx.head), idx.tail)
  }

  def main(args: Array[String]) = {

    for { i <- 1 to in.next.toInt } {
      printer.println(s"Case #$i: ${Numbers(in.next)}")
    }

    printer.flush
  }
}