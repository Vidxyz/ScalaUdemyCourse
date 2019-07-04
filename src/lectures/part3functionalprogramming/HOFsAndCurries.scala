package lectures.part3functionalprogramming

import scala.annotation.tailrec

object HOFsAndCurries extends App {

//  var superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = ???
  // HOF - higher order functions
  // map, flatmap, filter, are all HOFs

  // Function that applies a function n-times over a given value
  // nTimes(f, n, x) - applies f, n times, on x
  // nTimes(f, 3, x) = f(f(f(x)))


  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if (n <= 0) x
    else nTimes(f, n-1, f(x))
  }

  val plusOne: Int => Int = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 1))


  // Better way to implement, instead of passing all params at once,
  // Func n times takes a func, and a number, and then you can ....
  // Example

  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) = {
    if(n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n-1)(f(x))
  }

  var plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))

  //  ntb(f,n) = x => f(f(f...(x))
  // increment10 = ntb(plusOne, 10) = x => plusOne(plusOne(...(x))
  // val y = increment10(1)


  // CURRIED FUNCTIONS
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3) // y => 3 + y
  println(add3(10))
  println(superAdder(3)(10))

  // Functions with MULTIPLE parameter lists ACT like curried functions
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  // MUST SPECIFY types
  val standardFormat: Double => String = curriedFormatter("%4.2f")
  val preciseFormat: Double => String = curriedFormatter("%10.8f")


  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))


  /*
  1. Expand MyList
    - forEach method : Receives A => Unit and applies to every element
      [1,2,3].forEach(x => println(x))

    - sort function ((A, A) => Int) => MyList
      [1,2,3].sort((x,y) => y - x) => [3,2,1]

    - zipWith (list, (A, A) => B) => MyList[B]
      [1,2,3].zipWith([4,5,6], x * y) => [4, 10, 18]

    - fold(start) (function) => a value
      [1,2,3].fold(0)(x + y) = 6

   2. toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
      fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int

   3. compose(f, g) => x => f(g(x))
      andThen(f, g) => x => g(f(x))
   */

  // TODO: VERY IMPORTANT TO UNDERSTAND THIS
  // Return type is a LAMBDA
  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) = {
    x => y => f(x, y)
  }

  // RETURN type is a LAMBDA
  def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int = {
    (x, y) => f(x)(y)
  }


  def compose[A, B, T](f: A => B, g: T => A): T => B = {
    x => f(g(x))
  }

  def andThen[A, B, T](f: A => B, g: B => T): A => T = {
    x => g(f(x))
  }

  var superAdderFunction: (Int => Int => Int) = toCurry((x,y) => x + y)
  var adder4 = superAdderFunction(4)
  println(adder4(17))


  var simpleAdder = fromCurry(superAdder)
  println(simpleAdder(4, 17))

  val add2: Int => Int = (x: Int) => x + 2
  val times3: Int => Int = (x: Int) => x * 3

  val composed = compose(add2, times3)
  val ordered = andThen(add2, times3)

  println(composed(4))
  println(ordered(4))


}
