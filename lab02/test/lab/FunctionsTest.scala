package lab

import lab.Functions._
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test

class FunctionsTest {

  val lowestNumber = 1
  val middleNumber = 2
  val highestNumber = 3

  @Test def testEven() {
    assertEquals("even", parityVal(4))
    assertEquals("even", parityMethod(4))
  }

  @Test def testOdd() {
    assertEquals("odd", parityVal(5))
    assertEquals("odd", parityMethod(5))
  }

  @Test def testNeg() {
    val empty: String => Boolean = _ == ""
    val notEmpty: String => Boolean = negVal(empty)
    assertTrue(empty(""))
    assertFalse(empty("fool"))
    assertTrue(notEmpty("fool"))
    assertFalse(notEmpty(""))
  }

  @Test def testP1(): Unit = {
    testCurrying(p1)
  }

  @Test def testP2(): Unit = {
    testNoCurrying(p2)
  }

  @Test def testP3(): Unit = {
    testCurrying(p3)
  }

  @Test def testP4(): Unit = {
    testNoCurrying(p4)
  }

  @Test def testCompose(): Unit = {
    assertEquals(9, compose((_:Int)-1, (_:Int)*2)(5))
    assertEquals("Hello, Scala", compose((_:String)+"Scala", (_:String)+", ")("Hello"))
  }

  def testCurrying(f:Int => Int => Int => Boolean) {
    assertTrue(f(lowestNumber)(middleNumber)(highestNumber))
    assertFalse(f(lowestNumber)(highestNumber)(middleNumber))
    assertFalse(f(highestNumber)(middleNumber)(lowestNumber))
    assertFalse(f(middleNumber)(lowestNumber)(highestNumber))
    assertFalse(f(middleNumber)(highestNumber)(lowestNumber))
    assertFalse(f(highestNumber)(lowestNumber)(middleNumber))
  }

  def testNoCurrying(f:(Int, Int, Int) => Boolean) {
    assertTrue(f(lowestNumber, middleNumber, highestNumber))
    assertFalse(f(lowestNumber, highestNumber, middleNumber))
    assertFalse(f(highestNumber, middleNumber, lowestNumber))
    assertFalse(f(middleNumber, lowestNumber, highestNumber))
    assertFalse(f(middleNumber, highestNumber, lowestNumber))
    assertFalse(f(highestNumber, lowestNumber, middleNumber))
  }

}
