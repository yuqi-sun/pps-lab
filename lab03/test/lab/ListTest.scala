package lab

import ListTasks._
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test
import u02.Optionals.Option.{None, Some}
import u02.SumTypes.{Person, Student, Teacher}
import u03.Lists._
import u03.Lists.List._

class ListTest {

  //Ex.1 variables
  val l: Cons[Int] = List.Cons(10, List.Cons(20, List.Cons(30, List.Nil())))

  //Ex.3 variables
  val listWithProf: List[Person] = List.Cons(Student("Yuqi", 1), Cons(Teacher("Viroli", "PPS"),
    Cons(Teacher("Ricci", "PCD"), Cons(Student("Sun", 2), Nil()))))
  val listWithoutProf: List[Person] = List.Cons(Student("Yuqi", 1), Cons(Student("Sun", 2), Nil()))

  //Ex.4 variables
  val lst: List[Int] = Cons(3, Cons(7, Cons(1, Cons(5, Nil()))))

  //Ex.1 test
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
    assertEquals(Cons(11, Cons(21, Cons (31, Nil ()))), ListTasks.map(l)(v => v+1))
    assertEquals(Nil(), ListTasks.map(Nil[Int]())(v => v+1))
  }

  @Test def testFilter(): Unit = {
    assertEquals(Cons(20, List.Cons(30, List.Nil())), ListTasks.filter(l)(_ >= 20))
    assertEquals(Nil(), ListTasks.filter(l)(_ >= 50))
  }

  //Ex.2 test
  @Test def testMax(): Unit = {
    assertEquals(Some(30), max(l))
    assertEquals(Some(25), max(Cons(25, Nil())))
    assertEquals(None(), max(Nil()))
  }

  //Ex.3 test
  @Test def testGetCourses(): Unit = {
    assertEquals(Cons("PPS", Cons("PCD", Nil())), getCourses(listWithProf))
    assertEquals(Nil(), getCourses(listWithoutProf))
  }

  //Ex.4 test
  @Test def testFoldLeft(): Unit = {
    assertEquals(-16, foldLeft(lst)(0)(_-_))
    assertEquals(16, foldLeft(lst)(0)(_+_))
    assertEquals(0, foldLeft(Nil[Int]())(0)(_+_))
  }

  @Test def testFoldRight(): Unit = {
    assertEquals(-8, foldRight(lst)(0)(_-_))
    assertEquals(16, foldRight(lst)(0)(_+_))
    assertEquals(0, foldRight(Nil[Int]())(0)(_+_))
  }

}
