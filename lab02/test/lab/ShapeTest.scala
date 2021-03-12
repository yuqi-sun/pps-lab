package lab

import lab.Shape._
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test

class ShapeTest {

  val side:Double = 1
  val base:Double = 5
  val height:Double = 3
  val radius:Double = 3

  @Test def testRectangle() {
    val rectangle = Rectangle(base, height)
    assertEquals((base+height)*2, perimeter(rectangle))
    assertEquals(base*height, area(rectangle))
  }

  @Test def testCircle() {
    val circle = Circle(radius)
    assertEquals(2*radius*Math.PI, perimeter(circle))
    assertEquals(Math.pow(radius, 2)*Math.PI, area(circle))
  }

  @Test def testSquare() {
    val square = Square(side)
    assertEquals(side*4, perimeter(square))
    assertEquals(Math.pow(side, 2), area(square))
  }

}
