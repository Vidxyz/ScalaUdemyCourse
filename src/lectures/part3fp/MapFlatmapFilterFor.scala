package lectures.part3fp

object MapFlatmapFilterFor extends App {

  val list = List(1,2,3)
  println(list)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(x => x + 1))
  println(list.map(_ + 1))
  println(list.map(x => x + " is a number"))
  println(list.map(_ + " is a number"))

  // filter : elements that evaluate to true
  println(list.filter(_ % 2 == 0))
  println(list.filter(x => x % 2 == 0))

  // Continue from flatmap
  val toPair = (x: Int) => List(x, x+1)
  println(list.flatMap(toPair))
  println(list.flatMap(x => List(x, x+1)))


  // print out all combinations between two lists
  val numbers = List(1,2,3,4)
  val chars = List('a','b','c','d')
  val colors = List("black", "white")

  // n - 1 flatmaps and 1 map at the end
  // Functional "iterations"
  val combinations = numbers.flatMap(n => chars.map(c => "" + c + n))
  println(combinations)
  val superCombinations = numbers.flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + color)))
  println(superCombinations)

  // Foreach
  list.foreach(println)

  // Shorthand
  // for-comprehens
  // Same as the map/flatmap expression above
  val forCombinations = for {
    n <- numbers
    c <- chars
    color <- colors
  } yield "" + c + n + '-' + color

  // Guards (filters) can be added. A filter happens at the chain level on the expression above
  val moreCombinations = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield "" + c + n + '-' + color

  println(forCombinations)

  for {
    n <- numbers
  }println(n)

  // Syntax overload
  list.map { x =>
    x * 2
  }

  /*
  1. MyList suppoers for comprehensions?
    map(f: A=>B) => MyList[B]
    filter (p: A => Boolean) => MyList[A]
    flatMap(f: A => MyList[B]) => MyList[B]
  2. A small collection of at most ONE element - Maybe[+T]
    - map flatMap filter
   */



}
