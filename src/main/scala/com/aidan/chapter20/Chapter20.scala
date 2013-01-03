package com.aidan.chapter20

import java.io.File

import scala.Array.canBuildFrom
import scala.actors.Actor
import scala.util.Random
import scala.io.Source

import com.aidan.chapter9.Chapter9.subdirs

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
        case _ => throw new IllegalArgumentException("Not expected message to ResultsCollaterActor!")
      }
    }
  }
}

case class NumbersToProcess(val numArray: Array[Int], val resultsActor: ResultsCollaterActor)
case class ComputedAverage(val numArray: Double)

// //////////////////////////////
// Exercise 3
// //////////////////////////////
object exercise3 {
  def findFilesContainingMatchingWords(regex: String, rootDir: String) = {
    import com.aidan.chapter9.Chapter9.subdirs

    val resultsActor = new MatchingFileResultsActor
    resultsActor.start
    
    val filesActor = new FileFinderActor(rootDir, regex, resultsActor)
    filesActor.start
  }
}

class FileFinderActor(val rootDir: String, val regex: String, val resultsActor: MatchingFileResultsActor) extends Actor {
  def act() {
    // Dont need an "event loop" as this Actor has just one task to do - so it is really just the same as a thread???
    
    // Keep a count of workers created to be able to pass to results actor so it knows when all results received.
    var countActors = 0;
    for (d <- subdirs(new File(rootDir)); f <- d.listFiles if f.isFile()) {
      (new FindWordsInFileActor(f, regex, resultsActor)).start
      countActors += 1
    }

    resultsActor ! (new ActorsStarted(countActors))
  }
}

class MatchingFileResultsActor extends Actor {
  var numberOfActorsStarted = -1
  var numberOfActorsFinished = 0

  // Map of file names against a set of matching lines in the file
  var results = Map[String, Set[String]]()

  def act() {
    var continue = true
    while (continue) {
      receive {
        case MatchedFiles(fileName, matchedLine) => {
          val currentMatches = results.getOrElse(fileName, Set())
          results += (fileName -> (currentMatches + matchedLine))
        }
        case ActorsStarted(actorsStarted) => numberOfActorsStarted = actorsStarted
        case ActorsFinished() => numberOfActorsFinished += 1
        case _ => throw new IllegalArgumentException("Not expected message to MatchingFileResultsActor!")
      }
      if (numberOfActorsStarted == numberOfActorsFinished) {
        prettyPrint
        continue = false
      }
    }
  }

  // Pretty print the matching files and lines using one print statement
  private def prettyPrint: Unit = {
    val fileSeparator = System.getProperty("file.separator", "\\")
    val lineSeparator = System.getProperty("line.separator", "\\")
    
    var outputString = ""
    for ((k, v) <- results) {
      val theFile = (k.drop(k.lastIndexOf(fileSeparator) + 1))
      val paddedFile = theFile.padTo(30, " ").mkString
      outputString += ((for (text <- v) yield (
        if (text.trim.length > 80) {
          paddedFile + text.trim.take(77) + "..." + lineSeparator
        } else {
          paddedFile + text.trim + lineSeparator
        }))).mkString
    }
    println(outputString)
  }
}

class FindWordsInFileActor(val file: File, val regex: String, val resultsActor: MatchingFileResultsActor) extends Actor {
  def act() {
    // Dont need an "event loop" as this Actor has just one task to do - so it is really just the same as a thread???

    import com.aidan.chapter9.Chapter9.readFileAsArray

    val regexCompiled = regex.r // From a performance point of view, this could be done once outside of each Actor
    val fileContents = readFileAsArray(file.toString)
    val matches = for (line <- fileContents if (None != regexCompiled.findFirstIn(line))) yield line
    if (matches.size > 0) {
      for (matched <- matches) resultsActor ! (new MatchedFiles(file.toString, matched))
    }

    resultsActor ! (new ActorsFinished())
  }
}

case class MatchedFiles(val fileName: String, val matchedLine: String)
case class ActorsStarted(val actorsStarted: Int)
case class ActorsFinished()