package com.aidan.chapter14

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Chapter14Test extends FunSuite {
  import com.aidan.chapter14.Chapter14._

  test("Swap") {
    val aPair = (5, 8)
    assert(swap(aPair) === (8, 5))
  }
  
  test("Swap array values") {
    assert(swapArray(Array(1, 3, 4, 7)) === Array(3, 1, 4, 7), "Long array")
    assert(swapArray(Array(1, 3)) === Array(3, 1), "Two array")
    assert(swapArray(Array(1)) === Array(1), "One array")
    assert(swapArray(Array()) === Array(), "Zero array")
  }

}