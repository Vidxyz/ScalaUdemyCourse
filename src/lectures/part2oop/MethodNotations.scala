package lectures.part2oop

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {

    def likes(movie: String): Boolean = {
      movie == favoriteMovie
    }

    def hangOutWith(p: Person): String = s"${this.name} is hanging out iwth ${p.name}"

    def unary_!(): String = s"$name, What the heck!?"

    def +(newName: String): Person = new Person(this.name, newName)

    def unary_+(): Person = new Person(this.name, this.favoriteMovie, this.age + 1)

    def learns(learn: String): String = s"${this.name} learns $learn"

    def learnsScala(): String = this learns "Scala"


    def apply(): String = s"Hi, my name is $name and favo movie is $favoriteMovie"

    def apply(num: Int): String = s"${this.name} watched ${this.favoriteMovie} $num times"

    def isAlive(): Boolean = true


  }

  val mary = new Person("Mary", "Inception")
  val tom = new Person("Tom", "Fight Club")

  println(mary.likes("Inception"))
  // These two are syntactically equivalent!!
  // Infix notation, ONLY WORKS FOR ONE PARAMETER METHODS
  // Can overload +/-/*/... to form valid methods
  println(mary likes "Inception")
  println(mary hangOutWith tom)

  // EQUIVALENT!
  println(1+2)
  println(1.+(2))




  // PREFIX NOTATION - all about unary operators
  val x = -1
  val y = 1.unary_-
  // unary_ prefix only works with -,+,~,!

  println(!mary)
  println(mary.unary_!)




  // USED WHEN METHODS ARE PARAMETER LESS
  // POSTFIX NOTATION -
  println(mary.isAlive)

  // apply - SYNTACTICALLY EQUIVALENT - apply method called when mary()
  println(mary.apply())
  println(mary())

  println((mary + "the rocktster")(4))
  println((+mary).age)

  println(mary.learnsScala)










}
