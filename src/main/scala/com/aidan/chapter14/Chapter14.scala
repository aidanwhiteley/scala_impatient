package com.aidan.chapter14

object Chapter14 {
  def swap(aPair: Tuple2[Int, Int]) = {
    aPair match {
      case (x, y) => (y, x)
    }
  }
  
  def swapArray(anArray: Array[Int]) = {
    anArray match {
      case Array(x, y, rest @ _*) => Array(y, x) ++ rest
      case _ => anArray
    }
  }
}

