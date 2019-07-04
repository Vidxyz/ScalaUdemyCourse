package lectures.part2oop

object Generics extends App {

  class MyList[+A] {

    // use the type A inside class def

    // Careful about type system and how things are organized
    // Designing generic frameworks
    def add[B >: A](element: B): MyList[B] = ???
    /*
      def head(): Int
  def tail(): MyList
  def isEmpty(): Boolean
  def add(e: Int): MyList
  def printElements: String
  override def toString: String = "[" + printElements + "]"

     */


  }

  // Traits can also be type parameterized

  class MyMap[Key, Value] {

  }


  val listOfInt = new MyList[Int]
  val listOfString = new MyList[String]

  // Generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfInt = MyList.empty[Int]

  // Variance problem
  class Animal

  class Cat extends Animal

  class Dog extends  Animal

  //1. yes, List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]

  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  //2. animalList.add(new Dog)   -> Would this work??? HARD QUESTION -> WE RETURN A LIST OF ANIMALS
  // List of cat and list of animals are tow sepearte things

  // 2. Every type is its own
  class InvariantList[A]
  // This leads to an error
//  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]


  // 3. HELL NO, CONTRAVARIANCE

  class Trainer[-A]
  // Notice the type relations
  val trainerList: Trainer[Cat] = new Trainer[Animal]





  // BOUNDED TYPES
  // subtypes of animal
  // UPPER BOUNDED TYPE, lower bounded is flipped sign
  class Cage[A <: Animal](val animal: A)

  val cage = new Cage(new Dog)

  class Car
//  val newCage = new Cage(new Car)  // --> This will lead to an error!






}
