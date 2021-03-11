package lab

import lab.Recursion.fib
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test

class RecursionTest {

  @Test def testFib(): Unit = {
    assertEquals(0, fib(0))
    assertEquals(1, fib(1))
    assertEquals(3, fib(4))
    assertEquals(8, fib(6))
  }

  @Test def testFibException(): Unit = {
    assertThrows(classOf[IllegalArgumentException], () => fib(-1))
  }

}
