package lectures.part2oop

object Exceptions extends App{

  val x: String = null
//  println(x.length)
    // ^ Will cause NPE
    // Throwing and catching exceptions

  // 1. Throwing Exceptions
  // val weirdValue: String = throw new NullPointerException

  // Throwable classes extend the Throwable class.
  // Exception and Error are the major Throwable subtypes
  // Exception - went wrong with program
  // Error - went wrong with the system

  // 2. How to Catch exceptions
  def getInt(withException: Boolean): Int = {
    if (withException) throw new RuntimeException("No Int for you")
    else 42
  }

  // Try catch is also an EXPRESSION
  val potentialFail = try {
    // Code that might throw
    getInt(true)
  }
  catch {
    case e: NullPointerException => println("Caught a Null pointer exception")
    case e: RuntimeException => println("Caught a runtime exception")
  }
  finally {
    // Code that will get executed NO MATTER WHAT
    // this block is optional
    // Does NOT influence the RETURN value of the expression
    // Use finally ONLY for side effects :- For eg, logging something to a file
    println("Finally")
  }

  // 3. How to define your own Exceptions
  class MyException extends Exception

  val exception = new MyException
//  throw exception

  println(potentialFail)

//  throw new StackOverflowError
//  throw new OutOfMemoryError

//  This crashes JVM with an out of memoery error
//  val array = Array.ofDim(Int.MaxValue)

    // This casues stack overflow error
    /*
    def inf: Int = 1 + inf
    val noLimit = inf
     */

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MatchCalcException extends RuntimeException("Division By 0")

  object PocketCalculator {

    def add(x: Int, y: Int): Int = {
      if (x > 0 && y > 0 &&  x + y < 0) throw new OverflowException
      else if (x < 0 && y < 0 && x + y > 0) throw new UnderflowException
      else x + y
    }

    def sub(x: Int, y: Int): Int = {
      if (x > 0 && y < 0 && x - y < 0) throw new OverflowException
      if (x < 0 && y > 0 && x - y > 0) throw new UnderflowException
      else x + y
    }

    def multiply(x: Int, y: Int): Int = {
      if (x > 0 && y > 0 && x * y < 0) throw new OverflowException
      else if (x < 0 && y < 0 && x * y < 0) throw new OverflowException
      else if (x < 0 && y > 0 && x * y > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && x * y > 0) throw new UnderflowException
      else x * y
    }

    def divide(x: Int, y: Int): Int = {
      if (y == 0) throw new MatchCalcException
      else x / y
    }

  }

  // All these 4 throw exceptions are defined above
  /*
  println(PocketCalculator.add(Int.MaxValue, 1))
  println(PocketCalculator.sub(Int.MinValue, 1))
  println(PocketCalculator.divide(5, 0))
  println(PocketCalculator.multiply(Int.MaxValue, 2))
  */

}
