package lectures.part2oop

object CaseClasses extends App {

    /*
      equals, hashCode, toString - need to be redefined for all classes.
      Case classes already have them implemented
      1. class parameters are field
      2. sensible toString for debugging
      3. equals and hashCode are implemented out of the box
         - Makes case classes good to use in collection
      4. Case classes have handy copy methods
      5. Case classes have companion objects
      6. Case classes are serializable. Useful in distro systems. Crucial for AKKA
      7. Case classes have extractor patterns = CCs can be used in Pattern Matching
      8. Case objects dont have companion objects

     */

  case class Person(name: String, age: Int)

  // 1. class parameters are field
  val jim = new Person("Jim", 34)
  println(jim.name)

  // 2. sensible toString for debugging
  // println(jim) -> Redirects to toString method

  println(jim.toString)
  println(jim) // Syntactic Sugar

  // 3. equals and hashCode are implemented out of the box
  // Makes case classes good to use in collection
  val jim2 = new Person("Jim", 34)
  println(jim == jim2)  // Returns true

  // 4. Case classes have handy copy methods
  val jim3 = jim.copy(age = 45) // Copies all parameters except for named parameter arguments

  println(jim3)

  // 5. Case classes have companion objects
  val thePerson = Person //valid
  val mary = Person("Mary", 23) // Using implicit apply method. New keyword not needed

  // 6. Case classes are serializable. Useful in distro systems. Crucial for AKKA
  // 7. Case classes have extractor patterns = CCs can be used in Pattern Matching
  // 8. Case objects dont have companion objects

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  /*
  Expand Mylist, use case classes and case objects
  */


}
