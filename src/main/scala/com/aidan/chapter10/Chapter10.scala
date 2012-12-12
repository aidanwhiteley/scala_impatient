package com.aidan.chapter10

import java.beans.{ PropertyChangeSupport, PropertyChangeListener, PropertyChangeEvent }

object Chapter10 {
}

trait RectangleLike {

  def setFrame(x: Double, y: Double, w: Double, h: Double)
  def getX: Double
  def getY: Double
  def getWidth: Double
  def getHeight: Double

  def translate(x: Int, y: Int) = {
    setFrame(getX + x, getY + y, getWidth, getHeight)
  }

  def grow(h: Int, v: Int) = {
    setFrame(getX - h, getY - v, getWidth + (2 * h), getHeight + (2 * v))
  }
}

class OrderedPoint(x: Int, y: Int) extends java.awt.Point(x, y) with scala.math.Ordered[OrderedPoint] {
  def compare(that: OrderedPoint) = {
    if (this.x == that.x) this.y - that.y
    else this.x - that.x
  }
}

trait MessageLogger {
  def log(msg: String) = msg
}

trait CaesarCryptLogger extends MessageLogger {
  var rotation: Int
  override def log(msg: String) = {
    val rotated = for (ch <- msg) yield {
      // Uppercase ASCII A-Z
      if (ch >= 65 && ch <= 90) {
        val rotated = ch + rotation
        if (rotated > 90) {
          ((rotated - 90) + 64).toChar
        } else if (rotated < 65) {
          (90 - (64 - rotated)).toChar
        } else rotated.toChar
      } // Lowercase ASCII a-z
      else if (ch >= 97 && ch <= 122) {
        val rotated = ch + rotation
        if (rotated > 122) {
          ((rotated - 122) + 96).toChar
        } else if (rotated < 97) {
          (122 - (96 - rotated)).toChar
        } else rotated.toChar
      } else {
        // Not handling non alphas
        ch.toChar
      }
    }
    rotated.mkString
  }
}

class CryptoLogger extends CaesarCryptLogger {
  var rotation = 3

  def this(rotation: Int) {
    this()
    this.rotation = rotation
  }
}

/* 
 * Only implementing one of the PropertyChangeSupport methods!
 * 
 * Passes method calls on to PropertyChangeSupport delegate.
 */
trait PropertyChangeSupportT {
  val propChange = new PropertyChangeSupport(this)
  def addPropertyChangeListener(listener: PropertyChangeListener) = {
    propChange.addPropertyChangeListener(listener)
  }
}

/*
 * I dont really like my own solution! Means that each method in Point needs to be overriden to
 * a) call the super class
 * b) fire an event
 */
class ListeningPoint(x: Int, y: Int) extends java.awt.Point with PropertyChangeSupportT {
  override def move(x: Int, y: Int) {
    super.move(x, y)

    // Not populated the PropertyChangeEvent object properly - the work isn't worth it for this demo
    propChange.firePropertyChange(new PropertyChangeEvent(this, "X", "Get old value", "Output new value"))
  }
}

trait Drinker {
  var thirsty = true;
  val favouriteDrink: String
  val usualAmount = "glass"
  def drink = {
    //println("Drinking a " + usualAmount + " of " + favouriteDrink)
    thirsty = false
  }
}
trait BeerDrinker extends Drinker {
  val favouriteDrink = "beer"
  override val usualAmount = "pint"
}

trait Speaker {
  def speak(what: String) = what
}

trait NoLowerCaseVowelSpeaker extends Speaker {   
  override def speak(what: String) = {
    val noVowels = what.filter(p => ! Array('a', 'e', 'i', 'o', 'u').contains(p) )
    super.speak(noVowels.mkString)
  }
}

trait LoudSpeaker extends Speaker {
  override def speak(what: String) = {
    super.speak(what.toUpperCase())
  }
}
class Programmer {
  
}

trait BufferTrait extends java.io.InputStream {
  val bufferLength: Int
  override def read : Int = {
    var content = new Array[Byte](bufferLength)
    super.read(content, 0, bufferLength)
  }
  def readContent: String = {
    var content = new Array[Byte](bufferLength)
    super.read(content, 0, bufferLength)
    (for (ch <- content) yield ch.toChar).mkString
  }
}