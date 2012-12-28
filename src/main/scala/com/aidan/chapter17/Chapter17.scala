package com.aidan.chapter17

object Chapter17 {
  
}

class Pair[T, S](val first: T, val second: S) {
  def swap : Pair[S, T] = new Pair(second, first)
}

class MutablePair[T](var first: T, var second: T) {
  def swap() = {
    val temp = this.first
    this.first = this.second
    this.second = temp
    this
  }
}



