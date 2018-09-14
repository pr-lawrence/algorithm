import scala.io.StdIn
import java.io.FileInputStream
import java.io.FileOutputStream
import scala.annotation.tailrec

object Main extends App {

	Console.setIn(new FileInputStream("A-large-practice.in"))
		Console.setOut(new FileOutputStream("A-large-practice.out"))

		def solve(list: List[Int]): Int = {
			val n: Int = list.size

				@tailrec
				def solveInner(list: List[Int], acc: Int): Int = {
					if (list.isEmpty) acc
					else {
						val row = n - list.size;
						if (list.head <= row) solveInner(list.tail, acc)
						else {
							val targetIndex = list.indexWhere(_ <= row)
								solveInner(list.take(targetIndex) ++ list.drop(targetIndex + 1), acc + targetIndex)
						}
					}
				}

			solveInner(list, 0)
		}

	val cases = StdIn.readLine().toInt
		(1 to cases) foreach { n =>
			val list = (for (n <- 0 until StdIn.readLine().toInt) yield StdIn.readLine().lastIndexOf("1")).toList
				println(s"Case #$n: ${solve(list)}")
		}
}
