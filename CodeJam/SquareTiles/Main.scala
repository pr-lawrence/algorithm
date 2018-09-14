import java.io.FileInputStream
import java.io.FileOutputStream

object SquareTiles extends App {

	def solve(tiles: Array[Array[Char]]): Option[Array[Array[Char]]] = {
		for ( r <- (0 until (tiles.length - 1));
			c <- (0 until (tiles(r).length - 1)) ) {
			tiles(r)(c) match {
				case '#' => {
					if (tiles(r)(c + 1) == '#' && tiles(r + 1)(c) == '#' && tiles(r + 1)(c + 1) == '#') {
						tiles(r)(c) = '/'
							tiles(r)(c + 1) = '\\'
							tiles(r + 1)(c) = '\\'
							tiles(r + 1)(c + 1) = '/'
					}
				}
				case _ =>
			}
		}

		if (tiles.flatten.exists(n => n == '#'))
			None
		else
			Some(tiles)
	}

	Console.setIn(new FileInputStream("A-large-practice.in"))
	Console.setOut(new FileOutputStream("A-large-practice.out"))
	val cases = readLine().toInt;

	(1 to cases) foreach { n =>
		val Array(rn, _*) = readLine().split(' ')
			val tiles = (for (r <- (0 until rn.toInt)) yield readLine().toCharArray).toArray
			println(s"Case #$n:")
			println(solve(tiles) match {
				case Some(t) => t.map(_.mkString).mkString("\n")
				case None => "Impossible"
			})
	}
}
