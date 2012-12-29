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
    assert( (book set Title to "Scala for the Impatient" set Author to "Cay Horstmann").toString() === 
      "Title: Scala for the Impatient Author: Cay Horstmann")
  }

}