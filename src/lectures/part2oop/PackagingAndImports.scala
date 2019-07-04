package lectures.part2oop

import java.util.Date
import java.sql.{Date => SqlDate}

import exercises.OOPExercises.Writer

object PackagingAndImports extends App {

  // Package members accessible by name
  val writer = new Writer("John", "JVM", 2019)
  // Else needs importing

  // Packages are ordered in hierarchy
  // Matches folder structure in the file system


  // Package Object
  sayHello()
  println(SPEED_OF_LIGHT.toString)

//  import playground._ for importing everything
//  import playground.{Cinerella => Princess}
//  This imports and renames package in local scope

  // Option 1. Use fully qualified name
  // Option 2. Use aliasing
//  var date = new Date()
//  var sqldate = new SqlDate()

  // Default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???


}
