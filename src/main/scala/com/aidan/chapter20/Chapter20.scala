package com.aidan.chapter20

import scala.util.Random
import scala.actors.Actor

object Chapter20 {

  def exerciseOneMain(numberOfNumbers: Int, actors: Int): Long = {

    // Set up stuff - outside of timing
    val bigArray = new Array[Int](numberOfNumbers)
    val random = new Random()
    for (i <- 0 until numberOfNumbers) bigArray(i) = random.nextInt(100) // nextInt in smallish range to avoid exceeding max Int size
    val arrayChunks = bigArray.grouped(numberOfNumbers / actors)

    val start = System.currentTimeMillis()

    val resultsActor = new ResultsCollaterActor(actors)
    resultsActor.start
    
    for (j <- 0 until actors) {
      // Make sure the last created Actor has any remaining entries from the numbers array
      if (j == (actors - 1) && (numberOfNumbers % actors != 0)) {
        val lastArrayChunk = arrayChunks.next
        val lastChunk = lastArrayChunk ++ bigArray.slice(lastArrayChunk.size * actors, numberOfNumbers)
        (new NumberCruncherActor()).start ! NumbersToProcess(lastChunk, resultsActor)
      } else {
        (new NumberCruncherActor()).start ! NumbersToProcess(arrayChunks.next, resultsActor)
      }
    }

    System.currentTimeMillis() - start
  }

}

class NumberCruncherActor extends Actor {
  def act() {
    while (true) {
      receive {
        case NumbersToProcess(numArray, resultsActor) => {
          val average: Double = numArray.sum / numArray.size
          val message = new ComputedAverage(average)
          resultsActor ! message
        }
      }
    }
  }
}

class ResultsCollaterActor(val numActors: Int) extends Actor {
  var resultsReceived: Int = 0
  val results = new Array[Double](numActors)
  def act() {
    while (true) {
      receive {
        case ComputedAverage(average) => {
          results(resultsReceived) = average
          resultsReceived += 1
          if (resultsReceived == numActors) {
            println("Results collation Actor says the overall average: " + results.sum / results.size)
          }
        }
        case _ => "Not expected!"
      }
    } 
  }
}

case class NumbersToProcess(val numArray: Array[Int], val resultsActor: ResultsCollaterActor)
case class ComputedAverage(val numArray: Double)