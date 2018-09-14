import java.io.PrintWriter
import scala.io.Source
import java.io.File

object Main {

	val in = Source.fromFile("sample.in").getLines
	val printer = new PrintWriter(new File("large.out"))

	case class Box(count: BigInt, tp: BigInt)
	case class Toy(count: BigInt, tp: BigInt)

	def solve(boxes: Seq[Box], toys: Seq[Toy], acc: BigInt): BigInt = {
		if (boxes.isEmpty || toys.isEmpty) {
			return acc
		}

		val curBox = boxes.head
		val curToy = toys.head
		if (curBox.tp == curToy.tp) {
			val count = if (curBox.count > curToy.count) curToy.count else curBox.count
			solve(boxes.tail, toys.tail, acc + count)
		} else {
			val nextMatchBox = boxes.indexWhere(_.tp == curToy.tp)
			val nextMatchToy = toys.indexWhere(_.tp == curBox.tp)
			if(nextMatchBox == -1 && nextMatchToy == -1) return acc
			if (nextMatchBox > nextMatchToy) {
				solve(boxes, toys.drop(nextMatchToy), acc)
			} else {
				solve(boxes.drop(nextMatchBox), toys, acc)
			}
		}
	}

	def main(args: Array[String]) {
		process();
	}

	def process() {
		for { i <- 1 to in.next.toInt } {
			in.next.toString
			val boxes = in.next.split(" ").toList.sliding(2, 2).map{ a => Box(BigInt(a(0)), BigInt(a(1))) }.toList
			val toys = in.next.split(" ").toList.sliding(2, 2).map{ a => Toy(BigInt(a(0)), BigInt(a(1))) }.toList
			println(solve(boxes, toys, 0))
		}
		printer.flush
	}
}
