package lab

import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test
import u03.Lists.List._
import u03.Streams.Stream
import lab.StreamTasks._

class StreamTest {

  @Test def testDrop(): Unit = {
    val s: Stream[Int] = Stream.take(Stream.iterate(0)(_+1))(10)
    assertEquals(Cons(6, Cons(7, Cons(8, Cons(9, Nil())))), Stream.toList(drop(s)(6)))
    assertEquals(Nil(), Stream.toList(drop(s)(20)))
    assertEquals(Stream.toList(s), Stream.toList(drop(s)(-1)))
  }

  @Test def testConstant(): Unit = {
    assertEquals(Cons("x", Cons("x", Cons("x", Cons("x", Cons("x", Nil()))))),
      Stream.toList(Stream.take(constant("x"))(5))
    )

    assertEquals(Cons(Nil(), Cons(Nil(), Cons(Nil(), Nil()))),
      Stream.toList(Stream.take(constant(Nil()))(3)))
  }

  @Test def testFib(): Unit = {
    val fibs = fib()
    assertEquals(Cons(0, Cons(1, Cons(1, Cons(2, Cons(3, Cons(5, Cons(8, Cons(13 , Nil())))))))),
      Stream.toList(Stream.take(fibs)(8)))
  }

}
