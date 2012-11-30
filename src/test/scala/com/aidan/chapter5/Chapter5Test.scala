package com.aidan.chapter5

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Chapter5Test extends FunSuite {
  import com.aidan.chapter5.Chapter5._
  
  test("Improved counter") {
    val myCounter = new Counter(Int.MaxValue)
    myCounter.increment
    assert(myCounter.current === Int.MaxValue)
  }
  
  test("Bank account") {
    val account = new BankAccount
    account.deposit(20.00)
    account.withdraw(14.50)
    assert(account.balance == 5.50)
  }
  
  test("Basic time implementation") {
    val timeOne = new Time(18, 30)
    val timeTwo = new Time(20, 00)
    assert(timeOne.before(timeTwo), "Not before timeTwo")
  }
  
  test("Student class and Javabeans") {
    val aStudent = new Student("Socrates", 22)
    assert(aStudent.getName() === "Socrates")
    assert(aStudent.getId() === 22L)
  }
  
  test("Person with negative age") {
    val aPerson = new Person(-1)
    assert(aPerson.age === 0)
  }
  
  test("Named person with primary constructor") {
    val aPerson = new NamedPerson("Scooby Doo")
    assert(aPerson.firstName === "Scooby")
    assert(aPerson.lastName === "Doo")
    // Next line shouldnt compile
    // assert(aPerson.name === "Scooby Doo")
  }
  
  test("car tests") {
    val carOne = new Car("Ford", "Ka", "Bond 007")
    assert(carOne.manufacturer === "Ford")
    assert(carOne.model === "Ka")
    assert(carOne.modelYear === -1)
    assert(carOne.licensePlate === "Bond 007")
    
    val carTwo = new Car("Ford", "Ka")
    assert(carTwo.manufacturer === "Ford")
    assert(carTwo.model === "Ka")
    assert(carTwo.modelYear === -1)
    assert(carTwo.licensePlate === "")
  }
  
  test("Rewritten Employee class") {
    val anEmp = new Employee
    assert(anEmp.name === "John Q. Public")
    assert(anEmp.salary === 0.0)
    
    anEmp.salary = 2.50
    assert(anEmp.salary === 2.50)
  }

}