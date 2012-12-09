package com.aidan.chapter10

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

trait CaeasorCryptLogger extends MessageLogger {
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

class CryptoLogger extends CaeasorCryptLogger {
  var rotation = 3

  def this(rotation: Int) {
    this()
    this.rotation = rotation
  }
}