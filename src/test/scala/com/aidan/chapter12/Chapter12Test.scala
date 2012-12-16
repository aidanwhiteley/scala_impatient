package com.aidan.chapter12

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Chapter12Test extends FunSuite {
  import com.aidan.chapter12.Chapter12._

  test("Function values") {
    assert(values(x => x* x, 2, 3) === Seq( (2, 4), (3, 9)), "2 to 3")
  }

  test("Get largest value in array using reduceLeft") {
    val testArray = Array(4, 7, 22, 1, -4, 8, 21)
    assert(getLargestElementUsingReduceLeft(testArray) === 22, "Reduce left")
  }
}