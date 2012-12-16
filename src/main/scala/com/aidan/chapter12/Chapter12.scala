package com.aidan.chapter12

object Chapter12 {
	
	def values(fun: (Int) => Int, low: Int, high: Int) = {
	  // Replaced following named fucntion with an anonymous function
	  // def createPairs(x: Int) = (x, fun(x))
	  (low to high).map(x => (x, fun(x))).toList
	}
	
	def getLargestElementUsingReduceLeft(arr: Array[Int]): Int = {
	  arr.reduceLeft(_ max _)
	}
	
	def factorial(num: Int) : Long = {
	  (num to 1 by - 1).reduceLeft(_ * _)
	}
}

