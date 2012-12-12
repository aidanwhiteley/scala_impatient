package com.aidan.chapter10

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Chapter10Test extends FunSuite {
  import com.aidan.chapter10._

  test("Adding grow and transalte methods to Ellipse2D java class") {
    // Constructor x, y, width, height
    val egg = new java.awt.geom.Ellipse2D.Double(5, 10, 20, 30) with RectangleLike
    egg.translate(10, -10)
    assert(egg.getX() === 15, "Test x")
    assert(egg.getY() === 0, "Test y")

    egg.grow(10, 20)
    assert(egg.getX() === 5, "Test grow X")
    assert(egg.getY() === -20, "Test grow Y")
    assert(egg.getWidth() === 20 + (2 * 10), "Test grow width")
    assert(egg.getHeight() === 30 + (2 * 20), "Test grow height")
  }

  test("OrderedPoint mixin") {
    val point1 = new OrderedPoint(3, 5)
    val point2 = new OrderedPoint(4, 5)
    val point3 = new OrderedPoint(3, 6)
    val point4 = new OrderedPoint(1, 1)
    val point5 = new OrderedPoint(3, 5)

    assert(point1 < point2, "first test")
    assert(point1 < point3, "second test")
    assert(point1 > point4, "third test")
    assert(point1 >= point5, "fourth test")
  }

  test("Crypto logger") {
    val logger = new CryptoLogger(5)
    assert("yjxy" === logger.log("test"))

    val logger2 = new CryptoLogger()
    assert("cBd" === logger2.log("zYa"))

    val logger3 = new CryptoLogger(-3)
    assert("zYa" === logger3.log("cBd"))
  }

  test("Property change listener") {
    import java.beans.{ PropertyChangeListener, PropertyChangeEvent }

    class TestPropertyChangeListener extends PropertyChangeListener {
      var hasEventOccurred = false
      def propertyChange(evt: PropertyChangeEvent) = {
        hasEventOccurred = true;
      }
    }
    val myListerner = new TestPropertyChangeListener

    val point = new ListeningPoint(5, 10)
    point.addPropertyChangeListener(myListerner)
    point.move(3, 2)

    assert(myListerner.hasEventOccurred === true, "Has event occurred")
  }

  test("Rather poor trait hierarchy") {
    val programmer = new Programmer with BeerDrinker
    programmer.drink
    assert(programmer.thirsty === false)
    assert(programmer.favouriteDrink === "beer")

    val clippedNoisyProgrammer = new Programmer with LoudSpeaker with NoLowerCaseVowelSpeaker
    assert(clippedNoisyProgrammer.speak("scala is cool") === "SCL S CL")

    val nosiyClippedProgrammer = new Programmer with NoLowerCaseVowelSpeaker with LoudSpeaker
    assert(nosiyClippedProgrammer.speak("scala is cool") === "SCALA IS COOL")
  }

  test("Using trait to buffer input stream") {
    import java.io._

    val bLength = 1000
    // Test file has more content than specified buffer size
    val testStream = new FileInputStream("./src/test/scala/com/aidan/chapter10/myfile.txt") with BufferTrait {
      val bufferLength = bLength
    }
    
    // Of course, the following method is completely useless as the content from teh file isnt accessible!
    assert(testStream.read === bLength, "Didnt read expected size of buffered content")
    // So here's a slightly more useful method (ignoring error conditions as usual)
    assert(testStream.readContent.size === bLength, "Didnt get the expected content back")
  }

}