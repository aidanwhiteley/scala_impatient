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