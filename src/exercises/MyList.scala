package exercises

// Functional definition of a linked list in Scala

abstract class MyList[+A] {

  // Singly linked list of integer
  // head = first element of list
  // tail = remainder of list
  // isEmpty() methods
  // add(int) => new list with this element added
  // toString() => ovveride string representation
  def head(): A
  def tail(): MyList[A]
  def isEmpty(): Boolean
  def add[B >: A](e: B): MyList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"

}


object Empty extends MyList[Nothing] {
  override def head(): Nothing = throw new NoSuchElementException

  override def tail(): MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty(): Boolean = true

  override def add[B >: Nothing](e: B): MyList[B] = new Cons(e, Empty)

  override def printElements: String = ""
}

//Covariance is included here

class Cons[+A](val h: A, val t: MyList[A]) extends MyList[A] {
  override def head(): A = h

  override def tail(): MyList[A] = t

  override def isEmpty(): Boolean = false

  override def add[B >: A](e: B): MyList[B] = new Cons(e, this)

  override def printElements: String = {
    if(t.isEmpty()) "" + h
    else h + " " + t.printElements
  }
}

object ListSimpleTest  {
  val list = new NewCons(1, new NewCons(2, new NewCons(3, NewEmpty)))
  println(list.head())
  println(list.tail.head)
  println(list.add(4).head)
  println(list.isEmpty)

  println(list.toString)
  println(list.add(4).toString())

}

// Generecizing an API testing
object ListTest extends  App {

  val listOfInt: MyNewList[Int] = new NewCons(1, new NewCons(2, new NewCons(3, NewEmpty)))
  val listOfStrings: MyNewList[String] = new NewCons("Hello", new NewCons[String]("Scala", NewEmpty))

  println(listOfInt.toString)
  println(listOfStrings.toString)

}
