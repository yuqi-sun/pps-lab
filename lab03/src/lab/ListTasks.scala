package lab

import u02.Optionals.Option
import u02.Optionals.Option.{None, Some}
import u02.SumTypes.{Person, Teacher}
import u03.Lists._
import u03.Lists.List.{filter, _}

import scala.annotation.tailrec

object ListTasks {

  @tailrec
  def drop[A](l: List[A], n:Int): List[A] = l match {
      case Cons(_, tail) if n > 0 => drop(tail, n-1)
      case _ => l
  }

  def flatMap[A, B](l:List[A])(f: A => List[B]): List[B] = l match {
    case Cons(head, tail) => append(f(head), flatMap(tail)(f))
    case Nil() => Nil()
  }

  def map[A, B](l: List[A])(mapper: A=>B): List[B] =
    flatMap(l)(v => Cons(mapper(v), Nil()))

  def filter[A](l1: List[A])(predicate: A=>Boolean): List[A] =
    flatMap(l1)(v => if(predicate(v)) {Cons(v, Nil())} else {Nil()})

  @tailrec
  def max(l: List[Int]): Option[Int] = l match {
    case Cons(head, tail) => filter(tail)(_ > head) match {
      case Nil() => Some(head)
      case _ => max(tail)
    }
    case Nil() => None()
  }

  def getCourses(list: List[Person]): List[String] = {
    flatMap(list) {
      case Teacher(_, course) => Cons(course, Nil())
      case _ => Nil()
    }
  }

  @tailrec
  def foldLeft[A, B](list: List[A])(defaultValue: B)(accumulator: (B, A) => B): B = list match {
    case Cons(head, tail) => foldLeft(tail)(accumulator(defaultValue, head))(accumulator)
    case _ => defaultValue
  }

  def foldRight[A, B](list: List[A])(defaultValue: B)(accumulator: (A, B) => B): B = list match {
    case Cons(head, tail) => accumulator(head, foldRight(tail)(defaultValue)(accumulator))
    case _ => defaultValue
  }
}
