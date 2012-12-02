package com.aidan.chapter9

import scala.io.Source
import scala.collection.mutable.ArrayBuffer

object Chapter9 {

  /*
   * Im not that happy that any of the following file handling methods are best practise.
   * I want to get the resource de-allocation in finally blocks but
   * that seems to mean using mutable variables.
   * 
   * Think this all needs looking at again.
   */
  def readFileAsArray(fileName: String): Array[String] = {
    var lines = Array[String]()
    var source = Source.fromFile(fileName, "UTF-8")
    try {
      lines = source.getLines.toArray
    } finally {
      source.close()
    }
    lines
  }

  def reverseLinesInFile(fileName: String) = {
    import java.io.PrintWriter
    val currentContents = readFileAsArray(fileName)
    var out: PrintWriter = null;

    try {
      out = new PrintWriter(fileName)
      for (line <- currentContents.reverse) out.println(line)
    } finally {
      out.close
    }
  }

  def changeTabsToSpaces(testInFile: String, testOutFile: String, numberSpaces: Int) = {
    import java.io.PrintWriter
    val currentContents = readFileAsArray(testInFile)
    var out: PrintWriter = null;

    try {
      out = new PrintWriter(testOutFile)
      for (line <- currentContents) out.println(line.replace("\t", " " * numberSpaces))
    } finally {
      out.close
    }
  }

  // Look mum - no error handling!
  def printLongWordsToConsole(testFile: String, numberOfCharacters: Int) = {
    for (word <- Source.fromFile(testFile).mkString.split("\\s") if word.length() > numberOfCharacters) println(word)
  }

  def calculateFormattedPowersRecips(powerOf: Int, startPower: Int, endPower: Int, formatPrecision: Int, testFile: String): Array[(String, String)] = {
    import java.io.PrintWriter

    val powers = for (i <- startPower to endPower) yield scala.math.pow(powerOf, i)
    val maxLength = powers.last.toString.length()
    val formatedPowersAndRecips = for (power <- powers) yield (
      " " * (maxLength - power.toString.length()) + power.toString,
      (1 / power).toString.take(formatPrecision))

    var out: PrintWriter = null;
    try {
      out = new PrintWriter(testFile)
      for (elem <- formatedPowersAndRecips) out.println(elem._1 + ", " + elem._2)
    } finally {
      out.close
    }

    formatedPowersAndRecips.toArray
  }

  /*
   * I don't understand what is being asked for in exercise 6 for Chapter 9 so
   * there is no code here for it :-)
   */

  def findNonFloatingPointData(testFile: String) = {
    val fpRegex = """[-+]?[0-9]*\.?[0-9]*""".r
    val fileContents = Source.fromFile(testFile).mkString
    val fps = fpRegex.findAllIn(fileContents).toArray
    // As I am not great at regular expressions, trying to negate the above RE would be difficult!
    // Therefore, the "cheat" is to apply a diff operator to find those in the full file contents
    // that aren't in the floating point numbers found by the regular expression.
    // So most of the work has been done by a regular expression...
    fileContents.split("\\s").diff(fps)
  }
  
  def findAllImageSrc(webpageUrl: String) = {
    val rePattern = """(<img) (src=\S*)""".r
    for ( rePattern(img, src) <- rePattern.findAllIn(Source.fromURL(webpageUrl, "UTF-8").mkString)) yield src.drop(4)
  }

}

// Look mum - still no error handling!
class FloatingPointReader(testFile: String) {
  val tokens = Source.fromFile(testFile).mkString.split("\\s")
  val numbers = for (w <- tokens if !w.isEmpty()) yield w.toDouble

  def sum = numbers.sum
  def average = sum / numbers.length
  def min = numbers.min
  def max = numbers.max
}