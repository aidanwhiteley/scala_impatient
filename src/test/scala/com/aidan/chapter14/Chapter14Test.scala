package com.aidan.chapter14

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Chapter14Test extends FunSuite {
  import com.aidan.chapter14.Chapter14._

  test("Swap") {
    val aPair = (5, 8)
    assert(swap(aPair) === (8, 5))
  }
  
  test("Swap array values") {
    assert(swapArray(Array(1, 3, 4, 7)) === Array(3, 1, 4, 7), "Long array")
    assert(swapArray(Array(1, 3)) === Array(3, 1), "Two array")
    assert(swapArray(Array(1)) === Array(1), "One array")
    assert(swapArray(Array()) === Array(), "Zero array")
  }
  
  test("Prices of multiple") {
    // Test Articles and Bundles first
    val article1 = Article("art 1", 10.00)
    val article2 = Article("art 2", 15.50)
    val bundle1 = Bundle("bundle 1", 3.50, article1, article2)
    assert(price(article2) === 15.50, "article 1 price")
    assert(price(bundle1) === 22.00, "article 1 price")
    
    // Now test Multiples
    val multiples1 = Multiple(10, Article("Blackwell Toaster", 29.95))
    assert(price(multiples1) === 299.50, "multiple 1 price")
    
    val multiples2 = Multiple(5, bundle1)
    assert(price(multiples2) === 110.00, "multiple 2 price")
    
    val multiples3 = Multiple(2, multiples1)
    assert(price(multiples3) === 599.00, "multiple 3 price")
  }
  
  test("List Any") {
    val aList: List[Any] = List(List(3, 8), 2, List(5))
    assert(leafSum(aList) === 18)
  }
  
  test("Shallow binary tree") {
    val leaf1 = Leaf(4)
    val leaf2 = Leaf(5)
    val node = Node(leaf1, leaf2)
    
    assert(leafSum(node, 0) === 9)
  }
  
  test("Deeper binary tree") {
    val testData = NodeMultiple(NodeMultiple(Leaf(3), Leaf(8)), Leaf(2), NodeMultiple(Leaf(5)))
    assert(leafSum(testData, 0) === 18)
  }

}