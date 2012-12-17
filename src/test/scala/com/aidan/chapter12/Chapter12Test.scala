package com.aidan.chapter12

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Chapter12Test extends FunSuite {
  import com.aidan.chapter12.Chapter12._

  test("Function values") {
    assert(values(x => x * x, 2, 3) === Seq((2, 4), (3, 9)), "2 to 3")
  }

  test("Get largest value in array using reduceLeft") {
    val testArray = Array(4, 7, 22, 1, -4, 8, 21)
    assert(getLargestElementUsingReduceLeft(testArray) === 22, "Reduce left")
  }

  test("Factorial using reduceLeft and to") {
    assert(factorial(5) === 120, "5 factorial")
    assert(factorial(10) === 3628800, "10 factorial")
    assert(factorial(0) === 1, "0 factorial")
  }

  test("Factorial using foldLeft") {
    assert(factorialTake2(5) === 120, "5 factorial")
    assert(factorialTake2(10) === 3628800, "10 factorial")
    assert(factorialTake2(0) === 1, "0 factorial")
  }

  test("Largest function") {
    def testFunction(x: Int) = 10 * x - x * x
    assert(largest(testFunction, 1 to 10) === 25)
  }

  test("Largest function - return input") {
    def testFunction(x: Int) = 10 * x - x * x
    assert(largestReturnInput(testFunction, 1 to 10) === 5)
  }

  test("Adjust to pair") {
    assert(adjustToPair(_ * _)((6, 7)) === 42, "Adjust to pair test")

    val pairs = (1 to 3) zip (11 to 13)
    assert(pairs.map(adjustToPair(_ + _)).sum === 42, "Map sum")
  }
  
  test("Corresponds String array to Int array") {
    val theStrings = Array("The", "sands", "of", "time")
    val theLengths = Array(3, 5, 2, 4)
    assert(correspondsWithCurrying(theStrings, theLengths), "array corresponds" )
  }
  
  test("Corresponds without currying") {
    val theStrings = Array("were", "eroded", "by")
    val theLengths = Array(4, 6, 2)
    val fun = (a: String, b: Int) =>  a.length == b
    
    assert(correspondsNoCurrying(theStrings, theLengths, fun) === true, "array corresponds" )
  }
  
  test("An unless control structure") {
    var test = false
    unless (4 < 3) {
      test = true
    } 
    assert(test === true, "Unless test")
  }
}