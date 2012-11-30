package com.aidan.chapter2

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Chapter2Test extends FunSuite {
  import com.aidan.chapter2.Chapter2._

  test("signum") {
    assert(signum(23) === 1)
    assert(signum(-3) === -1)
    assert(signum(0) === 0)
  }

  test("empty block") {
    assert(emptyBlock === ())
  }

  test("assigment is unit") {
    assert(assignIsUnit === ())
  }

  test("print loop descending") {
    assert(printLoopDescending() === ())
  }

  test("countdown") {
    assert(countdown(7) === ())
  }

  test("hello as unicode") {
    assert(asUnicode("Hello") === 9415087488L)
  }

  test("hello as unicode without loop") {
    assert(asUnicodeWithoutLoop("Hello") === 9415087488L)
  }

  test("product") {
    assert(product("Hello") === 9415087488L)
  }
  
  test("recursive product") {
    assert(productR("Hello") === 9415087488L)
  }
}