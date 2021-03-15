package lab

import u02.Optionals.Option._
import u03.Lists.List._
import lab.Ex2._
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test

class Ex2Test {
  @Test def testMax(): Unit = {
    assertEquals(Some(25), max(Cons(10, Cons(25, Cons(20, Nil())))))
    assertEquals(Some(25), max(Cons(25, Nil())))
    assertEquals(None(), max(Nil()))
  }
}
