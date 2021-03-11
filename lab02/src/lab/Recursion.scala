package lab

import scala.annotation.tailrec

object Recursion {

  def fib(n:Int): Int = {
    @tailrec
    def _fib(num: Int, last: Int, secondToLast: Int): Int = num match {
      case 0 => last
      case _ => _fib(num - 1, secondToLast, secondToLast + last)
    }

    if (n < 0)
      throw new IllegalArgumentException()
    else
      _fib(n, 0, 1)
  }
}
