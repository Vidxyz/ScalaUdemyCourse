package exercises

import scala.annotation.tailrec

object RecursionExercises extends App {

  def concatN(s: String, n: Int): String = {

    @tailrec
    def helper(current: Int, acc: String): String = {
      if(current == n) acc
      else helper(current+1, acc + s + " ")
    }

    if(n == 1) s
    else helper(1, "")
  }




  def isPrimeTailRec(n: Int): Boolean = {

    @tailrec
    def helper(current: Int, acc: Boolean): Boolean = {
      if (!acc) false
      else if(current <= 1) true
      else helper(current + 1, acc && (n % current != 0))
    }

    helper(n/2, true)

  }



  def fibonacciTailRec(index: Int): Int = {

    @tailrec
    def helper(current: Int, f1: Int, f2: Int): Int = {
      if(current == index) f2 + f1
      else helper(current+1, f2, f1 + f2)
    }

    if(index <= 2) 1
    else helper(3, 1, 1)

  }

//  println(concatN("Potato", 6))
//  println(isPrimeTailRec(2003))

    println(fibonacciTailRec(8))

  // USE AS MANY ACCUMULATORS AS THERE ARE CODE PATHS
  // TAIL RECURSION IS KEY!!!



}
