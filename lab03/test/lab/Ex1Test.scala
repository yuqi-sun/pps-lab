package lab

import Ex1._
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test
import u03.Lists._
import u03.Lists.List._

class Ex1Test {

  val l: Cons[Int] = List.Cons(10, List.Cons(20, List.Cons(30, List.Nil())))

  @Test def testDrop(): Unit = {
    assertEquals(Cons(20, Cons(30, Nil())), drop(l, 1))
    assertEquals(Cons(30, Nil()), drop(l, 2))
    assertEquals(Nil(), drop(l, 5))
    assertEquals(Nil(), drop(Nil(), 5))
  }

  @Test def testFlatMap(): Unit = {
    assertEquals(Cons(11, Cons(21, Cons (31, Nil ()))), flatMap(l)(v => Cons(v+1, Nil())))
    assertEquals(Cons(11, Cons(12 ,Cons (21, Cons(22, Cons(31, Cons(32, Nil ())))))),
      flatMap (l)(v => Cons (v+1, Cons(v+2, Nil ())))
    )
    assertEquals(Nil(), flatMap(Nil[Int]())(v => Cons(v+1, Nil())))
  }

  @Test def testMap(): Unit = {
    assertEquals(Cons(11, Cons(21, Cons (31, Nil ()))), Ex1.map(l)(v => v+1))
    assertEquals(Nil(), Ex1.map(Nil[Int]())(v => v+1))
  }

  @Test def testFilter(): Unit = {
    assertEquals(Cons(20, List.Cons(30, List.Nil())), Ex1.filter(l)(_ >= 20))
    assertEquals(Nil(), Ex1.filter(l)(_ >= 50))
  }

}
