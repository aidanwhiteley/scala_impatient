package com.aidan.chapter3

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Chapter3Test extends FunSuite {
  import com.aidan.chapter3.Chapter3._

  test("random array") {
    val a = randomArray(5)
    assert(a.length === 5)
    println(a.mkString("<<", ",", ">>"))
  }

  test("array swap") {
    val original = Array[Int](1, 2, 3, 4, 5)
    val expectedTransformed = Array[Int](2, 1, 4, 3, 5)
    assert(swapElements(original) === expectedTransformed)
  }

  test("array swap using yield") {
    val original = Array[Int](1, 2, 3, 4, 5)
    val expectedTransformed = Array[Int](2, 1, 4, 3, 5)
    assert(swapElementsWithYield(original) === expectedTransformed)
  }

  test("transform array - positives first") {
    val original = Array[Int](7, -3, 2, 0, -9, 8)
    val expectedTransformed = Array[Int](7, 2, 8, -3, 0, -9)
    assert(transformArray(original) === expectedTransformed)
  }

  test("compute average - doubles") {
    val doubles = Array(2.0, 4.0, 3.5, 2.6)
    assert(averageDoubles(doubles) === 3.025)
  }

  test("Reverse sort") {
    val theInts = Array(3, 1, 6, 4, 9)
    assert(reverseSort(theInts) === Array(9, 6, 4, 3, 1))
  }

  test("Reverse sort ArrayBuffer") {
    import scala.collection.mutable.ArrayBuffer
    val theInts = ArrayBuffer(3, 1, 6, 4, 9)
    assert(reverseSortAB(theInts) === ArrayBuffer(9, 6, 4, 3, 1))
  }
  
  test("Get available timezones") {
    assert(getAvailableTimeZones("America")(0) == "Adak")
  }

  test("Get available image favours") {
    import scala.collection.mutable.Buffer
    val flavors: Buffer[String] = getFlavors()
    // This test is potentially platform specific!!!
    assert(flavors.contains("PNG"))
  }
}