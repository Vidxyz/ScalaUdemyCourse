package exercises

import lectures.part2oop.Generics.MyList

import scala.annotation.tailrec

// Functional definition of a linked list in Scala
/*
trait MyPredicate[-T] { // T => Boolean -> Can be made into a functional type
  def test(element: T): Boolean
}

trait MyTransformer[-A, B] { // A -> B -> Can be made into a functional type as well
  def transform(e: A): B
}

 */




abstract class MyNewList[+A] {

  // Singly linked list of integer
  // head = first element of list
  // tail = remainder of list
  // isEmpty() methods
  // add(int) => new list with this element added
  // toString() => ovveride string representation
  def head(): A
  def tail(): MyNewList[A]
  def isEmpty(): Boolean
  def add[B >: A](e: B): MyNewList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: A => B): MyNewList[B]

  def flatMap[B](transformer: A => MyNewList[B]): MyNewList[B]

  def filter(predicate: A => Boolean): MyNewList[A]

  // To suppress compiler
  def ++[B >: A](list: MyNewList[B]): MyNewList[B]


  /*
  HOFs

  1. Expand MyList
    - forEach method : Receives A => Unit and applies to every element
      [1,2,3].forEach(x => println(x))

    - sort function ((A, A) => Int) => MyList
      [1,2,3].sort((x,y) => y - x) => [3,2,1]

    - zipWith (list, (A, A) => B) => MyList[B]
      [1,2,3].zipWith([4,5,6], x * y) => [4, 10, 18]

    - fold(start) (function) => a value
      [1,2,3].fold(0)(x + y) = 6
 */

  def forEach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyNewList[A]
  def zipWith[B, C](list: MyNewList[B], zip: (A, B) => C): MyNewList[C]
  def fold[B](start: B)(operator: (B, A) => B): B

}


case object NewEmpty extends MyNewList[Nothing] {
  override def head(): Nothing = throw new NoSuchElementException

  override def tail(): MyNewList[Nothing] = throw new NoSuchElementException

  override def isEmpty(): Boolean = true

  override def add[B >: Nothing](e: B): MyNewList[B] = NewCons(e, NewEmpty)

  override def printElements: String = ""

  def map[B](transformer: Nothing => B): MyNewList[B] = NewEmpty

  def flatMap[B](transformer: Nothing => MyNewList[B]): MyNewList[B] = NewEmpty

  def filter(predicate: Nothing => Boolean): MyNewList[Nothing] = NewEmpty

  def ++[B >: Nothing](list: MyNewList[B]): MyNewList[B] = list

  override def forEach(f: Nothing => Unit): Unit = ()

  override def sort(compare: (Nothing, Nothing) => Int): MyNewList[Nothing] = NewEmpty

  override def zipWith[B, C](list: MyNewList[B], zip: (Nothing, B) => C): MyNewList[C] = {
    if(!list.isEmpty()) throw new RuntimeException("Lists do not have same length")
    else NewEmpty
  }

  override def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

//Covariance is included here

case class NewCons[+A](val h: A, val t: MyNewList[A]) extends MyNewList[A] {
  override def head(): A = h

  override def tail(): MyNewList[A] = t

  override def isEmpty(): Boolean = false

  override def add[B >: A](e: B): MyNewList[B] = NewCons(e, this)

  override def printElements: String = {
    if(t.isEmpty()) "" + h
    else h + " " + t.printElements
  }


  // Higher order functions - receive functions are parameters/returns them mas result
  def filter(predicate: A => Boolean): MyNewList[A] = {
    if(predicate(h)) NewCons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  def map[B](transformer: A => B): MyNewList[B] = {
    NewCons(transformer(h), t.map(transformer))
  }

  def flatMap[B](transformer: A => MyNewList[B]): MyNewList[B] = {
    transformer(h) ++ t.flatMap(transformer)
  }


  def ++[B >: A](list: MyNewList[B]): MyNewList[B] = NewCons[B](h, t ++ list)

  override def forEach(f: A => Unit): Unit = {
    f(h)
    t.forEach(f)
  }

  override def sort(compare: (A, A) => Int): MyNewList[A] = {

    // Not tail recursive
    def insert(x: A, sortedList: MyNewList[A]): MyNewList[A] = {
      if(sortedList.isEmpty()) new NewCons[A](x, NewEmpty)
      else if (compare(x, sortedList.head) <= 0) new NewCons[A](x, sortedList)
      else new NewCons(sortedList.head, insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)


//    //Tail recursive alternative
//    @tailrec
//    def insert_tail(x: A, sortedList: MyNewList[A], creationList: MyNewList[A]): MyNewList[A] = {
//      if(sortedList.isEmpty()) creationList
//      else if (compare(x, sortedList.head) <= 0) new NewCons[A](x, sortedList)
//      else new insert_tail()
//    }

    // val sortedTail = t.sort(compare)
    // insert_tail(h, sortedTail, Empty)

  }

  override def zipWith[B, C](list: MyNewList[B], zip: (A, B) => C): MyNewList[C] = {
    if(list.isEmpty()) throw new RuntimeException("Lists do not have same length")
    else new NewCons[C](zip(h, list.head), t.zipWith(list.tail, zip))
  }

  override def fold[B](start: B)(operator: (B, A) => B): B = {
    t.fold(operator(start, head))(operator)
  }
}

object NewListSimpleTest  {
  val list = NewCons(1, NewCons(2, NewCons(3, NewEmpty)))
  println(list.head())
  println(list.tail.head)
  println(list.add(4).head)
  println(list.isEmpty)

  println(list.toString)
  println(list.add(4).toString())

}

// Generecizing an API testing
object NewListTest extends  App {

  val listOfInt: MyNewList[Int] = NewCons(1, NewCons(2, NewCons(3, NewEmpty)))
  val anoterlistOfInt: MyNewList[Int] = NewCons(1, NewCons(2, NewCons(3, NewCons(4, NewEmpty))))
  val clonelistOfInt: MyNewList[Int] = NewCons(1, NewCons(2, NewCons(3, NewEmpty)))
  val alistOfInt: MyNewList[Int] = NewCons(4, NewCons(5, NewCons(6, NewEmpty)))
  val listOfStrings: MyNewList[String] = NewCons("Hello", NewCons[String]("Scala", NewEmpty))
  val anotherlistOfStrings: MyNewList[String] = NewCons("Hello", NewCons[String]("Scala",  NewCons[String]("What's",  NewCons[String]("UpDawg", NewEmpty))))

  println(listOfInt.toString)
  println(listOfStrings.toString)

  println(listOfInt.map(elem => elem * 2))
//  println(listOfInt.map(_ * 2))

  println(listOfInt.filter(e => e % 2 == 0))
//  println(listOfInt.filter(_% 2 == 0))

  println(listOfInt ++ alistOfInt)

  println(listOfInt.flatMap(e => NewCons(e, NewCons(e + 1, NewEmpty))))

  println(clonelistOfInt == listOfInt)

  // HOF TESTING


  listOfInt.forEach(x => println(x))

  listOfInt.sort((x, y) => y - x)
  listOfInt.sort((x, y) => -y + x)

  println(anoterlistOfInt.zipWith(anotherlistOfStrings, (x: Int, y: String) => x + "-" +  y))
//  listOfInt.zipWith(anotherlistOfStrings, _ + " " + _ )

  // Reudction operation here
  println(anoterlistOfInt.fold(0)((x, y) => x + y))

  val combinations = for {
    n <- listOfInt
    string <- listOfStrings
  } yield n + "-" + string

  println(combinations)

}
