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
    val map = indexesImmutable("Mississippi")
    assert(map('M') === Set(0), "M")
    assert(map('i') === Set(1, 4, 7, 10), "i")
    assert(map('p') === Set(8, 9), "p")
  }

}