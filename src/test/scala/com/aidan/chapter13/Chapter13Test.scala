package com.aidan.chapter13

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Chapter13Test extends FunSuite {
  import com.aidan.chapter13.Chapter13._

  test("Indexes using mutable maps and sets") {
    val map = indexes("Mississippi")
    assert(map('M') === Set(0), "M")
    assert(map('i') === Set(1, 4, 7, 10), "i")
    assert(map('p') === Set(8, 9), "p")
  }

  test("Indexes using immutable maps and sets") {
    val map = indexesImmutable("Missouri")
    assert(map('M') === Set(0), "M")
    assert(map('i') === Set(1, 7), "i")
    assert(map('s') === Set(2, 3), "s")
    assert(map('o') === Set(4), "o")
    assert(map('u') === Set(5), "u")
    assert(map('r') === Set(6), "r")
  }
  
  test("Removing zeroes from a linked list") {
    val intList = List(1, 0, 4, 5, 0, -1, 7, 0)
    assert(removeZeros(intList) === List(1, 4, 5, -1, 7))
  }
  
  test("Arrays, maps and flatMap test") {
    val testArray = Array("Tom", "Fred", "Harry")
    val testMap = Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5)
    assert(findMapValues(testArray, testMap) === Array(3, 5))
  }
  
  test("Basic mkString") {
    val testArray = Array("The", "sands", "of", "time")
    assert(mkString(testArray, " ") === "The sands of time")
  }

}