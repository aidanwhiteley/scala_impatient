package com.aidan.chapter4

import scala.collection.immutable.HashMap
import scala.collection.immutable.TreeMap
import scala.collection.mutable.LinkedHashMap

object Chapter4 {

  def applyDiscount(aMap: Map[String, Double]) = {
    for ((k, v) <- aMap) yield (k, v * 0.9)
  }

  def mapFileToWords(inFile: java.util.Scanner): scala.collection.mutable.HashMap[String, Int] = {
    var wordMap = new scala.collection.mutable.HashMap[String, Int]
    while (inFile.hasNext()) {
      val aWord = inFile.next()
      val count = wordMap.getOrElse(aWord, 0)
      wordMap(aWord) = count + 1
    }
    wordMap
  }

  def mapFileToWordsImmutable(inFile: java.util.Scanner): Map[String, Int] = {
    var wordMap = new HashMap[String, Int]
    while (inFile.hasNext()) {
      val aWord = inFile.next()
      val count = wordMap.getOrElse(aWord, 0)
      wordMap = wordMap + (aWord -> (count + 1))
    }
    wordMap
  }

  def mapFileToWordsSorted(inFile: java.util.Scanner): TreeMap[String, Int] = {
    var wordMap = new TreeMap[String, Int]
    while (inFile.hasNext()) {
      val aWord = inFile.next()
      val count = wordMap.getOrElse(aWord, 0)
      wordMap = wordMap + (aWord -> (count + 1))
    }
    wordMap
  }

  def mapFileToWordsSortedJava(inFile: java.util.Scanner): scala.collection.mutable.Map[String, Int] = {
    var wordMap = new java.util.TreeMap[String, Int]
    while (inFile.hasNext()) {
      val aWord = inFile.next()
      var count = wordMap.get(aWord)	// Null handling???
      wordMap.put(aWord , count + 1)
    }
    
    import collection.JavaConversions.mapAsScalaMap
    val returnMap: scala.collection.mutable.Map[String, Int] = wordMap
    returnMap
  }
  
  def getDaysOfWeek() : LinkedHashMap[String, Int] = {
    val days = scala.collection.mutable.LinkedHashMap(
      "Monday" -> java.util.Calendar.MONDAY,
      "Tuesday" -> java.util.Calendar.TUESDAY,
      "Wednesday" -> java.util.Calendar.WEDNESDAY,
      "Thursday" -> java.util.Calendar.THURSDAY,
      "Friday" -> java.util.Calendar.FRIDAY,
      "Saturday" -> java.util.Calendar.SATURDAY,
      "Sunday" -> java.util.Calendar.SUNDAY
    )
    days
  }
  
  def getSysProperties() : scala.collection.Map[String, String] = {
    import collection.JavaConversions.propertiesAsScalaMap 
    val props: scala.collection.Map[String, String] = System.getProperties()
    props
  }
  
  def getMinMax(theArray : Array[Int]) = {
    (theArray.min, theArray.max)
  }
  
  def lteqgt(values: Array[Int], v: Int) = {
    (values.count(_ < v), values.count(_ == v), values.count(_ > v))
  }
}