package com.aidan.chapter2

object Chapter2 {

  def signum(aNumber: Int): Int = {
    if (aNumber > 0) 1
    else if (aNumber == 0) 0
    else -1
  }

  def emptyBlock {

  }

  def assignIsUnit {
    var y: Int = 0
    val x: Unit = y = 1
  }

  def printLoopDescending() {
    for (i <- 10 to 0 by -1) print(i + " ")
    println("")
  }

  def countdown(n: Int) {
    for (i <- n to 0 by -1) print(i + " ")
    println("")
  }

  def asUnicode(in: String): Long = {
    var sum = 1L
    for (ch <- in) sum *= ch
    sum
  }

  def asUnicodeWithoutLoop(in: String): Long = {
    in.foldLeft(1L)(_ * _)
  }

  def product(s: String) = asUnicode(s)

  def productR(s: String): Long = {
    if (null == s || s.isEmpty()) 1L
    else {
      s.take(1).charAt(0) * productR(s.drop(1))
    }
  }
}