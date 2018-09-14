object sandbox {

  val l = List(1, 2, 3, 4)                        //> l  : List[Int] = List(1, 2, 3, 4)

  l.head                                          //> res0: Int = 1
  
  0 +: l                                          //> res1: List[Int] = List(0, 1, 2, 3, 4)
  
  List(-1, 0) ::: l                               //> res2: List[Int] = List(-1, 0, 1, 2, 3, 4)
  
  l :+ 5                                          //> res3: List[Int] = List(1, 2, 3, 4, 5)
}