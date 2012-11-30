package com.aidan.chapter6

object Reverse extends App {
  if (args.length > 0) {
    for (i <- (0 until args.length).reverse) {
      print(args(i) + " ")
    }
  } else {
    println("Supply some arguments")
  }
}

