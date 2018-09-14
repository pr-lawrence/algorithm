package richie.rich

import scala.annotation.tailrec

/**
  * @author Lawrence
  * @since 2018.01.17
  * @note https://www.hackerrank.com/challenges/richie-rich/problem
  */
object RichieRich {

  def calc(n: Int, k: Int, s: String): String = {

    @tailrec
    def func(zipped: List[(Char, Int)], result: List[(Char, Int)], acc: Int): (List[(Char, Int)], Int) ={
      if (zipped.isEmpty) (result, acc)
      else if(acc == 0) (result ::: zipped, 0)
      else {
        val head = zipped.head

        if(head._1 == '9') {
          func(zipped.tail, result ::: List(head), acc)
        } else {
          // no more modified
          if(head._2 == 0) {
            if( acc > 1) {
              func(zipped.tail, result ::: List(('9', 2)) , acc - 2)
            } else {
              func(zipped.tail, result ::: List(head), acc)
            }
          } else {
            // upgrade number 9
            func(zipped.tail, result ::: List(('9', 2)), acc - 1)
          }
        }
      }
    }

    val (head, tail, center) = if(s.length % 2 != 0) {
      val (head, tail) = s.splitAt(s.length/2+1)
      (head, tail, head.last)
    } else {
      val (head, tail) = s.splitAt(s.length/2)
      (head, tail, "")
    }

    val zipped = head.zip(tail.reverse)
      .map((x) => if ( x._1 == x._2) (x._1, 0) else if ( x._1 > x._2 ) (x._1, 1) else (x._2, 1)).toList

    val countOfModified = zipped.foldLeft(0)(_ + _._2)

    if(countOfModified > k) "-1"
    else if (countOfModified == k ) {
      val row = zipped.map(_._1).mkString("")
      row + center + row.reverse
    } else {
      val remained = k - countOfModified
      val (improved, acc) = func(zipped, Nil, remained)
      val row = improved.map(_._1).mkString("")

      if(acc > 0 && center != "") {
        row + "9" + row.reverse
      } else {
        row + center + row.reverse
      }
    }
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in);
    val n = sc.nextInt();
    val k = sc.nextInt();
    val s = sc.next();

    println(calc(n, k, s))
  }
}