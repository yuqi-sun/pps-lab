package u04lab.code

trait Complex {
  def re: Double
  def im: Double
  def +(c: Complex): Complex // should implement the sum of two complex numbers..
  def *(c: Complex): Complex // should implement the product of two complex numbers
}

object Complex {
  def apply(re:Double, im:Double):Complex = ComplexImpl(re, im)

  private case class ComplexImpl(override val re:Double, override val im:Double) extends Complex {
    override def +(c: Complex): Complex = ComplexImpl(re+c.re, im + c.im)

    override def *(c: Complex): Complex = ComplexImpl(re*c.re - im*c.im, re*c.im + im*c.re)
  }
}

class ComplexImpl(override val re:Double, override val im:Double) extends Complex {
  override def +(c: Complex): Complex = new ComplexImpl(re+c.re, im + c.im)

  override def *(c: Complex): Complex = new ComplexImpl(re*c.re - im*c.im, re*c.im + im*c.re)
}

object TryComplex extends App {
  //ComplexImpl class
  var a = Array(new ComplexImpl(10,20), new ComplexImpl(1,1), new ComplexImpl(7,0))
  var c = a(0) + a(1) + a(2)
  var n = new ComplexImpl(10, 20)
  println(c, c.re, c.im) //(u04lab.code.ComplexImpl@6a1aab78,18.0,21.0)
  var c2 = a(0) * a(1)
  println(c2, c2.re, c2.im) //(u04lab.code.ComplexImpl@8e24743,-10.0,30.0)
  println(a(0) == n) //false


  //case class
  val b = Array(Complex(10,20), Complex(1,1), Complex(7,0))
  val m = Complex(10, 20)
  val c3 = b(0) + b(1) + b(2)
  println(c3, c3.re, c3.im) //(ComplexImpl(18.0,21.0),18.0,21.0)
  val c4 = b(0) * b(1)
  println(c4, c4.re, c4.im) //(ComplexImpl(-10.0,30.0),-10.0,30.0)
  println(b(0) == m) //true
}

/** Hints:
  * - implement Complex with a ComplexImpl class, similar to PersonImpl in slides
  * - check that equality and toString do not work
  * - use a case class ComplexImpl instead, creating objects without the 'new' keyword
  * - check equality and toString now
  */