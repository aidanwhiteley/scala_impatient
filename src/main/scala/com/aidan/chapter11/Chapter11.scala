package com.aidan.chapter11

object Chapter11 {
  def operatorPrecedence1a = {
    val result = 3 + 4 -> 5
    result
  }
  def operatorPrecedence1b = {
    "Cant add an integer to a tuple"
  }

  def **(parm: Int): Int = {
    parm * parm
  }
}

class Fraction(n: Int, d: Int) {
  private val num: Int = if (d == 0) 1 else n * sign(d) / gcd(n, d)
  private val den: Int = if (d == 0) 0 else d * sign(d) / gcd(n, d)
  override def toString = num + "/" + den
  def sign(a: Int) = if (a > 0) 1 else if (a < 0) -1 else 0
  def gcd(a: Int, b: Int): Int = if (b == 0) scala.math.abs(a) else gcd(b, a % b)

  // Solution code.
  // Only basic fraction maths implemented - its 30 years since I did any fractional maths!
  def +(other: Fraction) = {
    val newDen = this.den * other.den
    val newNum = this.num * (newDen / this.den) + other.num * (newDen / other.den)
    val thisGcd = gcd(newDen, newNum)
    new Fraction(newNum / thisGcd, newDen / thisGcd)
  }
  def -(other: Fraction) = {
    val newDen = this.den * other.den
    val newNum = this.num * (newDen / this.den) - other.num * (newDen / other.den)
    val thisGcd = gcd(newDen, newNum)
    new Fraction(newNum / thisGcd, newDen / thisGcd)
  }
  def *(other: Fraction) = {
    val newDen = this.den * other.den
    val newNum = this.num * other.num
    val thisGcd = gcd(newDen, newNum)
    new Fraction(newNum / thisGcd, newDen / thisGcd)
  }
  def /(other: Fraction) = {
    val newDen = this.den * other.num
    val newNum = this.num * other.den
    val thisGcd = gcd(newDen, newNum)
    new Fraction(newNum / thisGcd, newDen / thisGcd)
  }

}
object Fraction {
  def apply(num: Int, den: Int) = new Fraction(num, den)
  def unapply(input: Fraction) =
    if (input.den == 0) None else Some((input.num, input.den))
}

class Money(d: Int, c: Int) {
  private val dollars = d + (c / 100) + (if (c < 0) -1 else 0)
  private val cents = if (c < 0) 100 + (c % 100) else (c % 100)
  def +(other: Money) = new Money(this.dollars + other.dollars, this.cents + other.cents)
  def -(other: Money) = new Money(this.dollars - other.dollars, this.cents - other.cents)
  def ==(other: Money) = (this.dollars == other.dollars) && (this.cents == other.cents)
  override def toString = (dollars + "." + cents)
}
object Money {
  def apply(dollars: Int, cents: Int) = new Money(dollars, cents)
}

class Table() {
  private var buffer: String = ""
  def |(text: String) = {
    buffer += "<td>" + text + "</td>"
    this
  }
  def ||(text: String) = {
    buffer += "</tr><tr><td>" + text + "</td>"
    this
  }
  def getTableHtml = "<table><tr>" + buffer + "</tr></table>"
}
object Table {
  def apply() = new Table()
}
