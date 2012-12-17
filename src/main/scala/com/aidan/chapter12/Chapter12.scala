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
	  // Not sure that factorials for negative integers exist but fact(0) = 1 according to my calculator
	  if (num < 1) 1
	  else (num to 1 by - 1).reduceLeft(_ * _)
	}
	
	def factorialTake2(num: Int) : Long = {
	  (num to 1 by - 1).foldLeft(1)(_ * _)
	}
	
	def largest(fun: (Int) => Int, inputs: Seq[Int]) = {
	  (for (input <- inputs) yield fun(input)).reduceLeft(_ max _)
	}
	
	def largestReturnInput(fun: (Int) => Int, inputs: Seq[Int]) = {
	  (for (input <- inputs) yield (input, fun(input))).reduceLeft((left, right) => if (left._2 > right._2) left else right)._1
	}
}

