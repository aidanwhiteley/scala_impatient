package com.aidan.chapter20

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Chapter20Test extends FunSuite {
  import com.aidan.chapter20.Chapter20._

  test("Using Actors to calculate average of large array") {
    
    val numberOfNumbers = 1000000
    val numberOfActors = 8 		// matching number of CPUs on my test machine
    val timeTakenAcrossMultipleCores = exerciseOneMain(numberOfNumbers, numberOfActors)
    val timeTakenWithOneActor = exerciseOneMain(numberOfNumbers, 1)
    
    // Assert commented out as
    // a) too machine dependant
    // b) for shorter tests, the setup costs of slicing the numbers array and creating Actors is greater than the parallel
    //    computation benefits
    //assert(timeTakenAcrossMultipleCores < timeTakenWithOneActor, "Adjust numberOfActors parameter for your test machine")
  }

}