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


}