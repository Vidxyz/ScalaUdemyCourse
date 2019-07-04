package lectures.part3functionalprogramming

object WhatsAFunction extends App {

  // DREAM : use functions are first class elements (variables/numbers)
  // Problem - OOP

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // Function Types = Function(1...22)[A,B]

  val stringToIntConverter = new Function[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }

  println(stringToIntConverter("3") + 7)


  val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  // Function types Function2[A,B,R] === ((A,B) => R)
  // Function1 takes 1 parameters
  // Function2 takes 2 parameters
  // ...
  // Function22 takes 22 parameters

  // ALL SCALA FUNCTIONS ARE OBJECTS - behind the scenes implementation

  val concatString: (String, String) => String = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  println(concatString("potato", " chips"))



  val superAdder = new Function[Int, Function1[Int, Int]] {
    override def apply(v1: Int): Int => Int = new Function[Int, Int] {
      override def apply(v2: Int): Int = v2 + v1
    }
  }

  val adder3 = superAdder(3)

  println(adder3(4))
  println(superAdder(3)(4))
  println(superAdder(5)(11)) // --> THIS IS CALLED A CURRIED FUNCTION
  // Means they can be called with multiple parameter list



}

trait MyFunction[A, B] {
  def apply(element: A): B
}