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

}