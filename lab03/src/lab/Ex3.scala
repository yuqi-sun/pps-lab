package lab

import u02.SumTypes._
import u03.Lists._
import u03.Lists.List._
import lab.Ex1.flatMap


object Ex3 {

  def getCourses(list: List[Person]): List[String] = {
    flatMap(list) {
      case Teacher(_, course) => Cons(course, Nil())
      case _ => Nil()
    }
  }

}
