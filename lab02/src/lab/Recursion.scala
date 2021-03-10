package lab

import scala.annotation.tailrec
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test

object Recursion {

  def fib(n:Int): Int = {
    @tailrec
    def _fib(num: Int, last: Int, secondToLast: Int): Int = num match {
      case 0 => last
      case _ => _fib(num - 1, secondToLast, secondToLast + last)
    }

    _fib(n, 0, 1)
  }

  @Test def testFib(): Unit = {
    assertEquals(0, fib(0))
    assertEquals(1, fib(1))
    assertEquals(3, fib(4))
    assertEquals(8, fib(6))
  }
}
