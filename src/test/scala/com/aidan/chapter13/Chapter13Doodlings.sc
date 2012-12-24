package com.aidan.chapter13

object Chapter13Doodlings {
  def digits(n: Int): Set[Int] =
    if (n < 0) digits(-n)
    else if (n < 10) Set(n)
    else digits(n / 10) + (n % 10)                //> digits: (n: Int)Set[Int]

  digits(5)                                       //> res0: Set[Int] = Set(5)
  digits(43)                                      //> res1: Set[Int] = Set(4, 3)

  for (i <- (0 to 100).par) print(i + " ")        //> 0 62 1 63 2 64 3 65 4 66 5 37 38 39 25 40 50 41 51 42 52 43 53 44 54 45 55 4
                                                  //| 6 12 13 6 7 75 8 76 9 77 10 78 11 79 14 80 15 47 16 48 17 49 56 57 58 18 59 
                                                  //| 60 26 61 27 67 68 19 69 20 70 21 29 22 30 23 28 24 31 81 32 82 34 88 35 94 3
                                                  //| 6 83 33 97 98 71 91 72 73 74 99 100 84 95 89 96 90 85 86 92 87 93 
  for (i <- (0 to 100).par) yield i + " "         //> res2: scala.collection.parallel.immutable.ParSeq[String] = ParVector(0 , 1 ,
                                                  //|  2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10 , 11 , 12 , 13 , 14 , 15 , 16 , 17 , 18 
                                                  //| , 19 , 20 , 21 , 22 , 23 , 24 , 25 , 26 , 27 , 28 , 29 , 30 , 31 , 32 , 33 ,
                                                  //|  34 , 35 , 36 , 37 , 38 , 39 , 40 , 41 , 42 , 43 , 44 , 45 , 46 , 47 , 48 , 
                                                  //| 49 , 50 , 51 , 52 , 53 , 54 , 55 , 56 , 57 , 58 , 59 , 60 , 61 , 62 , 63 , 6
                                                  //| 4 , 65 , 66 , 67 , 68 , 69 , 70 , 71 , 72 , 73 , 74 , 75 , 76 , 77 , 78 , 79
                                                  //|  , 80 , 81 , 82 , 83 , 84 , 85 , 86 , 87 , 88 , 89 , 90 , 91 , 92 , 93 , 94 
                                                  //| , 95 , 96 , 97 , 98 , 99 , 100 )
  val testArray = Array("The", "sands", "of", "time").mkString(" ")
                                                  //> testArray  : String = The sands of time
}