object PickingUpChicks extends App {

  def minSwap(k: Int, b: Int, t: Int, xs: Seq[Int], vs: Seq[Int]): Option[Int] = {

    @annotation.tailrec
    def loop(chicks: List[(Int, Int)], hopeless: Int, k: Int, count: Int): Option[Int] = chicks match {
      case _ if k == 0 => Some(count)
      case Nil         => None
      case (x, v) :: tail =>
        val (dh, dk, dc) = if (x + v * t >= b) (0, -1, hopeless) else (1, 0, 0)
        loop(tail, hopeless + dh, k + dk, count + dc)
    }

    loop((xs zip vs).sortBy(-_._1).toList, 0, k, 0)
  }

  def process(lineIn: Iterator[String])(lineOut: String => Unit) =
    for (i <- 1 to lineIn.next().toInt) {
      def readInts() = lineIn.next().split(' ').map(_.toInt)
      val Array(n, k, b, t) = readInts()
      lineOut(s"Case #$i: ${minSwap(k, b, t, readInts(), readInts()) getOrElse "IMPOSSIBLE"}")
    }

  val filename = "B-large-practice"
  val writer = new java.io.PrintWriter(filename + ".out")
  try {
    process(io.Source.fromFile(filename + ".in").getLines) { s =>
      writer.println(s); writer.flush()
    }
  } finally {
    writer.flush(); writer.close()
  }

}
