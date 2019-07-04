package lectures.part1basics

// Extending app gives the object a main method(?)

object ValuesVariablesTypes extends App{

  // Vals cannot be reassinged - IMMUTABLE
  // Types of vals are optional. Compiler can infer types still
  val x = 42
  println(x)

  // Scala way of thinking involves working with vals

  // Semi colons allowed, not necessary
  val aString: String = "Hello World"
  val another: String = "Another string"

  val aBoolean: Boolean = true || false
  val aChar: Char = 'a'

  val anInt: Int = x
  // 2bytes, not 4. INT is 4 bytes
  val aShort: Short = 4423
  // 8 bytes size - 64 bits
  val aLong: Long = 442233465445454545L
  val aFloat: Float = 2.005f
  val aDouble: Double = 3.14141765

  // Variables in scala (VAR vs VAL)
  var aVariable: Int = 4
  aVariable = 5 // Side effects
  aVariable += 4
  println(aVariable)
  // Programs without side effects are easier to understand
  // Mindful of side effects from now on


}
