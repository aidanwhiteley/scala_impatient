package com.aidan.chapter20

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Chapter20Test extends FunSuite {
  import com.aidan.chapter20.Chapter20._

  test("Using Actors to calculate average of large array") {

    val numberOfNumbers = 1000000
    val numberOfActors = 8 // matching number of CPUs on my test machine
    val timeTakenAcrossMultipleCores = exerciseOneMain(numberOfNumbers, numberOfActors)
    val timeTakenWithOneActor = exerciseOneMain(numberOfNumbers, 1)

    // Assert commented out as
    // a) too machine dependant
    // b) for shorter tests, the setup costs of slicing the numbers array and creating Actors is greater than the parallel
    //    computation benefits
    //assert(timeTakenAcrossMultipleCores < timeTakenWithOneActor, "Adjust numberOfActors parameter for your test machine")
  }

  // No test for exercise 2 - would take too much time learning about Java BufferedImages
  // TODO - exercise 2 inverting strips of a BufferedImage

  test("Ex 3, 4 and 5 - using Actors to find matching words in files") {
    import com.aidan.chapter20.exercise3._

    val regex = "TODO"
    val rootDir = "./src"
    findFilesContainingMatchingWords(regex: String, rootDir: String)
  }

  test("Ex 6 - Comparing thread usage of receive versus react") {
    import com.aidan.chapter20.exercise6._
    val receiveResults = receiveActorsRun
    
    // Haven't figured out how to make this wait until the "supervisor" actor 
    // has finished. Without this, the JVM spawned for this test ends
    // and all Actors and processing stops...
    // TODO - remove this sleep statement!
    Thread.sleep(100)
    
    val reactResults = reactActorsRun
    Thread.sleep(100)
    
    assert(receiveResults.threads.size > reactResults.threads.size, 
        "Number of threads used by RECEIVE based actors should be much higher than used by REACT actors")
    
    println("Number of threads used by RECEIVE based actors: " + receiveResults.threads.size)
    println("Number of threads used by REACT based actors: " + reactResults.threads.size)
  }

}