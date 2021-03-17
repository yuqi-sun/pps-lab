package lab

import u03.Streams.Stream
import u03.Streams.Stream.{Cons, cons, iterate}

import scala.annotation.tailrec

object StreamTasks {

  @tailrec
  def drop[A](stream: Stream[A])(n: Int): Stream[A] = stream match {
    case Cons(_, tail) if n > 0 => drop(tail())(n-1)
    case _ => stream
  }
}
