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