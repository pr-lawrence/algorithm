import java.io.{FileOutputStream, FileInputStream}

import scala.io.StdIn

object Main extends App {
	Console.setIn(new FileInputStream("B-large-practice.in"))
	Console.setOut(new FileOutputStream("B-large-practice.out"))

	val CLEARED = Integer.MAX_VALUE

	def solve(stages: List[List[Int]], stars: Int): Option[Int] = {
		val (avail2, nonavail2) = stages.partition(x => x(1) <= stars)
			if (stages.isEmpty) Some(0)
			else if (avail2.isEmpty) {
				val (avail1, nonavail1) = stages.partition(x => x(0) <= stars)
					if (avail1.isEmpty) None
					else {
						val (first, remain) = avail1.sortBy(_(1)).reverse.splitAt(1)
							solve(first(0).updated(0, CLEARED) :: nonavail1 ::: remain, stars + 1).map(_ + 1)
					}
			} else solve(nonavail2, stars + avail2.flatten.count(_ != CLEARED)).map(_ + avail2.length)
	}

	val cases = StdIn.readLine().toInt
	(1 to cases) foreach { n =>
		val qNum = StdIn.readLine().toInt
			val stages = (for (i <- 1 to qNum) yield StdIn.readLine().split(" ").map(_.toInt).toList).toList
			println(s"Case #$n: ${solve(stages, 0).getOrElse("Too Bad")}")
	}
}
