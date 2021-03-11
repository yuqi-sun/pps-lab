package lab

import lab.Option._
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test

class OptionTest {

  val s1:Option[Int] = Some(2)
  val s2:Option[Int] = Some(3)
  val s3:Option[Int] = None()

  @Test def testFilter(): Unit = {
    assertEquals(s1, filter(s1)(_ > 1))
    assertEquals(None(), filter(s2)(_ > 3))
    assertEquals(None(), filter(s3)(_ > 3))
  }

  @Test def testMap(): Unit = {
    assertEquals(Some(true), map(s1)(_ > 1))
    assertEquals(Some(true), map(s1)(_ == 2))
    assertEquals(None(), map(s3)(_ > 3))
  }

  @Test def testMap2(): Unit = {
    assertEquals(Some(5), map2(s1)(s2)(_ + _))
    assertEquals(Some(false), map2(s1)(s2)(_ > 2 && _ > 2))

    assertEquals(None(), map2(s1)(s3)(_ + _))
    assertEquals(None(), map2(s3)(s1)(_ + _))
    assertEquals(None(), map2(s3)(s3)(_ + _))
  }
}
