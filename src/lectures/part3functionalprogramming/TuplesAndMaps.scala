package lectures.part3functionalprogramming

object TuplesAndMaps extends App {

  val tuple = new Tuple2(2, "Hello, Scala") // Tuple2[Int, String] = (Int, String)
  val tuple2 = Tuple2(2, "Hello, Scala") // Tuple2[Int, String] = (Int, String)
  val tuple3 = (2, "Hello, Scala") // Tuple2[Int, String] = (Int, String)
  // At most 22 elements can be grouped together

  println(tuple._1)
  println(tuple._2)

  // Similar to case classes
  println(tuple.copy(_2 = "goodbye Java"))

  println(tuple.swap) // Swaps elements in place

  // Maps - keys -> values
  val map: Map[String, Int] = Map()
  // Notice the syntactic sugar
  val phonebook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1)
  println(phonebook)

  // map ops
  println(phonebook.contains("Jim"))
  // Returns value associated with key
  println(phonebook("Jim"))
  // This crashes, throws an exception, unlesss withDefaultValue is ad
  println(phonebook("Mary"))

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhoneBook = phonebook + newPairing
  println(newPhoneBook)

  // functionals
  // map, flatMap, filter
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2 * 2))

  // filterkeys & mapvalues - more often used
  // Only prints those keys that passes this predicate
  // These methods are derecated - they are now replaced with
  println(phonebook.filterKeys(x => x.startsWith("J")))
  // Mapvalues
  println(phonebook.mapValues(number => number * 10))

  // Check this out
  println(phonebook.toList)
  println(List(("Daniel", 444)).toMap)

  val names = List("Bob", "James", "Angela", "Andy", "Jim", "Daniel")
  // Literally groups by matching same char at charAt(0)
  // Looks pretty powerful this
  println(names.groupBy(name => name.charAt(0)))
}
