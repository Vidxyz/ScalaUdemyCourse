package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int = {
    if(n <= 1) 1
    else {
      println("Computing factorial of " + n)
      val result = n * factorial(n-1)
      println("Computed")
      result
    }
  }

//  factorial(10)

  // Each recursive call uses stack frame

  // Use tail recursion saves call stack space
  def fact2(n: Int): BigInt = {
    @tailrec
    def helper(x: Int, acc: BigInt): BigInt = {
      if(x<=1) acc
      else helper(x-1, acc * x) // Tail recursion = USe recurive call as the LAST expression on each code path
    }

    helper(n, 1)
  }

//  factorial(5000)
  println(fact2(22000))


  // WHEN YOU NEED LOOPS, USE TAIL RECURSION


}
