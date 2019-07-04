package exercises

object OOPExercises extends App {

  class Writer(val firstName: String, val surname: String, val year:Int ) {

    def fullname(): String = this.firstName + " " + this.surname

  }


  class Novel(val name: String, val releaseYear: Int, val author: Writer) {

    def authorAge(): Int = this.releaseYear - author.year

    def isWritterBy(author: Writer): Boolean = {
      author == this.author
    }

    def copy(newYear: Int): Novel = {
      new Novel(this.name, newYear, this.author)
    }

  }

  val author = new Writer("Charles", "Dickens", 1812)
  val imposter = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great expectations", 1861, author)

  println(novel.authorAge())
  println(novel.isWritterBy(author))
  println(novel.isWritterBy(imposter))

  // -------------------

  // Counter class
  // Receives an int value
  // Method to increment/decrement
  // method current count
  // Overload increment/decrement to receive an amount


  class Counter(val count: Int = 0) {

    def inc = {
      println("Incrementing")
      new Counter(count + 1) //Immutability - instances CANNOT be modified. Return new instance always
    }

    def dec = {
      println("Decrementing")
      new Counter(count - 1)
    }

    def inc(n: Int): Counter = {
      if(n <= 0) this
      else inc.inc(n-1)
    }

    def dec(n: Int): Counter = {
      if(n <= 0) this
      else dec.dec(n-1)
    }

    def print = println(count)

  }

  val c = new Counter(4)

  c.inc(8).print

  // Takeways- Classes defining and implementing
  // Val/var needed in constructtor to make values
  // Methods w/o parameters can be called without paranthesis just like a member



}
