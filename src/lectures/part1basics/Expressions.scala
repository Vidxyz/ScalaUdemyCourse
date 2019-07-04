package lectures.part1basics

// Makes runnable in intelliJ
object Expressions extends App {

  val x = 1 + 2 // Expression
  println(x)

  println(2 + 3 * 4)
  /// >>> right shift with zero extension


  println(1 == x)
  println(!(1 == x))

  // && || exist
  var variable = 3
  variable += 5       // Side effect
  println(variable)

  // Instructions (DO) vs Expressions (VALUE)
  // Instruction - tell computer to DO something -> Usually in imperative progs
  // Expressions - Compute a value

  // IF Expression
  val aCondition = true
  val conditionValue = if(aCondition) 5 else 3 // IF EXPRESSION NOT IF INSTRUCTION

  println(if(aCondition) 5 else 3) // NO compiler problems

  // Loops are discouraged from being used
  var i = 0

  val awhile = while(i<10) {
    println(i)
    i+=1
  }

  // NEVER WRITE THIS AGAIN
  // DONT WRITE IMPERATIVE CODE WITH SCALA SYNTAX
  // EVERYTHING IN SCALA IS AN EXPRESSION

  // INSTUCTIONS ARE EXECUTED, EXPRESSIONS ARE EVALUATED (SCALA)

  val weirdValue = (variable = 3)
  // Unit is a special type in scala - similar to void
  // Side effects result in returning Units
  println(weirdValue)

  // Side effects - println(), while loops, reassigning - reminiscent of imperative programming. Like instructions but expressions returning units


  // Code Blocks - also an expression
  val aCodeBlock = {

    val y = 2
    val z = y-1

    // This is the value of this expression
    if(z>2) "Hello" else 3.5f

  }

  println(aCodeBlock)


  // 1. DIFFERENCE BETWEEN  "Hello word" and println("Hello world")
  // Answer - value vs expression (side effect returning unit)



}
