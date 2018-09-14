object Main {

	def solve(ropes: List[(Int, Int)]): Int = {

		def sorting(li: List[(Int, Int)], acc: Int): Int = {
			if (li == Nil) acc
			else {
				val h = li.head
					sorting(li.tail, acc + li.tail.foldLeft(0){(m: Int, n: (Int,Int)) => if (( h._1 - n._1 )*(h._2 - n._2) < 0) m + 1 else m})
			}
		}
		sorting(ropes, 0)
	}

	def main(args: Array[String]) {

		val writer = new java.io.PrintWriter("large.out")
			try {
				process(io.Source.fromFile("large.in").getLines)(writer.println)
			} finally {
				writer.flush; writer.close
			}
		Console println "process has been complated"
	}

	def process(lineIn: Iterator[String])(lineOut: String => Unit) = {
		for (i <- 1 to lineIn.next.toInt) {
			val n = lineIn.next.toInt

				val ropes: List[(Int,Int)]  = List.fill(n)(lineIn.next.split(" ").map(_.toInt) match { case Array(a,b) => (a, b)})
				Console println ropes
				lineOut(s"Case #$i: ${solve(ropes)}")
		}
	}
}
