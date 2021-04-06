package u05lab.code
import collection.immutable.List

object Ex4 extends App {

  def sequence[A](a: List[Option[A]]): Option[List[A]] = {
    if (a.contains(None))
      None
    else
      a.foldRight(Some(List.empty[A]))((option, acc) => Some(acc.get.prepended(option.get)))
  }

  println(sequence(List(Some(1),Some(2),Some(3)))) // Some(List(1, 2, 3))
  println(sequence(List(Some(1),None,Some(3)))) //None

}
