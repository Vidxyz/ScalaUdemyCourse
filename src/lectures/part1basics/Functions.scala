package lectures.part1basics

object Functions extends App {

  def function(a: String, b: Int): String = {
    a + " " + b
  }

  println(function("Hello", 54))

  def parameterLess(): Int = 42069

  println(parameterLess())
  println(parameterLess)


  // Recursive functions need explicit return types
  // Non-recursive can get away with it
  // Use types nonetheless
  def repeater(s: String, b: Int): String = {
    if(b == 1) s
    else s + " " +  repeater(s, b-1)
  }

  print(repeater("Hello", 5))

  // WHEN YOU NEED LOOPS, USE RECURSION


  def functionWSideEffects(a: String): Unit = {
    println(a);
  }

  // Code blocks can have auxiliary functions
  def bigFunction(n: Int): Int = {
    def small(a: Int, b: Int): Int = { a + b }

    small(n, n-1)

   }

}


/*
  1. Greeting
 */