package lectures.part3fp

object AnonymousFunctions extends App {

  // Using anonymous function and instantiating on the spot
  val doubler = new Function[Int, Int] {
    override def apply(v1: Int): Int = v1 * 2
  }


  // Anonymous function (LAMBDA)
  //                     this is optional
  val doubler2: Int => Int = (x: Int) => x * 2

  println(doubler(2))
  println(doubler2(2))

  // Multiple parameters in lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // No params
  val doSomething = () => 3

  // HOWEVER, this prints object path
  println(doSomething) // function itself
  // Need () to call LAMBDAS!
  println(doSomething())

  // curly braces with lambdas
  val stringToInt: String => Int = { (str: String) =>
    str.toInt
  }

  //--------------------
  // MORE SYNTACTIC SUGAR
  val niceIncrementer: Int => Int = (x: Int) => x + 1
  // THese both are equivalent!
  val niceIncrementer1: Int => Int = _ + 1


  val niceAdder: (Int, Int) => Int = (a: Int, b: Int) => a +  b
  // These both are equivalent
  val niceAdder2: (Int, Int) => Int = _ + _
  // EACH UNDERSCORE REPLACES A PARAMETER, IN THAT ORDER

  /*
  EXERCISES
  ---
  1. MyList - replace all functionX calls with lambdas
  2. Rewrite the special adder as an anonymous function
  ---
   */

  // Same curried function written earlier in WhatsAFunction class
  val superAdd: Int => (Int => Int) = (x: Int) => (y: Int) => x + y

  println(superAdd(4)(9))


}
