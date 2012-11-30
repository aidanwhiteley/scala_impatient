package com.aidan.chapter3

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Buffer

object Chapter3 {

  def randomArray(n: Int) = {
    import scala.util.Random
    val a = new Array[Int](n)
    for (i <- 0 until n) a(i) = new Random().nextInt(n)
    a
  }

  def swapElements(anArray: Array[Int]): Array[Int] = {
    val outputArray = new Array[Int](anArray.length)
    for (i <- 0 until anArray.length) {
      if (i + 1 < anArray.length && i % 2 == 0) {
        outputArray(i) = anArray(i + 1)
        outputArray(i + 1) = anArray(i)
      } else if (i == anArray.length - 1) {
        outputArray(i) = anArray(i)
      }
    }
    outputArray
  }

  def swapElementsWithYield(anArray: Array[Int]): Array[Int] = {
    val result = for (i <- 0 until anArray.length) yield {
      if (i + 1 < anArray.length && i % 2 == 0) {
        anArray(i + 1)
      } else if (i % 2 != 0) {
        anArray(i - 1)
      } else {
        anArray(i)
      }
    }
    result.toArray
  }

  def transformArray(anArray: Array[Int]): Array[Int] = {
    val positives = anArray.filter(_ > 0)
    val negatives = anArray.filter(_ <= 0)

    positives ++ negatives
  }

  def averageDoubles(theDoubles: Array[Double]) = {
    theDoubles.sum / theDoubles.length
  }

  def reverseSort(theInts: Array[Int]) = {
    scala.util.Sorting.quickSort(theInts)
    theInts.reverse
  }

  def reverseSortAB(theInts: ArrayBuffer[Int]) = {
    theInts.sortWith(_ < _).reverse
  }

  def getAvailableTimeZones(filter: String): Array[String] = {
    import util.Sorting._

    val ids = java.util.TimeZone.getAvailableIDs()
    val americas = for (elem <- ids if (elem.startsWith(filter))) yield elem.stripPrefix(filter + "/")
    quickSort(americas)
    americas
  }

  def getFlavors(): Buffer[String] = {
    import java.awt.datatransfer._
    import collection.JavaConversions._
    
    val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
    val natives = flavors.getNativesForFlavor(DataFlavor.imageFlavor)
    natives
  }

}