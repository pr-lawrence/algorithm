package main

import scala.io.Source
import java.io.PrintWriter
import java.io.File

object sortingMain {

  val in = Source.fromFile("small.in").getLines
  val printer = new PrintWriter(new File("large.out"))

  def sort(arr: Array[Int]): Array[Int] = {
    val (alexBooks ,bobBooks) = arr.partition(_ % 2 != 0)
    
    val (oddIndexs , evenIndexs) = arr.zipWithIndex.partition(_._1  % 2 != 0)
    
    (alexBooks.sorted.zip(oddIndexs.map(_._2)) ++ bobBooks.sorted.reverse.zip(evenIndexs.map(_._2)) ).sortBy(_._2).map(_._1)
  }

  def main(args: Array[String]) {
    process()
  }

  def process() {
    for { i <- 1 to in.next.toInt } {
      in.next
      println(s"Case #$i: ${sort(in.next.split(" ").map(_.toInt)).mkString(" ")}")
    }
    printer.flush
  }
}