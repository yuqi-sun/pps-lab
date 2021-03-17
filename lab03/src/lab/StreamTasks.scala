package lab

import u03.Streams.Stream
import u03.Streams.Stream._

import scala.annotation.tailrec

object StreamTasks {

  @tailrec
  def drop[A](stream: Stream[A])(n: Int): Stream[A] = stream match {
    case Cons(_, tail) if n > 0 => drop(tail())(n-1)
    case _ => stream
  }

  def constant[A](seed: => A): Stream[A] = cons(seed, constant(seed))

  def fib(): Stream[Int] = {
    def _fib(last: => Int)(secondToLast: => Int): Stream[Int] =
      cons(last, _fib(secondToLast)(last+secondToLast))

    _fib(0)(1)
  }
}
