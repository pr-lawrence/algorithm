import scala.collection.mutable
 
object main {

  def search(index:Int, input:List[List[Int]], isVisited:mutable.Set[Int]):Boolean = {
    if (isVisited.contains(index)) {
      true
    } else {
      isVisited.add(index)
      input(index).exists(nextIndex => search(nextIndex - 1, input, isVisited))
    }
  }
 
  def solve(input:List[List[Int]]):Boolean = {
    (0 until input.length).exists(x => search(x, input, mutable.Set()))
  }
 
  def main(args:Array[String]) {
 
    val writer = new java.io.PrintWriter("a.large.out")
    try {
      process(io.Source.fromFile("A-large-practice.in").getLines)(writer.println)
    } finally {
      writer.flush(); writer.close()
    }
  }
 
  def process(lineIn: Iterator[String])(lineOut: String => Unit) = {
 
    for (i <- 1 to lineIn.next().toInt) {
      val classNum = lineIn.next().toInt
      val input = for (k <- 1 to classNum) yield lineIn.next().split(" ").map(_.toInt).toList.tail
 
      lineOut(s"Case #$i: ${if (solve(input.toList)) "Yes" else "No"}")
    }
  }
}