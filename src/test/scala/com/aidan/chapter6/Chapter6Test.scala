package com.aidan.chapter6

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Chapter6Test extends FunSuite {
  import com.aidan.chapter6.Chapter6._
  
  test("Conversion objects") {
    import com.aidan.chapter6.Conversions._
    assert(inchesToCentimeters(2.0) === 5.08)
    assert(gallonsToLiters(3.0) === 13.635)
    assert(milesToKilometers(4.0) === 6.437376)    
  }
  
  test("Conversion class hierarchy") {
    import com.aidan.chapter6.Conversions._
    assert(InchesToCentimeters.convert(2.0) === 5.08)
    assert(GallonsToLiters.convert(3.0) === 13.635)
    assert(MilesToKiloMeters.convert(4.0) === 6.437376)    
  }
  
  test("Origin extends java Point") {
    import com.aidan.chapter6.Origin._ 
    assert(Origin(2,3) === new java.awt.Point(2, 3))
    assert(Origin(new java.awt.Point(4, 5)) === new java.awt.Point(4, 5))
  }
  
  test("Point apply method") {
    import com.aidan.chapter6.Point._  
    assert(Point(3,4) === new Point(3, 4))
  }
  
  test("Playing card enum toString") {
    import com.aidan.chapter6.Suit._
    assert(Clubs.toString() === "♣")
  }
  
  test("Hard coded test for red card suit") {
    import com.aidan.chapter6.Suit._
    assert(isRed(Hearts))
    assert(! isRed(Clubs))
  }
  
  test("RGB colour cube") {
    import com.aidan.chapter6.Colours._
    assert(White.id === 0xFFFFFF)
    assert(Red.toString() === "Red")
  }

}