package u04lab.code

import Lists.List._
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._

class ListFactoryTest {

  val cPPS: Course = Course("PPS", "Viroli")
  val cOOP: Course = Course("OOP", "Viroli")
  val cPCD: Course = Course("PCD", "Ricci")
  val cSDR: Course = Course("SDR", "D'Angelo")

  @Test def testListFactory(): Unit = {
    assertEquals(Cons(1, Cons(2, Cons(3, nil))), ListFactory(1, 2, 3))
    assertEquals(Cons("A", Cons("B", Cons("C", nil))), ListFactory("A", "B", "C"))
    assertEquals(nil, ListFactory())
  }

  @Test def testDoesNotHaveSameTeacher(): Unit = {
    val courses = ListFactory(cPPS, cOOP, cPCD, cSDR)
    courses match {
      case SameTeacher(t) => assertEquals(None, t)
      case _ =>
    }
  }

  @Test def testHasSameTeacher(): Unit = {
    val courses = ListFactory(cPPS, cOOP)
    courses match {
      case SameTeacher(t) => assertEquals("Viroli", t)
      case _ =>
    }
  }

}
