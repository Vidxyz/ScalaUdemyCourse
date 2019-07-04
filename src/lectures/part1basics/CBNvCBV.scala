package lectures.part1basics

object CBNvCBV extends App {


  // Value of function is evaluated before passed in
  def calledByValue(a: Long): Unit = {
    println(a)
    println(a)
  }

  def calledByName(a: => Long): Unit = {
    println(a)
    println(a)
  }


  calledByName(System.nanoTime())
  calledByValue(System.nanoTime())

  def inf(): Int = 1 + inf()
  def printFirst(x: Int, y: => Int): Unit = println(x)

  printFirst(34, inf())
  // CRASH doesnt happen, because call-by-name parameters are only evaluated upon necessity, and not otherwise

}
