package main.scala

import java.io.{FileInputStream, FileOutputStream}

import scala.io.StdIn

object Main extends App {

	Console.setIn(new FileInputStream("B-large-practice.in.txt"))

	Console.setOut(new FileOutputStream("B-large-practice.out.txt"))

	def solve(lists: List[BigInt]): BigInt = {
		val a1 = lists.flatMap(n => {
				lists.map(b => n - b)
			}).filter(n => n > 0).toSet

		val result = a1.foldLeft(a1.head)((a2, b2) => { a2.gcd(b2) })

		if (lists.head % result == 0) 0 else result - lists.head % result
	}
	val cases = StdIn.readLine().toInt
		(1 to cases) foreach { n =>
			val list = StdIn.readLine().split(" ").map(BigInt(_)).toList.tail
			println(s"Case #$n: ${solve(list)}")
		}
}
