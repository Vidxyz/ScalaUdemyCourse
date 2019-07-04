package lectures.part2oop

object Inheritence extends App {

  // Single level inheritence
  sealed class Animal {
    val creatureType = "Wild"
     def eat(): Unit = println("nomnomnom")
  }

  class Cat extends  Animal {
    def crunch() = {
      eat
      println("Crunch crunch")
    }
  }


  // Constructors
  class Person(name: String, age: Int) {

    // overload
    def this(name: String) = this(name, 0)

  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)


  class Dog(override val creatureType: String) extends  Animal {
//    override val creatureType: String = "Domestic"
    override def eat(): Unit = {
      super.eat()
      println("Munch, Munch")
    }
  }


  val cat = new Cat
  cat.crunch()

  // OVerriding inherited member through constructor
  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // TYPE substituition - POLYMORPHISM
  val unknown: Animal = new Dog("K9")
  unknown.eat


  // Super


  // Preventing overrides
  // 1 - final keyword on member
  // 2 - Final on class itself - prevents class from being extended
  // 3 - seal the class = extend classes in THIS FILE, prevent extension in other files: Use the 'sealed' keyword


}
