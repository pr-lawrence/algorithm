import scala.collection.mutable._

/**
  * @author Lawrence
  * @since 2018.01.16
  * @note https://www.hackerrank.com/challenges/countingsort4/problem
  */
object TheFullCountingSort {

  def translate(input: List[ArrayBuffer[String]]): String = {
    input.map(x => x.mkString(" ")).mkString(" ")
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    val n = sc.nextInt()

    val list = List.fill(100)(ArrayBuffer[String]())

    for (x <- 0 until n) {
      val number = sc.nextInt
      val word = sc.next
      val adjustWord = if (n / 2 > x) "-" else word
      list(number).append(adjustWord)
    }
    println(translate(list))
  }
}
