object TrainTimetable extends App {

	def solve(tt:Int, as:List[(Int,Int)], bs:List[(Int, Int)]): String = {
		val asPlus = as.map( (x) => (x._1, x._2 + tt))
			val bsPlus = bs.map( (x) => (x._1, x._2 + tt))

			val asList: List[(Int, String)] = (asPlus.map( (x) => ( x._1, "A" )) ::: bsPlus.map( (x) => (x._2 , "0B"))).sorted
			val bsList: List[(Int, String)] = (asPlus.map( (x) => ( x._2, "0A" )) ::: bsPlus.map( (x) => (x._1 , "B"))).sorted

			def calcAs(asList: List[(Int, String)], accA: Int, accB: Int) : Int = {
				if ( asList.isEmpty ) accA
				else {
					if ( asList.head._2  == "A" ) {
						calcAs(asList.tail, if ( accB > 0 ) accA else accA + 1, if ( accB > 0 ) accB-1 else 0)
					} else {
						calcAs(asList.tail, accA, accB+1)          
					}
				}
			}

		def calcBs(bsList: List[(Int, String)], accA: Int, accB: Int) : Int = {
			if ( bsList.isEmpty ) accB
			else {
				if ( bsList.head._2  == "B" ) {
					calcBs(bsList.tail, if ( accA > 0 ) accA-1 else 0, if ( accA > 0 ) accB else accB+1)
				} else {
					calcBs(bsList.tail, accA+1, accB)          
				}
			}
		}

		println(asList)
			//    println(asList)
			//    println(calcAs(asList,0,0))
			//    println(calcBs(bsList,0,0))

			s"${calcAs(asList,0,0)} ${calcBs(bsList,0,0)}"
	}

	def process(lineIn: Iterator[String])(lineOut: String => Unit) = {
		for (i <- 1 to lineIn.next.toInt){
			val turnarroundTime = lineIn.next.toInt
				val Array(na, nb) = lineIn.next.split(" ").map(_.toInt)
				val as = List.fill(na)(lineIn.next.split(" ").map(_.replace(":", "").toInt) match { case Array(a,b) => (a, b) } )
				val bs = List.fill(nb)(lineIn.next.split(" ").map(_.replace(":", "").toInt) match { case Array(a,b) => (a, b) } )

				lineOut(s"Case #$i: ${solve(turnarroundTime, as, bs)}")
		}
	}

	val writer = new java.io.PrintWriter("large.out")
		try{
			//process(io.Source.fromFile("A-large-practice.in").getLines)(writer.println)
			process(io.Source.fromFile("large.in").getLines)(writer.println)
		} finally {
			writer.flush; writer.close
		}
}
