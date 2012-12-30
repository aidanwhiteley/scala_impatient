package com.aidan.chapter18

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Chapter18Test extends FunSuite {
  import com.aidan.chapter18.Chapter18._

  test("Bug class chainable methods") {
    // Not much to automatically verify here as output is a "side effect".
    // Look at console output top see if it works
    val bugsy = new Bug
    assert(bugsy.move(4).show.move(6).show.turn.move(5).show.isInstanceOf[com.aidan.chapter18.Bug])
  }

  /*
   * Ignored as part of the code has been commented out to allow it to compile.
   */
  ignore("Fluent interface for Bug class") {
    // Not much to automatically verify here as output is a "side effect".
    // Look at console output top see if it works
    val bugsy = new Bug

    // FAILED! I can make "and" a parameter of "show" but that doesn't help
    // when it then needs to be a method call after "move(6)"!
    bugsy move 4 show //and then move 6 and show turn around move 5 and show 
  }

  test("Fluent interface for Ex 3") {
    val book = new Book()
    assert((book set Title to "Scala for the Impatient" set Author to "Cay Horstmann").toString() ===
      "Title: Scala for the Impatient Author: Cay Horstmann")
  }

  test("Type projection and Network membership") {
    val chatter = new Network
    val myFace = new Network

    val fred = chatter.join("Fred")
    val barney = myFace.join("Barney")
    fred.contacts += barney // allowed due to type projection on contacts type
    val penny = chatter.join("penny")

    assert(fred == penny, "Fred is equal to Penny") // According to the well odd definition that two members are equal if in the same network!!!!
    assert(fred != barney, "But Fred is not equal to Barney") // In different networks
  }

  test("Type alias - forSome") {
    val chatter = new Network
    val myFace = new Network
    val fred = chatter.join("Fred")
    val wilma = chatter.join("Wilma")
    val barney = myFace.join("Barney")

    type NetMember = n.Member forSome { val n: Network }
    def process1[M <: NetMember](m1: M, m2: M) = (m1, m2)

    process1(fred, wilma)
    //process1(fred, barney)	// Not allowed - not same network

    type NetworkMember = n.Member forSome { val n: Network }
    def process2(m1: NetworkMember, m2: NetworkMember) = (m1, m2)
    process2(fred, wilma)
    // Allowed - no check they are both of same Network
    assert(process2(fred, barney) === (fred, barney))
  }

  test("Either and infix type notation") {
    val intList = List(2, 4, 5, 6, 8, 15, 20).sorted

    val result = indexIntoList(intList, 4)
    result match {
      case Right(x) => assert(x === 1)
      case Left(x) => assert(false, "Expected to find exact match. Returned value was: " + x)
    }
    
    val result2 = indexIntoList(intList, 13)
    result2 match {
      case Right(x) => assert(false, "Did not expect to find exact match. Returned value was: " + x)
      case Left(x) => assert(x === 5)
    }
  }
  
  test("Writing a method signature that only accepts objects ith close() method") {
    val testClass = new ClassWithClose
    gimmeObjectsWithCloseMethod(testClass)
    assert(testClass.closed, "Checking close called OK")
    
    // gimmeObjectsWithCloseMethod("Wont compile - no close() on a String")
  }
}