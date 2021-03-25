package u04lab.code

import Lists.List
import Lists.List._

object ListFactory {
  def apply[A](elements: A*): List[A] = elements.foldRight(nil[A])(Cons[A])
}

object SameTeacher {

  def unapply(list: List[Course]): Option[String] = list match {
    case Cons(head, tail) =>
      val l = foldLeft(tail)(nil[Boolean])((acc, course) => Cons(course.teacher == head.teacher, acc))
      filter(l)(_ == false) match {
        case Nil() => Some(head.teacher)
        case _ => None
      }
    case _ => None
  }
}
