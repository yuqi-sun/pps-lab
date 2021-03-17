package lab

import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test
import u03.Lists.List._
import u03.Streams.Stream
import lab.StreamTasks._

class StreamTest {

  val s: Stream[Int] = Stream.take(Stream.iterate(0)(_+1))(10)

  @Test def testDrop(): Unit = {
    assertEquals(Cons(6, Cons(7, Cons(8, Cons(9, Nil())))), Stream.toList(drop(s)(6)))
    assertEquals(Nil(), Stream.toList(drop(s)(20)))
  }
}
