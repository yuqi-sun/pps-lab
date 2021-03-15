package lab

import u02.Optionals.Option
import u02.Optionals.Option._
import u03.Lists._
import u03.Lists.List._

import scala.annotation.tailrec

object Ex2 {

  @tailrec
  def max(l: List[Int]): Option[Int] = l match {
    case Cons(head, tail) =>
      if (filter(tail)(_ > head) == Nil())
        Some(head)
      else
        max(tail)
    case Nil() => None()
  }

}
