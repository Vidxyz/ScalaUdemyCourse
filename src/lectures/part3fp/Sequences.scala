package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  // Seq
  val aSequence = Seq(1,2,3,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2)) // apply method retrieves value
  println(aSequence ++ Seq(7,5,6))
  // works if type is by default ordered
  println(Seq(7,5,3,8).sorted)

  // Ranges
  val anInclusiveRange: Seq[Int] = 1 to 10
  val anExclusiveRange: Seq[Int] = 1 until 10
  anInclusiveRange.foreach(println)
  anExclusiveRange.foreach(println)

  anInclusiveRange.foreach(x => println("Hello"))

  // List
  val list = List(1,2,3)
  val prepended = 43 :: list
  val preponded = 42 +: list :+ 89
  println(prepended)
  println(preponded)

  // Curried function
  val apples5 = List.fill(5)("apple")
  println(apples5)

  // Concat + insert parameter
  println(list.mkString("-"))

  // Arrays
  val numbers = Array(1,2,3,4)
  val threElements = Array.ofDim[Int](3)
  println(threElements)
  // primitives default to 0 (false), others are null
  println(threElements.foreach(println))

  // mutation
  numbers(2) = 0
  // syntax sugar for numbers.update(2,0)
  // update is a special method in scala like apply
  println(numbers.mkString(" "))

  // This is possible! Implicity conversions!!
  val numbersSeq: Seq[Int] = numbers
  println(numbersSeq)

  // Vectors
   val vector: Vector[Int] = Vector(1,2,3)
  println(vector)
  // Same func. as other collections too

  // vectors vs list, a time comparison of which one is faster
  val maxRuns = 1000
  val maxCapacity = 100000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    }yield {
      val currentTime = System.nanoTime()
      // operation
      collection.updated(r.nextInt(maxCapacity), 0)
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // Adv: Keep reference to tail
  // Disadv: Updating an element in the middle takes long
  println(getWriteTime(numbersList))
  // Adv: Depth of the tree is small
  // Disadv: Needs to replace an entire 32- element chunk
  println(getWriteTime(numbersVector))

}
