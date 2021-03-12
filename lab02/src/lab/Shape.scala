package lab

sealed trait Shape

object Shape {

  case class Rectangle(base:Double, height:Double) extends Shape
  case class Circle(radius:Double) extends Shape
  case class Square(side:Double) extends Shape

  def perimeter(shape:Shape): Double = shape match {
    case Rectangle(base, height) => (base+height)*2
    case Circle(radius) => radius*2*Math.PI
    case Square(side) => side*4
  }

  def area(shape:Shape): Double = shape match {
    case Rectangle(base, height) => base*height
    case Circle(radius) => Math.pow(radius, 2)*Math.PI
    case Square(side) => Math.pow(side, 2)
  }
}
