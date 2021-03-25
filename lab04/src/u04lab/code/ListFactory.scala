package u04lab.code

import Lists.List
import Lists.List._

object ListFactory {
  def apply[A](elements: A*): List[A] = elements.foldRight(nil[A])(Cons[A])
}

object SameTeacher {

  def unapply(list: List[Course]): Option[String] = map(list)(_.teacher) match {
    case Cons(head, tail) => foldLeft[String, Option[String]](tail)(Some(head))((acc, teacher) => acc match {
      case Some(n) if n == teacher => Some(n)
      case _ => None
    })
    case _ => None
  }
}
