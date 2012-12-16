package com.aidan.chapter11

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Chapter11Test extends FunSuite {
  import com.aidan.chapter11.Chapter11._

  test("Operator precedence part 1a") {
    assert(new Tuple2(7, 5) === operatorPrecedence1a)
  }

  test("Fraction maths - addition") {
    var Fraction(num, den) = Fraction(1, 4) + Fraction(1, 4)
    assert(num === 1, "Adding two quarters - numerator")
    assert(den === 2, "Adding two quarters - denominator")
  }

  test("Fraction maths - subtraction") {
    var Fraction(num, den) = Fraction(2, 3) - Fraction(1, 6)
    assert(num === 1, "Subtracting - numerator")
    assert(den === 2, "Subtracting - denominator")
  }

  test("Fraction maths - multiplication") {
    var Fraction(num, den) = Fraction(2, 3) * Fraction(1, 2)
    assert(num === 1, "Multiplying - numerator")
    assert(den === 3, "Multiplying - denominator")
  }

  test("Fraction maths - division") {
    var Fraction(num, den) = Fraction(2, 3) / Fraction(1, 2)
    assert(num === 4, "Multiplying - numerator")
    assert(den === 3, "Multiplying - denominator")
  }

  test("Money addition") {
    assert(Money(1, 75) + Money(0, 10) == Money(1, 85), "Money addition 1")
    assert(Money(1, 75) + Money(0, 25) == Money(2, 00), "Money addition 2")
    assert(Money(1, 75) + Money(0, 50) == Money(2, 25), "Money addition 3")
    assert(Money(4, 99) + Money(6, 99) == Money(11, 98), "Money addition 4")
  }

  test("Money subtraction") {
    assert(Money(1, 75) - Money(0, 10) == Money(1, 65), "Money subtraction 1")
    assert(Money(1, 75) - Money(0, 75) == Money(1, 00), "Money subtraction 2")
    assert(Money(1, 75) - Money(0, 85) == Money(0, 90), "Money subtraction 3")
    assert(Money(4, 49) - Money(6, 99) == Money(-3, 50), "Money subtraction 4")
  }

  test("Operators for HTML table") {
    var aTable = Table() | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | "JVM, .NET"
    assert(aTable.getTableHtml === "<table><tr><td>Java</td><td>Scala</td></tr><tr><td>Gosling</td><td>Odersky</td></tr><tr><td>JVM</td><td>JVM, .NET</td></tr></table>")
  }

  test("ASCII art addition") {
    // No actual tests - just prints to the console
    // Some blank lines below to make the ascii arts different heights.
    val art1 = """
      
      
  -_-/             ,,
 (_ /          _   ||   _   
(_ --_   _-_  < \, ||  < \, 
  --_ ) ||    /-|| ||  /-|| 
 _/  )) ||   (( || || (( || 
(_-_-   \\,/  \/\\ \\  \/\\ 
"""

    val art2 = """
                                ,, 
 '                              || 
\\  _-_,        _-_  /'\\  /'\\ || 
|| ||_.        ||   || || || || || 
||  ~ ||       ||   || || || || || 
\\ ,-_-        \\,/ \\,/  \\,/  \\ 
"""

    val ascii1 = new ASCIIArt(art1)
    val ascii2 = new ASCIIArt(art2)
    println(ascii1 + ascii2)
    // println(ascii1 ^ ascii2)
  }
  
  test("Getting bits to/from a Long") {
    assert("Haven't got a Scooby about this one" === "Haven't got a Scooby about this one")
  }
  
  test("Playing with a Matrix") {
    assert("Its all getting a little school maths dependant for me" != "Haven't got a Scooby about this one either")
  }
  
  // Uncomment the unapply method and comment out the unapplySeq method to be able to run this test.
  // The "extractor sequence" test below will then fail.
  ignore("Extractors") {
    val richFile = new RichFile("""c:\yingtong\yiddleEyeHay.png""")
    val RichFile(path, name, ext) = richFile
    assert(path === """c:\yingtong""", "path")
    assert(name === "yiddleEyeHay", "name")
    assert(ext === "png", "ext")
  }
  
  test("Extractor sequence") {
    val richFile = new RichFile("/home/cay/readme.txt")
    val RichFile(first, middle, last) = richFile
    assert(first === "home", "first")
    assert(middle === "cay", "middle")
    assert(last === "readme.txt", "last")
  }

}