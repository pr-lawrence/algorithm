object Main extends App {
 

  trait State
  case class Closed(last: Option[Char]) extends State
  case class Open(n: Int, last: Option[Char]) extends State
  
  def ignoreComments(in: Iterator[Char])(out: Char => Unit): Unit = {
 
    def step(s: State, ch: Char): State = (s, ch) match {
    
        case (Closed(Some('/')),'*') => Open(1, None)
        case (Closed(last), ch) => 
          if (last.nonEmpty) out(last.get)
          Closed(Some(ch))
        case (Open(n, Some('/')), '*') => Open(n+1, Some(ch))
        case (Open(1, Some('*')), '/') => Closed(None)
        case (Open(n, Some('*')), '/') => Open(n-1, None)
        case (Open(n, last), ch) => Open(n, Some(ch))
      }
    
    (Closed(None) /:[State] in) (step)  match {
      case Closed(Some(ch)) => out(ch)
      case _ => 
    }
     
  }
 
  def process(in: Iterator[Char])(charOut: Char => Unit) = {
    
    ignoreComments(in)(charOut)
 
  }
 
  val writer = new java.io.PrintWriter("e.small.out")
  try {
    writer.println("Case #1:")
    process(io.Source.fromFile("E-small-practice.in").iter)(writer.print)
  } finally {
    writer.flush(); writer.close()
  }
 
}
