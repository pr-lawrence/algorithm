package main

import scala.io.Source
import java.io.PrintWriter
import java.io.File

object RationaNumberTree {
  
  val in = Source.fromFile("small.in").getLines
  val printer = new PrintWriter(new File("small.out"))
  
  def main(args: Array[String]) = {
    
    val lines = in.next
    
    for ( line <- lines) {
    }
  }

  def findItsPosition(n: BigInt): (BigInt, BigInt) = {
    def travelsalTree(xs: String, p: BigInt, q: BigInt): (BigInt, BigInt) = {
      if (xs.isEmpty) (p, q)
      else {
        if (xs.head.equals('0')) travelsalTree(xs.tail, p, q + p)
        else travelsalTree(xs.tail, p + q, q)
      }
    }
    val x = n.toString(2)
    if (n == 1) (1, 1)
    else {
      travelsalTree(x.tail,1,1)
    }
  }

  def findNth(p: BigInt, q: BigInt): BigInt = {
    def findLocation(_p: BigInt, _q: BigInt): String = {
      var str: String = ""
      if (_p == _q) ""
      else {
        if (_p > _q) {
          "1" + findLocation(_p - _q, _q)
        } else {
          "0" + findLocation(_p, _q - _p)
        }
      }
    }

    val line = findLocation(p, q).reverse
    Math.pow(2, line.length.toDouble).toInt + Integer.parseInt(line, 2)
  }

}