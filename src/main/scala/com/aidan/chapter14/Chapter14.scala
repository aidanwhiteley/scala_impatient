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
  
  def price(it: Item) : Double = it match {
    case Article(_, p) => p
    case Bundle(_, disc, its @ _*) => its.map(price _).sum - disc
    case Multiple(n, item) => n * price(item)
  }
}

sealed abstract class Item
case class Article(descriptiopn: String, price: Double) extends Item
case class Bundle(description: String, discount: Double, items: Item*) extends Item
case class Multiple(howMany: Int, item: Item) extends Item



