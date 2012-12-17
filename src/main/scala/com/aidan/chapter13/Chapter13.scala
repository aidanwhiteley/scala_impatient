package com.aidan.chapter13

object Chapter13 {
	def indexes(aString: String) = {
	  import scala.collection.mutable
	  val map = mutable.Map[Char, mutable.Set[Int]]()
	  for (c <- aString.zipWithIndex) map(c._1) = map.getOrElse(c._1, mutable.Set(c._2)) + c._2
	  map
	}
}

