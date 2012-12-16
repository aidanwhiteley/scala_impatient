package com.aidan.chapter12

object Chapter12 {
	
	def values(fun: (Int) => Int, low: Int, high: Int) = {
	  // Replaced following named fucntion with an anonymous function
	  // def createPairs(x: Int) = (x, fun(x))
	  (low to high).map(x => (x, fun(x))).toList
	}
}

