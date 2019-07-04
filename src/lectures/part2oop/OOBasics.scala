package lectures.part2oop

object OOBasics extends App {

  val person = new Person("Vid", 23)
  println(person.age)
  println(person.x)

  person.greet("Daniel")
  person.greet

}

// CONstructor
// class parameters are NOT FIELDS (members)
// Needs a val keyhword before it
// Default paramaeters are possible
class Person(name: String, val age: Int = 0)  {
  // Body. Defines implementation

  // THis is a member
  val x = 2

  // This is run, (side-effect) when class is instantiated
  println(1+3)
  // Can do anything here like in a block expression


  // Method
  def greet(name: String): Unit = {
    println(s"${this.name} says: Hi $name")
  }

  // This is method overloading - same name, different signatures
  def greet(): Unit = println(s"Hi, I am ${this.name}")

  // Multiple constructors
  // Aux constructor calls primary constructor
  // This isnt needed if default params exist in main constructor
//  def this(name: String) = this(name, 0)
  // This calls secondary constructor, which in turn calls base constructor
//  def this() = this("John Doe")



}
