package journey.to.the.moon

/**
  * @author Lawrence
  * @since 2018.01.16
  * @note https://www.hackerrank.com/challenges/journey-to-the-moon/problem
  */
object JourneyToTheMoon {

  def journeyToMoon(n: Int, astronaut: Array[Array[Int]]): Long =  {
    val calc = (x: List[List[Int]], y: List[Int]) => {
      if(x.isEmpty) List(y)
      else {
        val eithers = x.map { xx =>
          xx.filter(y.contains(_)) match {
            case Nil =>
              Left(xx)
            case xs =>
              Right((xx ::: y).distinct)
          }
        }

        if (eithers.filter(_.isRight).size == 0) {
          x :+ y
        } else if( eithers.filter(_.isRight).size == 1) {
          eithers.map(x => if(x.isLeft) x.left.get else x.right.get)
        } else {
          eithers.filter(_.isRight).map(_.right.get).flatten.distinct +: eithers.filter(_.isLeft).map(_.left.get)
        }
      }
    }

    // Complete this function
    val total = (1 until n).foldLeft(0L)(_ + _)

    val countOfImPossible = astronaut.map(_.toList).toList.foldLeft(List.empty[List[Int]])(calc)
      .map(x => (1 until x.size).foldLeft(0L)(_ + _)).foldLeft(0L)(_ + _)

    total - countOfImPossible
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in);
    var n = sc.nextInt();
    var p = sc.nextInt();
    var astronaut = Array.ofDim[Int](p,2);
    for(astronaut_i <- 0 to p-1) {
      for(astronaut_j <- 0 to 2-1){
        astronaut(astronaut_i)(astronaut_j) = sc.nextInt();
      }
    }
    val result = journeyToMoon(n, astronaut);
    println(result)
  }
}
