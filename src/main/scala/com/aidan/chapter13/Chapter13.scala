package com.aidan.chapter13

object Chapter13 {
  def indexes(aString: String) = {
    import scala.collection.mutable
    val theMap = mutable.Map[Char, mutable.Set[Int]]()
    for (c <- aString.zipWithIndex) theMap(c._1) = theMap.getOrElse(c._1, mutable.Set()) + c._2
    theMap
  }

  def indexesImmutable(aString: String) = {
    // Maybe come back to this one and find a way to implement without the var
    var theMap = Map[Char, Set[Int]]()
    for (c <- aString.zipWithIndex) theMap += ( (c._1, theMap.getOrElse(c._1, Set()) + c._2) )
    theMap
  }
  
  def removeZeros(aList: List[Int]) = {
    aList.filterNot(a => a == 0)
  }
  
  def findMapValues(anArray : Array[String], aMap: Map[String, Int]) = {
    val results = (for (elem <- anArray) yield aMap.get(elem))
    results.flatten
  }
  
  def mkString(input : Array[String], separator: String) = {
    input.reduceLeft((a , b) => a + separator + b)
  }
  
  // Exercise 6 not implemented. Looks like line noise rather than Scala to me. 
  // Might read better with foldLeft / foldRight
  
  def multiplyTuple = ((price: Double, quantity: Int) => price * quantity).tupled
  
  def arrayGroup(input: Array[Int], columns: Int) = {
    input.grouped(columns).toArray
  }
}

