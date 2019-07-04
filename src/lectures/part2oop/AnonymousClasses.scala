package lectures.part2oop

object AnonymousClasses extends App {


  abstract class Animal {

    val name: String

    def eat(): Unit
  }


  // Instantiating a real abstract class
  val funnyAnimal = new Animal {

    override val name: String = "Potato"

    override def eat(): Unit = println("ahahahhaaahaha")
  }



  class Person(val name: String) {
    def sayHi: Unit = println(s"Hi my name is $name, how can I help?")
  }


  // Anonymous extension of a non abstract class. Works for both abstract and non abstract
  // SAME GOES FOR TRAITS AS MUCH AS CLASSES!
  // Constructor arguments are required
  // Implement all abstract fields/methods

  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Hi my name is Jim, how can I help?")
  }

}
