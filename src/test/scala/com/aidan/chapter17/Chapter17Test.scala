package com.aidan.chapter17

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Chapter17Test extends FunSuite {
  import com.aidan.chapter17.Chapter17._

  test("Immutable pair swap") {
    val swapped = new Pair("Test", 1).swap
	assert(swapped.first == 1)
	assert(swapped.second == "Test")
  }
  
  test("Mutable pair swap") {
    val swapped = new MutablePair("Test", "Two").swap()
    assert(swapped.first === "Two")
    assert(swapped.second === "Test")
  }

  test("Generic pair awap method") {
    import com.aidan.chapter17.Pair._
    
    val pair = swap(new Pair("A string", 42))
    assert(pair.first === 42)
    assert(pair.second === "A string")
  }
  
  test("Testing replaceFirst method and bounds") {
    val pair1 = new PairB[Student](new Student("Student 1"), new Student("Student 2"))
    val replacedPair1 = pair1.replaceFirst(new Student("Student 3"))
    assert(replacedPair1.first.name === "Student 3")
    
    val replacedPair2 = pair1.replaceFirstWithSuperType(new Person("Person 1"))
    assert(replacedPair2.first.name === "Person 1")
    
    val pair3 = new PairB[Person](new Person("Person 3"), new Person("Person 4"))
    val replacedPair3 = pair1.replaceFirst(new Student("Student 4"))
    // Lower bounds not required as a Student is a Person (allegedly)
    assert(replacedPair3.first.isInstanceOf[com.aidan.chapter17.Person])
  }
  
  /*
   * Not worried about defining "middle" for sequences with an even number of elements!
   */
  test("Middle element from Iterable[T]") {
    assert(middle("World") === 'r')
    val aList = List(1, 2, 3, 4, 5, 6, 7)
    assertResult(4) {
      middle(aList)
    }
  }

  // TODO - implement exercise 9
  
  // TODO - implement exercise 10
}