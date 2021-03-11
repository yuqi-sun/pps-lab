package lab

sealed trait Option[A]

object Option {
  case class None [A]() extends Option [A]
  case class Some [A]( a: A) extends Option [A]

  def filter[A](opt: Option[A])(predicate: A => Boolean): Option[A] = opt match{
    case Some(a) =>
      if (predicate(a))
        Some(a)
      else
        None()
    case _ => None()
  }

  def map[A, B](opt: Option[A])(mapFunction: A => B): Option[B] = opt match {
    case Some(a) => Some(mapFunction(a))
    case _ => None()
  }

  def map2[A, B, C](opt1:Option[A])(opt2: Option[B])(f: (A, B) => C): Option[C] = (opt1, opt2) match {
    case (Some(a), Some(b)) => Some(f(a, b))
    case _ => None()
  }
}
