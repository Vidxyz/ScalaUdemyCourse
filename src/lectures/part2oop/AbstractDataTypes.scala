package lectures.part2oop

object AbstractDataTypes extends App {

  // Abstract classes, members, methods
  abstract class Animal {
    val creatureType: String
    def eat(): Unit
  }


  class Dog extends Animal {
    override val creatureType: String = "Canine"
    // Override keyword can be dropped
    override def eat(): Unit = println("Crunch Crunch")
  }

  // traits
  // Kinda like abstract classes
  trait Carnivore {
    // Abstrct method
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fresh meat"
  }

  trait ColdBlooded {

  }

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "Croc"
    def eat(): Unit = println("JAWS")
    def eat(animal: Animal): Unit = println(s"I'm a croc, I'm eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile

  croc eat dog

  // Traits vs abstract classes
  // Abstract classes can have both abstract and non-abstract members, JUST LIKE TRAITS
  // 1 - Traits do not have constructor parameters
  // 2 - Can only extend one class, mix in multiple traits however
  // 3 - Traits are BEHAVIOURS that describe something. Abstract class is a type of "THING"

  // NOTHING in scala - is a subtype of EVERYTHING! - EVERYTHING!

}
