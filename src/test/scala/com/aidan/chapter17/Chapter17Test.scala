package com.aidan.chapter17

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Chapter17Test extends FunSuite {
  import com.aidan.chapter17.Chapter17._

  test("Immutable pair swap") {
    val swapped = (new Pair("Test", 1)).swap
	assert(swapped.first == 1)
	assert(swapped.second == "Test")
  }
  
  test("Mutable pair swap") {
    val swapped = (new MutablePair("Test", "Two")).swap()
    assert(swapped.first === "Two")
    assert(swapped.second === "Test")
  }


}