object Main {

	def solve(existDir: Set[String], createdDir: Set[String]): Int = {

		def makeSubset(dir: Set[String]): Set[String] = {
			dir.flatMap((c) => c.split("/").foldLeft(List[String]()) { (m: List[String], n: String) => m ++ Set((m.lastOption ++ List(n)).mkString("/")) }.toSet -- Set(""))
		}

		(makeSubset(createdDir) -- makeSubset(existDir)).size
	}

	def main(args: Array[String]) {
		val writer = new java.io.PrintWriter("large.out")
			try {
				//process(io.Source.fromFile("A-large-practice.in").getLines)(writer.println)
				process(io.Source.fromFile("large.in").getLines)(writer.println)
			} finally {
				writer.flush; writer.close
			}
		Console println "process has been complated"
	}

	def process(lineIn: Iterator[String])(lineOut: String => Unit) = {
		for (i <- 1 to lineIn.next.toInt) {
			val Array(a, b) = lineIn.next.split(" ").map(_.toInt)

				val existDir: Set[String] = List.fill(a)(lineIn.next).toSet
				val createdDir: Set[String] = List.fill(b)(lineIn.next).toSet

				lineOut(s"Case #$i: ${solve(existDir, createdDir)}")
		}
	}

}
