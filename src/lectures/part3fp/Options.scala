package lectures.part3fp

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noneOption: Option[Int] = None

  println(myFirstOption)
  println(noneOption)


  def unsafeMethod(): String = null
//  val result = Some(unsafeMethod()) // WRONG, if unsafeMethod returns null
  val result = Option(unsafeMethod()) // Some or none, depending on return value of function
  println(result)

  // Chained methods, similar to elvis operator of Kotlin
  def backupMethod(): String = "Valid Result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // DESIGN unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("Valid Result")

  val betterChainedResult = betterUnsafeMethod orElse betterBackupMethod

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // Unsafe - throws NPE - DO NOT USE THIS (in general)

  println("*****")
  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.flatMap(x => Option(x  * 10)))


  // for comprehensions
  /*
  Exercise:
   */
  val config: Map[String, String] = Map (
    // Fetched from elsewhere, not certain that the values are correctly populated
    "host" -> "176.164.193.99",
    "port" -> "9090"
  )

  class Connection {
    def connect = "Connected" // Connect to some server
  }

  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] = {
      if (random.nextBoolean()) Some(new Connection)
      else None
    }
  }

  // Try to establish a connection, if so - print the connect method
  val host = config.get("host")
  val port = config.get("port")

  /*
  if (h != null)  {
    if (p != null) {
      connection.apply(h, p)

   return null
   */

  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))

  /*
  if c is not null
    return c.connect
  return null
   */
  val connectionStatus = connection.map(c => c.connect)
  // if connectionsattus is null, println (none) else print (some(connectionstatus.get)))
  println(connectionStatus)
  // if status is not equal to null,
  // println(status)
  connectionStatus.foreach(println)

  println("*** CHAINED SOLUTION ***")

  // Another way
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port)
        .map(connection => connection.connect)))
    .foreach(println)


  // For comprehensions, most readable way
  // Options are kinda like list with a max size of ONE AT MOST
  println("**** FOR COMPREHENSIONS")

  val connectionStatus2 = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect

  connectionStatus2.foreach(println)

}
