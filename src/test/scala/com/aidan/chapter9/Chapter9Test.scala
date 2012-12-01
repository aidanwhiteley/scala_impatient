package com.aidan.chapter9

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Chapter9Test extends FunSuite {
  import com.aidan.chapter9.Chapter9._

  test("Reversing lines in file") {
    val testFile = "./src/test/scala/com/aidan/chapter9/myfile.txt"

    val fileAsArray = readFileAsArray(testFile)
    val firstLine = fileAsArray(0)
    val lastLine = fileAsArray(fileAsArray.length - 1)

    reverseLinesInFile(testFile)

    val reversedFileAsArray = readFileAsArray(testFile)
    assert(lastLine === reversedFileAsArray(0))
    assert(firstLine === reversedFileAsArray(reversedFileAsArray.length - 1))
  }

  /**
   * This test diverges from the book in that it doesnt
   * overwrite the input file. The aim is to make it more re-runnable.
   */
  test("Tabs to spaces") {
    val testInFile = "./src/test/scala/com/aidan/chapter9/mytabfile.txt"
    val testOutFile = "./src/test/scala/com/aidan/chapter9/myspacefile.txt"
    val tabToSpaces = 2

    changeTabsToSpaces(testInFile, testOutFile, tabToSpaces)

    // Forgetting about error handling in test file...
    import scala.io.Source
    assert(!Source.fromFile(testOutFile).mkString.contains("\t"))
  }

  test("Print all words > 12 characters") {
    val testFile = "./src/test/scala/com/aidan/chapter9/mylargewordsfile.txt"

    // Uncomment next line to see stuff splurged to console
    // printLongWordsToConsole(testFile, 12)

    // No actual tests in this test! Just stuff printed to the console
  }

  test("Reading floating point numbers from file") {
    val testFile = "./src/test/scala/com/aidan/chapter9/mynumbersfile.txt"
    val fp = new FloatingPointReader(testFile)
    assert(fp.sum === 48.0)
    assert(fp.average === 6.0)
    assert(fp.max === 20.0)
    assert(fp.min === 1.1)
  }

  test("Powers and reciprocals") {
    val testFile = "./src/test/scala/com/aidan/chapter9/mypowersfile.txt"
    val data = calculateFormattedPowersRecips(2, 0, 20, 20, testFile)

    val (power0, recip0) = data(0)
    assert(power0.trim().toDouble === 1.0)
    assert(recip0.trim().toDouble === 1.0)

    val (power20, recip20) = data(20)
    assert(power20.trim().toDouble === 1048576.0)
    assert(recip20.trim().toDouble === 9.5367431640625E-7)
  }

}