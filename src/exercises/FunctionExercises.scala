package exercises

object FunctionExercises  extends  App{


  def factorial(n: Int): Int = {
    if(n <= 0) 1
    else n * factorial(n-1)
  }

  println(factorial(5))




  def fibonacci(n: Int): Int = {
    if(n <= 2) 1
    else fibonacci(n-1) + fibonacci(n-2)
  }

  println(fibonacci(2))



  def isPrime(n: Int): Boolean = {

    def isPrimeUntil(current: Int): Boolean = {
      if(current <= 1) true
      else n % current != 0 && isPrimeUntil(current-1)
    }

    isPrimeUntil(n/2)

  }



  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(37*13))

}
