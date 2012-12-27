package com.aidan.chapter5

import scala.reflect.BeanProperty

object Chapter5 {

}

class Counter(var value: Int) {
  //private var value = 0
  def increment() { if (value < Int.MaxValue) value += 1 }
  def current = value
}

class BankAccount {
  private var amount: Double = 0.00
  def deposit(anAmount: Double) = amount += anAmount
  def withdraw(anAmount: Double) = amount -= anAmount
  def balance = amount
}

class Time(val hrs: Int, val min: Int) {
  if (hrs < 0 || hrs > 23) throw new IllegalArgumentException
  if (min < 0 || min > 59) throw new IllegalArgumentException

  private val totalMins = (hrs * 60) + min

  def before(other: Time) = {
    totalMins < other.totalMins
  }
}

class Student(@BeanProperty var name: String, @BeanProperty var id: Long)

class Person(var age: Int) {
  if (age < 0) age = 0;
}

class NamedPerson(name: String) {
  val firstName = (name.split(" "))(0)
  val lastName = (name.split(" "))(1)
}

class Car(val manufacturer: String, val model: String, val modelYear: Int = -1, var licensePlate: String) {

  def this(manufacturer: String, model: String) {
    this(manufacturer, model, -1, "")
  }

  def this(manufacturer: String, model: String, licensePlate: String) {
    this(manufacturer, model, -1, licensePlate)
  }

  def this(manufacturer: String, model: String, modelYear: Int) {
    this(manufacturer, model, modelYear, "")
  }
}

class Employee {
  private var privateName: String = "John Q. Public"
  var salary: Double = 0.0

  def this(name: String, salary: Double) {
    this()
    this.privateName = name
    this.salary = salary
  }

  def name = privateName
}
