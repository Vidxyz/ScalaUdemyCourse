package lectures.part3functionalprogramming

import scala.annotation.tailrec

object TuplesAndMaps extends App {

  val tuple = new Tuple2(2, "Hello, Scala") // Tuple2[Int, String] = (Int, String)
  val tuple2 = Tuple2(2, "Hello, Scala") // Tuple2[Int, String] = (Int, String)
  val tuple3 = (2, "Hello, Scala") // Tuple2[Int, String] = (Int, String)
  // At most 22 elements can be grouped together

  println(tuple._1)
  println(tuple._2)

  // Similar to case classes
  println(tuple.copy(_2 = "goodbye Java"))

  println(tuple.swap) // Swaps elements in place

  // Maps - keys -> values
  val map: Map[String, Int] = Map()
  // Notice the syntactic sugar
  val phonebook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1)
  println(phonebook)

  // map ops
  println(phonebook.contains("Jim"))
  // Returns value associated with key
  println(phonebook("Jim"))
  // This crashes, throws an exception, unlesss withDefaultValue is ad
  println(phonebook("Mary"))

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhoneBook = phonebook + newPairing
  println(newPhoneBook)

  // functionals
  // map, flatMap, filter
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2 * 2))

  // filterkeys & mapvalues - more often used
  // Only prints those keys that passes this predicate
  // These methods are derecated - they are now replaced with
  println(phonebook.filterKeys(x => x.startsWith("J")))
  // Mapvalues
  println(phonebook.mapValues(number => number * 10))

  // Check this out
  println(phonebook.toList)
  println(List(("Daniel", 444)).toMap)

  val names = List("Bob", "James", "Angela", "Andy", "Jim", "Daniel")
  // Literally groups by matching same char at charAt(0)
  // Looks pretty powerful this
  println(names.groupBy(name => name.charAt(0)))
  // When mapping keys, ensure keys remain unique
  // Else data can be lost by possible overwrtiting with latest key created from mappping
  // Example "Jim".toLowerCase -> 567 and "JIM".toLowerCase -> 90900

  /*
  Overly simplified social network bnased on maps
  Person = String
  - add person to netowkr
  - remove
  - fiend
  - unfriend

  - number of friends of person
  - person with most friends
  - how many people NO friends
  - if there is a social connection between two people (direct or not)

   */

  def addUser(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)
    // Being replaced - a and b keys values are reconstructed and replaced
    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String) = {
    val friendsA = network(a)
    val friendsB = network(b)
    // Being replaced - a and b keys values are reconstructed and replaced. Similar to what the friend function does right now
    // Replace old pairing with new pairing
    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  // Need to remove all friend relationships first before deleting to avoid dangling references
  def removeUser(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {

    @tailrec
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] = {
      if(friends.isEmpty) networkAcc
      // else unfriend people
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))
    }

    val unfriendedMap: Map[String, Set[String]] = removeAux(network(person), network)
    unfriendedMap - person // this gets returned


  }


  // Tests
  val empty: Map[String, Set[String]] = Map()
  var network = addUser(addUser(empty, "Bob"), "Mary")
  println(network)
  network = friend(network, "Bob", "Mary")
  println(network)
  network = unfriend(network, "Mary", "Bob")
  println(network)
  network = friend(network, "Bob", "Mary")
  println(network)
  network = removeUser(network, "Bob")
  println(network)


  // Jim, Bob, Mary
  val people = addUser(addUser(addUser(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")

  println(testNet)

  def numFriends(network: Map[String, Set[String]], person: String): Int = {
    if(!network.contains(person)) 0
    else network(person).size
  }

  println(numFriends(testNet, "Bob"))

  // Note this carefully. maxBy returns the pair for which the condition is MAX satisfied
  def mostFriends(network: Map[String, Set[String]]): String = {
    network.maxBy(pair => pair._2.size)._1
  }

  def numberOfNoFriendsPeople(network: Map[String, Set[String]]): Int = {
    network.filterKeys(k => network(k).isEmpty).size
  }

  // Direct filtering on the second element of the pair, instead of using filterkeys
  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
//    network.filter(pair => pair._2.isEmpty).size
    network.count(pair => pair._2.isEmpty) // filter and size can be replaced by count - IDE insight
    // can also write _._2.isEmpty (sugar)
  }

  println(mostFriends(testNet))
  println(numberOfNoFriendsPeople(testNet))
  println(numberOfNoFriendsPeople(people))
  println(nPeopleWithNoFriends(people))

  // Running a Breadth First Search in our graph
  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {

    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if(consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }

    bfs(b, Set(), network(a) + a)

  }

  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(network, "Mary", "Bob"))

}


