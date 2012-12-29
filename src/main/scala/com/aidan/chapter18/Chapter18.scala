package com.aidan.chapter18

object Chapter18 {

}
object show  
object then  
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
    if (useNextArgs == Title) {title = arg; this}
    else if (useNextArgs == Author) {author = arg; this}
    else this
  }
  override def toString = {
    "Title: " + title + " Author: " + author
  }
}
class Book extends Document
