package lab

object Functions {

  // parity as function literal
  val parityVal: Int => String = {
    case n if (n%2 == 0) => "even"
    case _ => "odd"
  }

  //parity as method
  def parityMethod(n:Int): String = n match {
    case n if n%2 == 0 => "even"
    case _ => "odd"
  }

  val negVal: (String => Boolean) => (String => Boolean) = f => (s => !f(s))

  //def negMethod(f:(String => Boolean)): (String => Boolean) = s => !f(s)
  def negMethod[A](f:(A => Boolean)): (A => Boolean) = s => !f(s)

  val p1: Int => Int => Int => Boolean = x => y => z => x <= y && y <= z
  val p2: (Int, Int, Int) => Boolean = (x, y, z) => x <= y && y <= z

  def p3(x:Int)(y:Int)(z:Int): Boolean = x <= y && y <= z
  def p4(x:Int, y:Int, z:Int): Boolean = x <= y && y <= z

  //def compose(f:Int => Int, g:Int=>Int): Int => Int = x => f(g(x))
  def compose[A, B, C](f:B => C, g:A => B): A => C = x => f(g(x))

}
