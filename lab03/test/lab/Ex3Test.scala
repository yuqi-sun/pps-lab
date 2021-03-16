package lab
import lab.Ex3.getCourses
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test
import u02.SumTypes._
import u03.Lists._
import u03.Lists.List._

class Ex3Test {
  val listWithProf: List[Person] = List.Cons(Student("Yuqi", 1), Cons(Teacher("Viroli", "PPS"),
    Cons(Teacher("Ricci", "PCD"), Cons(Student("Sun", 2), Nil()))))

  val listWithoutProf: List[Person] = List.Cons(Student("Yuqi", 1), Cons(Student("Sun", 2), Nil()))

  val result: List[String] = List.Cons("PPS", Cons("PCD", Nil()))

  @Test def testGetCourses(): Unit = {
    assertEquals(result, getCourses(listWithProf))
    assertEquals(Nil(), getCourses(listWithoutProf))
  }
}
