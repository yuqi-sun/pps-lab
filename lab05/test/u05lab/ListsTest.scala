package u05lab

import u05lab.code._
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._

class ListsTest {

  @Test def testZipRight(): Unit = {
    val l = List("a","b","c")
    assertEquals(List.nil, List.nil.zipRight)
    assertEquals(List(("a",0), ("b",1), ("c",2)), l.zipRight)
  }

  @Test def testPartition(): Unit = {
    val l = List(0, 1, 2, 3, 4, 5)
    assertEquals((List(0, 2, 4), List(1, 3, 5)), l.partition(_%2 == 0))
    assertEquals((List.nil, List.nil), List.nil[Int].partition(_%2 == 0))
    assertEquals((List.nil, l), l.partition(_ > 10))
    assertEquals((l, List.nil), l.partition(_ < 10))
  }

  @Test def testSpan(): Unit = {
    val l = List(0, 2, 4, 1, 0)
    assertEquals((List(0, 2, 4), List(1, 0)), l.span(_%2 == 0))
    assertEquals((List.nil, List.nil), List.nil[Int].span(_%2 == 0))
    assertEquals((List.nil, l), l.span(_ > 10))
    assertEquals((l, List.nil), l.span(_ < 10))
  }

  @Test def testReduce(): Unit = {
    val l = List(0, 1, 2, 3)
    assertEquals(6, l.reduce(_+_))
    assertThrows(classOf[UnsupportedOperationException],() => List.nil[Int].reduce(_+_))
  }
}