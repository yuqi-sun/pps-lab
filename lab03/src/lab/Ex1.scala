package lab

import u03.Lists._
import u03.Lists.List._

import scala.annotation.tailrec

object Ex1 {

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
}
