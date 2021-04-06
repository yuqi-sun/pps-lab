package u05lab.code

import java.util.concurrent.TimeUnit
import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.concurrent.duration.FiniteDuration

object PerformanceUtils {
  case class MeasurementResults[T](result: T, duration: FiniteDuration) extends Ordered[MeasurementResults[_]] {
    override def compare(that: MeasurementResults[_]): Int = duration.toNanos.compareTo(that.duration.toNanos)
  }

  def measure[T](msg: String)(expr: => T): MeasurementResults[T] = {
    val startTime = System.nanoTime()
    val res = expr
    val duration = FiniteDuration(System.nanoTime()-startTime, TimeUnit.NANOSECONDS)
    if(!msg.isEmpty) println(msg + " -- " + duration.toNanos + " nanos; " + duration.toMillis + "ms")
    MeasurementResults(res, duration)
  }

  def measure[T](expr: => T): MeasurementResults[T] = measure("")(expr)
}


object CollectionsTest extends App {

  import PerformanceUtils._

  def create[T](msg: String)(expr: => T) = {
    measure(msg + "creation")(expr).result
  }

  def query[T](head: => T)(tail: => T)(size: => T) = {
    measure("read head")(head).duration
    measure("read tail")(tail).duration
    measure("read size")(size).duration
  }

  def update[T](insert: => T) = {
    measure("insert")(insert).duration
  }

  def remove[T](delete: => T) ={
    measure("remove")(delete).duration
  }

  def printResult[T](msg: String)(head: => T, tail: => T, size: => T, add: => T, delete: => T): Unit = {
    println("------" + msg + "------")
    query(head)(tail)(size)
    update(add)
    remove(delete)
  }

  val range = 1 to 1000000
  val num = 1000001

  /* Linear sequences: List, ListBuffer */
  val list = create("list ")(range.toList)
  val listBuff = create("list buffer ")(ListBuffer.from(range))
  printResult("list")(list.head, list.tail, list.size, list:+num, list.take(list.size-1))
  printResult("list buffer")(listBuff.head, listBuff.tail, listBuff.size, listBuff:+num, listBuff.remove(listBuff.size-1))
  println("\n")

  /* Indexed sequences: Vector, Array, ArrayBuffer */
  val vector = create("vector ")(range.toVector)
  val array = create("array ")(range.toArray)
  val arrayBuff = create("array buffer")(ArrayBuffer.from(range))
  printResult("vector")(vector.head, vector.tail, vector.size, vector:+num, vector.take(vector.size-1))
  printResult("array")(array.head, array.tail, array.length, array:+num,  array.take(array.length-1))
  printResult("array buffer")(arrayBuff.head, arrayBuff.tail, arrayBuff.size, arrayBuff:+num, arrayBuff.remove(arrayBuff.size-1))
  println("\n")

  /* Sets */
  val set = create("set ")(range.toSet)
  val hashSet = create("hash set ")(mutable.HashSet.from(range))
  printResult("set")(set.head, set.tail, set.size, set+num, set-num)
  printResult("hash set")(hashSet.head, hashSet.tail, hashSet.size, hashSet+=num, hashSet.remove(num))
  println("\n")

  /* Maps */
  val map = create("map ")(range.map(v => (v, v)).toMap)
  val hashMap = create("mutable map ")(mutable.HashMap.from(range.map(v => (v, v))))
  printResult("map")(map.head, map.tail, map.size, map+(num -> num), map-num)
  printResult("mutable map")(hashMap.head, hashMap.tail, hashMap.size, hashMap.addOne(num -> num), hashMap.remove(num))
}