import java.io.FileOutputStream

import scala.io.Source

object UpAndDown {
  val INPUT = "B-large-practice.in"
  val OUTPUT = INPUT.takeWhile(_ != '.') + ".out"

  def solve(list: Array[Int], accr: Int = 0): Int = {
    if (list.isEmpty) accr
    else {
      val idx = list.indexOf(list.min)
      val cnt = if (list.length / 2 <= idx) list.length - idx - 1 else idx
      val (head, others) = list.splitAt(idx)
      solve(head ++ others.tail, accr + cnt)
    }
  }

  def main(args: Array[String]): Unit = {
    val itr = Source.fromFile(INPUT).getLines()
    val sets = itr.next().toInt

    val writer = new FileOutputStream(OUTPUT)
    //    val writer = Console.out
    try {
      Console.withOut(writer) {
        for (set <- 1 to sets) {
          itr.next()
          val list = itr.next().split(' ').map(_.toInt)
          println(f"Case #${set}: ${solve(list)}")
        }
      }
    } finally {
      writer.close()
    }
  }
}