import java.io.PrintWriter
import scala.io.Source
import java.io.File
 
object Main {
 
  val in = Source.fromFile("large.in").getLines
  val printer = new PrintWriter(new File("large.out"))
 
  def getMaxIdx(searchEngines: List[String], queries: List[String]): Int = {
    val queiresWithIndex = queries.zipWithIndex
 
    searchEngines.foldLeft(0)((a, f) => {
      val idx = getFirstIdx(f, queries)
      if (idx > a)
        idx
      else
        a
    })
  }
  def getFirstIdx(searchEngine: String, queries: List[String]): Int = {
 
    queries.zipWithIndex.find(p => p._1 == searchEngine) match {
      case Some((_, i)) => i
      case _ => queries.size
    }
  }
 
  def solve(searchEngines: List[String], queries: List[String], acc: Int): Int = {
    if (searchEngines.toSet.size > queries.toSet.size) acc
    else if (queries.size == 0) acc
    else {
      var maxIdx = getMaxIdx(searchEngines, queries)
      if (queries.size == maxIdx) {
        acc
      } else {
        solve(searchEngines, queries.takeRight(queries.size - maxIdx), acc + 1)
      }
    }
  }
 
  def main(args: Array[String]) {
 
    process()
  }
 
  def process() {
    for { i <- 1 to in.next.toInt } {
      var searchEngines = (for { i <- 0 until in.next.toInt } yield { in.next.toString }).toList
      var quries = (for { i <- 0 until in.next.toInt } yield { in.next.toString }).toList
      printer.println(s"Case #$i: ${solve(searchEngines, quries, 0)}")
    }
    printer.flush
  }
 
}