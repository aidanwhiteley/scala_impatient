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
  
  def leafSum(aList: List[Any]) : Int = {
    (for (elem <- aList) yield {
      elem match {
        case x: Int => x
        case y: List[_] => leafSum(y)
        case _ => 0
      }
    }).sum
  }
  
  def leafSum(tree: BinaryTree, accum: Int) : Int = {
    tree match {
      case leaf: Leaf => accum + leaf.value
      case node: Node => leafSum(node.left, accum) + leafSum(node.right, accum)
      case nodeMultiple: NodeMultiple => (for (elem <- nodeMultiple.child) yield leafSum(elem, accum)).sum
    }
  }
}

sealed abstract class Item
case class Article(descriptiopn: String, price: Double) extends Item
case class Bundle(description: String, discount: Double, items: Item*) extends Item
case class Multiple(howMany: Int, item: Item) extends Item

sealed abstract class BinaryTree
case class Leaf(value: Int) extends BinaryTree
case class Node(left: BinaryTree, right: BinaryTree) extends BinaryTree
case class NodeMultiple(child: BinaryTree*) extends BinaryTree



