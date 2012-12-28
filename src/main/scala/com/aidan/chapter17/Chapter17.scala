package com.aidan.chapter17

object Chapter17 {
  
  def middle[T](iter: Iterable[T]) = {
    val midPoint = (iter.size / 2) + 1
    val result = iter.take(midPoint).last
    result
  }

}

class Pair[T, S](val first: T, val second: S) {
  def swap: Pair[S, T] = new Pair(second, first)
}

object Pair {
  def swap[T, S](aPair: Pair[T, S]): Pair[S, T] = {
    val pair = new Pair(aPair.second, aPair.first)
    pair
  }
}

class MutablePair[T](var first: T, var second: T) {
  def swap() = {
    val temp = this.first
    this.first = this.second
    this.second = temp
    this
  }
  //def replaceFirst[R >: T](newFirst: R) {
  //  first = newFirst
  //}
}

class Person(val name: String = "Mr Person")  
class Student(override val name: String = "Mr Student") extends Person

class PairB[T](val first: T, second: T) {
  def replaceFirst(newFirst: T) = new PairB[T](newFirst, second)
  def replaceFirstWithSuperType[R >: T](newFirst: R) = new PairB[R](newFirst, second)
}


