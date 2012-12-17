package com.aidan.chapter13

object Chapter13 {
	def indexes(aString: String) = {
	  import scala.collection.mutable
	  val theMap = mutable.Map[Char, mutable.Set[Int]]()
	  for (c <- aString.zipWithIndex) theMap(c._1) = theMap.getOrElse(c._1, mutable.Set()) + c._2
	  theMap
	}
}

