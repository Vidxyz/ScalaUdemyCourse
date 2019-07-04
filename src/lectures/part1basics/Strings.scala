package lectures.part1basics

object Strings extends  App {

  val str: String = "Hello, I am learning how to use Scala"

  println(str.charAt(2))

  println(str.substring(7,11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))

  println(str.replace(" ", "-"))
  println(str.toLowerCase())
  println(str.toUpperCase())

  println(str.length())



  // SCALA SPECIFIC UTITILIES
  val numberString: String = "45"
  val number = numberString.toInt

  // Prepending the operation and appending
  println('a' +: numberString :+ 'z')

  println(str.reverse)

  // Take x chards out of string
  println(str.take(2))


  // SCALA specific - String interpolators
  // S-interpolators
  val name = "David"
  val age = 12
  // Injected string
  val greeting = s"Hello, my name is $name and I am $age years old"
  val anothergreeting = s"Hello, my name is $name and I am ${age + 1} years old"

  println(greeting)
  println(anothergreeting)

  // F-interpolators
  val speed = 1.2f
  // 2.2 f means that 2 before decimal, 2 decimal places following the point
  val myth = f"$name%s can eat $speed%2.2f burgers per minute"

  println(myth)


  // raw interpolator
  // Similar to s-interpolator
  println(raw"This is a \n newline")
  // This gets escaped, only in the previous case the characters are not escaped
  val escaped = "This is a \n newline"
  println(raw"$escaped")



}
