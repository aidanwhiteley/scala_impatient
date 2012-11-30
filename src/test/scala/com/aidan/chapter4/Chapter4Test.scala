package com.aidan.chapter4

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Chapter4Test extends FunSuite {
  import com.aidan.chapter4.Chapter4._

  test("get discount") {
    val gizmos = Map("GPS" -> 10.00, "Dinghy" -> 10000.00, "House" -> 250000.00)
    assert(applyDiscount(gizmos)("GPS") === 9.00)
  }

  test("word map count") {
    import scala.collection.mutable.HashMap

    val in = new java.util.Scanner(new java.io.File("./src/test/scala/com/aidan/chapter4/myfile.txt"))
    val wordMap = mapFileToWords(in);
    assert(wordMap("the") === 2)
    assert(wordMap("time") === 1)
    assert(wordMap.getOrElse("phlogiston", 0) === 0)
  }

  test("word map count immutable") {
    val in = new java.util.Scanner(new java.io.File("./src/test/scala/com/aidan/chapter4/myfile.txt"))
    val wordMap = mapFileToWordsImmutable(in);
    assert(wordMap("the") === 2)
    assert(wordMap("time") === 1)
    assert(wordMap.getOrElse("phlogiston", 0) === 0)
  }

  test("word map count sorted") {
    import scala.collection.immutable.TreeMap

    val in = new java.util.Scanner(new java.io.File("./src/test/scala/com/aidan/chapter4/myfile.txt"))
    val wordMap = mapFileToWordsSorted(in);

    assert(wordMap.keys.head === "by")
    assert(wordMap.values.head === 1)
  }

  test("word map count sorted - java implementation") {
    import scala.collection.immutable.TreeMap

    val in = new java.util.Scanner(new java.io.File("./src/test/scala/com/aidan/chapter4/myfile.txt"))
    val wordMap = mapFileToWordsSortedJava(in);

    assert(wordMap.keys.head === "by")
    assert(wordMap.values.head === 1)
  }
  
  test("order of linked hash map") {
    val daysOfWeek = getDaysOfWeek()
    assert(daysOfWeek.head === ("Monday", java.util.Calendar.MONDAY))
  }
  
  test("get java properties") {
    val sysProps = getSysProperties
    assert(sysProps.keySet.contains("java.runtime.name"))
    
    var maxLength = 0
    for ((k, v) <- sysProps if k.length > maxLength) maxLength = k.length
    
    // Uncomment to spew loads to the tests sysout!
    // for ((k, v) <- sysProps) println(k + " " * (maxLength - k.length) + "| " + v)
  }
  
  test("min max") {
    val testArray = Array(4, 1, 5, 9, 3)
    val (min, max) = getMinMax(testArray)
    assert(min === 1)
    assert(max === 9)
  }
  
  test("lt eq gt") {
    val testArray = Array(4, 1, 5, 9, 3)
    val (lt, eq, gt) = lteqgt(testArray, 5)
    assert(lt === 3)
    assert(eq === 1)
    assert(gt === 1)
  }
  
  test("hello world zip") {
    val zip = "Hello".zip("world");
    assert(zip.head === ('H', 'w'))
  }

}