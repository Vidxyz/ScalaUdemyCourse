package lectures.part1basics

object DefaultArgs extends App {


  // Default arguments exist in SCALA, same way as java
  def factorial(x: Int, acc: Int = 1): Int = {
    if(x <= 1) acc
    else factorial(x-1, acc*x)
  }

  println(factorial(5))
  /*
   1. Pass in every leading argument
   2. Name the arguments

   DEFAULTS dont have to be on the end of the method signatures here
   factorial(5, acc=1);
   */



}
