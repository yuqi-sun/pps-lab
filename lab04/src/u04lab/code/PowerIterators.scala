package u04lab.code

import Optionals.Option
import Option.{Some, None}
import Lists.List
import Lists.List.{nil, append, foldRight, reverse}
import Streams._
import Stream._
import scala.util.Random

trait PowerIterator[A] {
  def next(): Option[A]
  def allSoFar(): List[A]
  def reversed(): PowerIterator[A]
}

trait PowerIteratorsFactory {

  def incremental(start: Int, successive: Int => Int): PowerIterator[Int]
  def fromList[A](list: List[A]): PowerIterator[A]
  def randomBooleans(size: Int): PowerIterator[Boolean]
}

class PowerIteratorsFactoryImpl extends PowerIteratorsFactory {

  def toStream[A](list:List[A]): Stream[A] = foldRight(list)(empty[A]())(cons(_, _))

  private case class PowerIteratorImpl[A](private var s:Stream[A]) extends PowerIterator[A] {

    private var pastList: List[A] = nil

    override def next(): Option[A] = s match {
      case Cons(h, t) =>
        val head = h()
        pastList = append(pastList, List.Cons(head, nil))
        s = t()
        Some(head)
      case _ => None()
    }

    override def allSoFar(): List[A] = pastList

    override def reversed(): PowerIterator[A] = PowerIteratorImpl(toStream(reverse(pastList)))
  }

  override def incremental(start: Int, successive: Int => Int): PowerIterator[Int] =
    new PowerIteratorImpl[Int](iterate(start)(successive))

  override def fromList[A](list: List[A]): PowerIterator[A] =
    new PowerIteratorImpl[A](toStream(list))

  override def randomBooleans(size: Int): PowerIterator[Boolean] =
    new PowerIteratorImpl[Boolean](take(generate(Random.nextBoolean()))(size))
}
