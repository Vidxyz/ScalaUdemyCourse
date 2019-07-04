package playground

object ScalaPlayground extends App {

  println("Hello Scala")


  object Person {
    val N_EYES = 2
    def canFly: Boolean = false

    // Factory method
    def from(mom: Person, dad: Person): Person = new Person("Bobbie")

    def apply(mom: Person, dad: Person): Person = new Person( "Bobbie")
  }

  // Pattern used in practice
  class Person(val name: String) {
    // Seperate instance level functionality from "static" scope functionality
  }
  // COMPANION PATTERN

  println(Person.N_EYES)
  println(Person.canFly)

  // SCALA OBJECTS ARE A SINGLETON INSTANCE
  val m = Person
  val n = Person
  println(m == n) // true

  val mary = new Person("Mary")
  val john = new Person("John")
  println(mary == john) // false

  val bobbie = Person.from(mary, john)
  // looks like consructor but is factory method
  val bob = Person(mary, john)



  // Scala applications
  // Scala object with
  // def main(args: Array[String]): Unit = {// Code goes here }
  // Need ^ this function signature if you dont want to extend App as part of the top level object




}
