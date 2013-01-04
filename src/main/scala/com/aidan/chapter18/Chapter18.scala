package com.aidan.chapter18

import scala.collection.mutable.ArrayBuffer
import scala.language.reflectiveCalls

object Chapter18 {
  /*
   * Dont understand how to use infix notation with an Either so just returning an Either!
   */
  def indexIntoList(aList: List[Int], value: Int): Either[Int, Int] = {
    val indexedList = aList.zipWithIndex
    indexedList.find(p => p._1 == value) match {
      case Some(x) => Right(x._2)
      case None =>
        val (lessList, moreList) = indexedList.partition(p => p._1 < value)
        // Not testing for empty lists. Could do - but can't be bothered for this exercise
        if ((value - lessList.last._1) < (moreList.head._1 - value)) Left(lessList.last._2) else Left(moreList.head._2)
    }
  }

  def gimmeObjectsWithCloseMethod(target: { def close(): Unit }) = {
    try {
      doNothingMuch(target)
    } catch {
      case _: Throwable => // Not doing anything with it
    } finally {
      target.close
    }
  }
  def doNothingMuch(target: { def close(): Unit }) = {
    // Occasionally throw an exception
    if (System.currentTimeMillis() % 2 == 0)
      throw new Exception("Just because we can")
  }
  
  def printValues(applyCapableObj: { def apply(anInt: Int): Int }, from: Int, upto: Int) = {
    // Not checking for sensible input values of from and upto!!!
    for (i <- from to upto) yield applyCapableObj(i)
  }
}

object show
object thenn
object around

class Bug {
  sealed abstract class Direction
  case object Right extends Direction
  case object Left extends Direction

  private var direction: Direction = Right
  private var location: Int = 0

  def turn: this.type = {
    direction match {
      case Left => direction = Right
      case Right => direction = Left
    }
    this
  }

  def move(distance: Int): this.type = {
    direction match {
      case Left => location -= distance
      case Right => location += distance
    }
    this
  }

  def show: this.type = {
    print(location + " ")
    this
  }

  def and: this.type = {
    this
  }
}

object Title
object Author
class Document {
  private var useNextArgs: Any = null
  private var title: String = ""
  private var author: String = ""

  def set(obj: Title.type): this.type = { useNextArgs = obj; this }
  def set(obj: Author.type): this.type = { useNextArgs = obj; this }

  def to(arg: String): this.type = {
    if (useNextArgs == Title) { title = arg; this }
    else if (useNextArgs == Author) { author = arg; this }
    else this
  }

  override def toString = {
    "Title: " + title + " Author: " + author
  }
}
class Book extends Document

class Network {
  outerClass =>
  class Member(val name: String) {
    val contacts = new ArrayBuffer[Network#Member]
    val whichNetwork = outerClass
    override def equals(that: Any) = {
      // Not bothering with all the additional null / same type checks normally needed when 
      // implementing equals. Or doing a hashCode.
      if (outerClass == that.asInstanceOf[Member].whichNetwork) true else false
    }
  }
  private val members = new ArrayBuffer[Member]
  def join(name: String) = {
    val m = new Member(name)
    members += m
    m
  }
}

class ClassWithClose {
  var closed = false
  def close(): Unit = closed = true
}

trait Dim[T] {
  this: T =>
  val value: Double
  val name: String
  protected def create(v: Double): T
  def +(other: Dim[T]) = create(value + other.value)
  override def toString() = value + " " + name
}
class Seconds(val value: Double) extends Dim[Seconds] {
  val name = "s"
  override def create(v: Double) = new Seconds(v)
}
class Meters(val value: Double) extends Dim[Meters] {
  val name = "m"
  override def create(v: Double) = new Meters(v)
}
