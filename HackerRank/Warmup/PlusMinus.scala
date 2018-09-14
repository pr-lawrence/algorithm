/**
  *
  * @author Lawrence
  * @since 2018. 3. 13.
  * @note https://www.hackerrank.com/challenges/plus-minus/problem
  * @version
  */
object Solution {

    def plusMinus(arr: Array[Int]):Unit  =  {
        // Complete this function
      val size = arr.length
      val plus = arr.filter(_ > 0).length
      val minus = arr.filter(_ < 0).length
      val zero = arr.filter(_ == 0).length
      
      println(plus.toDouble/size)
      println(minus.toDouble/size)
      println(zero.toDouble/size)
    }

    def main(args: Array[String]) {
        val sc = new java.util.Scanner (System.in);
        var n = sc.nextInt();
        var arr = new Array[Int](n);
        for(arr_i <- 0 to n-1) {
           arr(arr_i) = sc.nextInt();
        }
        plusMinus(arr);
    }
}
