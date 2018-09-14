object sandbox {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(44); 

  val l = List(1, 2, 3, 4);System.out.println("""l  : List[Int] = """ + $show(l ));$skip(10); val res$0 = 

  l.head;System.out.println("""res0: Int = """ + $show(res$0));$skip(12); val res$1 = 
  
  0 +: l;System.out.println("""res1: List[Int] = """ + $show(res$1));$skip(23); val res$2 = 
  
  List(-1, 0) ::: l;System.out.println("""res2: List[Int] = """ + $show(res$2));$skip(12); val res$3 = 
  
  l :+ 5;System.out.println("""res3: List[Int] = """ + $show(res$3))}
}
