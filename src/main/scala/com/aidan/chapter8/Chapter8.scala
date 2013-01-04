package com.aidan.chapter8

class BankAccount(initialBalance: Double) {
  private var balance = initialBalance
  def deposit(amount: Double) : Double = { balance += amount; balance }
  def withdraw(amount: Double) : Double = { balance -= amount; balance }
}

class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance) {
  override def deposit(amount: Double) : Double = { super.deposit(amount - checkingAccountCharge) }
  override def withdraw(amount: Double) : Double = { super.withdraw(amount + checkingAccountCharge) }
}

class SavingsAccount(initialBalance: Double) extends BankAccount(initialBalance) {
  private var xtnsThisMonth: Int = 0
  override def deposit(amount: Double) : Double = {
    xtnsThisMonth += 1
    val xtnCharge = if (xtnsThisMonth > 3) savingsAccountCharge else 0
    super.deposit(amount - xtnCharge)
  }
  override def withdraw(amount: Double) : Double = {
    xtnsThisMonth += 1
    val xtnCharge = if (xtnsThisMonth > 3) savingsAccountCharge else 0
    super.withdraw(amount + xtnCharge)
  }
  def earnMonthlyInterest : Double = {
    xtnsThisMonth = 0;
    // This next line is a terrible hack as a work around to the base class
    // definition of balance being private rather than protected. Could have
    // changed the base class field visibility or added a read only accessor 
    // but changing the base class felt like cheating.
    val currBal = super.withdraw(0.00)
    val interest = currBal * savingsInterestRate
    super.deposit(interest)
  }
}


abstract class Item {
  def price: Double
  def description: String
}
class SimpleItem(override val price: Double, override val description: String) extends Item
class Bundle(val description: String) {
  import scala.collection.mutable.ArrayBuffer
  val items = ArrayBuffer[Item]()
  def addItem(anItem: Item) : ArrayBuffer[Item] = items += anItem
  def price : Double  = (for (item <- items) yield item.price).sum
}

class Point(val x: Double, val y: Double) {
  final override def equals(other: Any) = {
    val that = other.asInstanceOf[Point]
    if (that == null) false
    else that.x == x && that.y == y
  }
  final override def hashCode : Int = 13 * x.toInt * 17 * y.toInt
}
object Point {
  def apply(x: Double, y: Double) : Point = new Point(x, y)
}
class LabeledPoint(val description: String, x: Double, y: Double) extends Point(x, y)

class Shape(x: Double, y: Double) {
  var centerPoint = new Point(x, y)
}
class Rectangle(val width: Double, val height: Double, x: Double, y: Double) extends Shape(x, y)
class Circle(val radius: Double, x: Double, y: Double) extends Shape(x, y)

class Square(corner: Point, val width: Double) {
  val cornerX = corner.x
  val cornerY = corner.y
  val height = width
}