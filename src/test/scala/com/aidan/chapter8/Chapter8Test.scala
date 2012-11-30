package com.aidan.chapter8

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Chapter8Test extends FunSuite {
  import com.aidan.chapter8._

  test("Checking account charges $1") {
    val checking = new CheckingAccount(20.00)
    assert(checking.deposit(10.00) === 29.00)
    assert(checking.withdraw(5.00) === 23.00)
  }

  test("Savings account with free xtns") {
    val savings = new SavingsAccount(50.00)
    assert(savings.deposit(5.00) === 55.00)
    assert(savings.deposit(10.00) === 65.00)
    assert(savings.withdraw(20.00) === 45.00)

    // Over xtn limit - this xtn charges
    assert(savings.deposit(10.00) === 54.00)

    // Get interest and reset xtn limit
    val withInterest = 54.00 + (54.00 * savingsInterestRate)
    assert(savings.earnMonthlyInterest == withInterest)

    // Check xtn limit reset
    assert(savings.deposit(5.00) === withInterest + 5.00)
  }

  test("SimpleItem def overrides") {
    val aSimple = new SimpleItem(50.00, "Cheap GPS")
    assert(aSimple.price === 50.00)
    assert(aSimple.description === "Cheap GPS")
  }

  test("Bundle of SimpleItem") {
    val item1 = new SimpleItem(10.00, "Item 1")
    val item2 = new SimpleItem(20.00, "Item 2")

    val bundle = new Bundle("test bundle")
    bundle.addItem(item1)
    bundle.addItem(item2)

    assert(bundle.price === 30.00)
    assert(bundle.description === "test bundle")
  }

  test("LabelledPoint test") {
    val labeled = new LabeledPoint("Black Thursday", 1929, 230.07)
    assert(labeled.description === "Black Thursday")
    assert(labeled.x === 1929)
    assert(labeled.y === 230.07)
  }

  test("Shapes") {
    val rectangle = new Rectangle(10, 10, 0, 0)
    val circle = new Circle(5, 10, 0)
    assert(rectangle.centerPoint === new Point(0, 0))
    assert(circle.centerPoint === new Point(10, 0))
  }

  test("Three squares") {
    val square1 = new Square(Point(5, 5), 10)
    val square2 = new Square(Point(0, 0), 5)
    val square3 = new Square(Point(0, 0), 0)

    // Nothing meaningful to check given the requirements spec!
    assert(square1.height === 10)
    assert(square2.height === 5)
    assert(square3.height === 0)
  }

}