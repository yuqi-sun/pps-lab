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
}