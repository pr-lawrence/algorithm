package crossTheMaze

import scala.io.Source
import java.io.File
import java.io.PrintWriter
import scala.collection.mutable.ArrayBuffer

object crossTheMazeMain {
  class Direction {
  }

  object W extends Direction {
    override def toString() = { "W" }
  }
  object N extends Direction {
    override def toString() = { "N" }
  }
  object E extends Direction {
    override def toString() = { "E" }
  }
  object S extends Direction {
    override def toString() = { "S" }
  }

  // create Advenced map. When the method is complete, map wrapped by '#'
  def createMap(m: Array[String]): Array[String] = {
    var tempArray: ArrayBuffer[String] = ArrayBuffer.empty
    tempArray += (for (i <- 0 to m(0).length + 1) yield "#").toArray.mkString("")
    for (m1 <- m) { tempArray += "#" + m1 + "#" }
    tempArray += (for (i <- 0 to m(0).length + 1) yield "#").toArray.mkString("")

    tempArray.toArray
  }

  def solve(m: Array[String], sx: Int, sy: Int, ex: Int, ey: Int): (Int, String) = {
    val edisonMap = createMap(m);

    Edison(edisonMap, sx, sy, ex, ey)
  }

  def Edison(edisonMap: Array[String], sx: Int, sy: Int, ex: Int, ey: Int): (Int, String) = {
    val d: Direction = if (sy == 1) N else S
    val result = moveToEdison(edisonMap, d, sx, sy, ex, ey, 0, "")
    (result._1, result._2.tail)
  }

  def moveToEdison(m: Array[String], ed: Direction, sx: Int, sy: Int, ex: Int, ey: Int, acc: Int, res: String): (Int, String) = {
    if (sx == ex && sy == ey) (acc, res + ed.toString)
    else {
      val (nd: Direction, nx: Int, ny: Int) = ed match {
        case N => if (m(sx)(sy - 1).equals('.')) (W, sx, sy - 1) else if (m(sx - 1)(sy).equals('.')) (N, sx - 1, sy) else if (m(sx)(sy + 1).equals('.')) (E, sx, sy + 1) else if (m(sx + 1)(sy).equals('.')) (S, sx + 1, sy) else (S, 0, 0)
        case E => if (m(sx - 1)(sy).equals('.')) (N, sx - 1, sy) else if (m(sx)(sy + 1).equals('.')) (E, sx, sy + 1) else if (m(sx + 1)(sy).equals('.')) (S, sx + 1, sy) else if (m(sx)(sy - 1).equals('.')) (W, sx, sy - 1) else (W, 0, 0)
        case S => if (m(sx)(sy + 1).equals('.')) (E, sx, sy + 1) else if (m(sx + 1)(sy).equals('.')) (S, sx + 1, sy) else if (m(sx)(sy - 1).equals('.')) (W, sx, sy - 1) else if (m(sx - 1)(sy).equals('.')) (N, sx - 1, sy) else (N, 0, 0)
        case W => if (m(sx + 1)(sy).equals('.')) (S, sx + 1, sy) else if (m(sx)(sy - 1).equals('.')) (W, sx, sy - 1) else if (m(sx - 1)(sy).equals('.')) (N, sx - 1, sy) else if (m(sx)(sy + 1).equals('.')) (E, sx, sy + 1) else (E, 0, 0)
      }
      if (acc >= 10000 || (nx == 0 && ny == 0)) {
        (acc, " Edison ran out of energy.")
      } else {
        moveToEdison(m, nd, nx, ny, ex, ey, acc + 1, res + ed.toString)
      }
    }
  }

  def main(args: Array[String]) = {
    val lines = in.next

    for (line <- 1 to lines.toInt) {
      // First, indicate width and height
      val n = in.next.toInt
      // Second to length of First, Maze that wall is '#', empty cell is '.'
      val m: Array[String] = Array.fill(n)(in.next)
      // Next, Start X, Start Y, End X, End Y
      val Array(sx, sy, ex, ey) = in.next.split(" ").map(_.toInt)

      val (acc, result) = solve(m, sx, sy, ex, ey)

      if (acc == 0 || acc == 10000) printer.println(s"Case #$line: $result")
      else printer.println(s"Case #$line: $acc\n$result")

    }

    printer.flush()
  }

  val in = Source.fromFile("large.in").getLines
  val printer = new PrintWriter(new File("large.out"))
}
