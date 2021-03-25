package u04lab.code

import Optionals._
import Lists.List._
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._

class PowerIteratorsTest {

  val factory = new PowerIteratorsFactoryImpl()

  @Test def testIncremental() {
    val pi = factory.incremental(5,_+2); //pi produce 5,7,9,11,13,...
    assertEquals(Option.of(5), pi.next())
    assertEquals(Option.of(7), pi.next())
    assertEquals(Option.of(9), pi.next())
    assertEquals(Option.of(11), pi.next())
    assertEquals(Cons(5, Cons(7, Cons(9, Cons(11, nil)))), pi.allSoFar()); //elementi già prodotti
    for (_ <- 0 until 10) {
      pi.next(); //procedo in avanti per un po'..
    }
    assertEquals(Option.of(33), pi.next()); //sono arrivato a 33
  }

  @Test def testRandom(): Unit = { //semi-automatico, si controlleranno anche le stampe a video
    val pi = factory.randomBooleans(4) //pi produce 4 booleani random
    val b1 = pi.next()
    val b2 = pi.next()
    val b3 = pi.next()
    val b4 = pi.next()
    System.out.println(b1 + " " + b2 + " " + b3 + " " + b4) //stampo a video.. giusto per vedere se sono proprio random..

    assertTrue(Option.isEmpty(pi.next())) //ne ho già prodotti 4, quindi il prossimo è un opzionale vuoto

    assertEquals(Cons(b1, Cons(b2, Cons(b3, Cons(b4, nil)))), map(pi.allSoFar())(Option.Some(_))) // ho prodotto proprio b1,b2,b3,b4
  }

  @Test def testFromList(): Unit = {
    val pi = factory.fromList(Cons("a", Cons("b", Cons("c", nil)))) // pi produce a,b,c
    assertEquals(Option.of("a"), pi.next())
    assertEquals(Option.of("b"), pi.next())
    assertEquals(Cons("a", Cons("b", nil)), pi.allSoFar()) // fin qui a,b

    assertEquals(Option.of("c"), pi.next())
    assertEquals(Cons("a", Cons("b", Cons("c", nil))), pi.allSoFar()) // fin qui a,b,c

    assertTrue(Option.isEmpty(pi.next())) // non c'è più niente da produrre

  }

  @Test def optionalTestReversedOnList(): Unit = {
    val pi = factory.fromList(Cons("a", Cons("b", Cons("c", nil))))
    assertEquals(Option.of("a"), pi.next())
    assertEquals(Option.of("b"), pi.next())
    val pi2 = pi.reversed() //pi2 itera su b,a
    assertEquals(Option.of("c"), pi.next()) // c viene prodotto da pi normalmente

    assertTrue(Option.isEmpty(pi.next()))
    assertEquals(Option.of("b"), pi2.next())
    assertEquals(Option.of("a"), pi2.next())
    assertEquals(Cons("b", Cons("a", nil)), pi2.allSoFar()) // pi2 ha prodotto b,a

    assertTrue(Option.isEmpty(pi2.next()))
  }

  @Test def optionalTestReversedOnIncremental(): Unit = {
    val pi = factory.incremental(0, x => x + 1) // 0,1,2,3,...
    assertEquals(Option.of(0), pi.next())
    assertEquals(Option.of(1), pi.next())
    assertEquals(Option.of(2), pi.next())
    assertEquals(Option.of(3), pi.next())
    val pi2 = pi.reversed() // pi2 itera su 3,2,1,0
    assertEquals(Option.of(3), pi2.next())
    assertEquals(Option.of(2), pi2.next())
    val pi3 = pi2.reversed() // pi2 ha prodotto 3,2 in passato, quindi pi3 itera su 2,3
    assertEquals(Option.of(2), pi3.next())
    assertEquals(Option.of(3), pi3.next())
    assertTrue(Option.isEmpty(pi3.next()))
  }
}