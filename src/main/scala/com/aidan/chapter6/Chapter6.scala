package com.aidan.chapter6

object Chapter6 {

}

object Conversions {
  def inchesToCentimeters(inches: Double) = {
    val conversionFactor = 2.54
    inches * conversionFactor
  }

  def gallonsToLiters(gallons: Double) = {
    val conversionFactor = 4.545
    gallons * conversionFactor
  }

  def milesToKilometers(miles: Double) = {
    val conversionFactor = 1.609344
    miles * conversionFactor
  }
}

abstract class UnitConversion(val conversionFactor: Double) {
  def convert(valueToConvert: Double) = {
    valueToConvert * conversionFactor
  }
}

object InchesToCentimeters extends UnitConversion(2.54) {}
object GallonsToLiters extends UnitConversion(4.545) {}
object MilesToKiloMeters extends UnitConversion(1.609344) {}

object Origin extends java.awt.Point {
  // These methods break the Scala convention of returning an instance of the companion class.
  // There is no ability to provide constructor parameters to Scala objects
  def apply(x: Int, y: Int) = {
    new java.awt.Point(x, y)
  }

  def apply(point: java.awt.Point) = {
    new java.awt.Point(point)
  }
}

class Point(var x: Int, var y: Int) {
  // hashCode, equals and canEqual from Ch28 - Programming in Scala
  override def hashCode = 41 * (41 + x) + y
  override def equals(other: Any) = other match {
    case that: Point =>
      (that canEqual this) &&
        (this.x == that.x) && (this.y == that.y)
    case _ =>
      false
  }
  def canEqual(other: Any) = other.isInstanceOf[Point]
}
object Point {
  def apply(x: Int, y: Int) = {
    new Point(x, y)
  }
}

object Suit extends Enumeration {
  val Clubs = Value("♣")
  val Diamonds = Value("♦")
  val Hearts = Value("♥")
  val Spades = Value("♠")
  
  def isRed(suit: Suit.Value) : Boolean = {
    suit == Suit.Diamonds || suit == Suit.Hearts
  }
}

object Colours extends Enumeration {
  val Black = Value(0x000000, "Black")
  val Red = Value(0xff0000, "Red")
  val Green = Value(0x00ff00, "Green")
  val Blue = Value(0x0000ff, "Blue")
  val Cyan = Value(0x00ffff, "Cyan")
  val Magenta = Value(0xff00ff, "Magenta")
  val Yellow = Value(0xffff00, "Yellow")
  val White = Value(0xFFFFFF, "White")
}