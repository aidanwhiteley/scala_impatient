package com.aidan.chapter13

object Chapter13Doodlings {
  def digits(n: Int): Set[Int] =
  	if (n < 0) digits(-n)
  	else if (n < 10) Set(n)
  	else digits(n / 10) + (n % 10)            //> digits: (n: Int)Set[Int]
  	
  digits(5)                                       //> res0: Set[Int] = Set(5)
  digits(43)                                      //> res1: Set[Int] = Set(4, 3)
  
  for (i <- (0 to 100).par) print(i + " ")        //> 0 50 1 51 2 52 3 53 4 54 5 55 56 57 12 58 13 18 14 19 15 20 16 21 17 43 44 4
                                                  //| 5 6 46 7 47 8 48 9 49 10 37 11 38 25 39 26 40 27 41 28 42 29 30 75 34 76 35 
                                                  //| 31 62 32 63 33 64 65 66 68 69 22 70 23 71 24 72 73 59 74 60 67 81 36 77 88 8
                                                  //| 4 85 91 86 92 87 93 79 89 94 90 95 99 96 100 82 61 83 97 98 80 78 
  for (i <- (0 to 100).par) yield i + " "         //> res2: scala.collection.parallel.immutable.ParSeq[String] = ParVector(0 , 1 ,
                                                  //|  2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10 , 11 , 12 , 13 , 14 , 15 , 16 , 17 , 18 
                                                  //| , 19 , 20 , 21 , 22 , 23 , 24 , 25 , 26 , 27 , 28 , 29 , 30 , 31 , 32 , 33 ,
                                                  //|  34 , 35 , 36 , 37 , 38 , 39 , 40 , 41 , 42 , 43 , 44 , 45 , 46 , 47 , 48 , 
                                                  //| 49 , 50 , 51 , 52 , 53 , 54 , 55 , 56 , 57 , 58 , 59 , 60 , 61 , 62 , 63 , 6
                                                  //| 4 , 65 , 66 , 67 , 68 , 69 , 70 , 71 , 72 , 73 , 74 , 75 , 76 , 77 , 78 , 79
                                                  //|  , 80 , 81 , 82 , 83 , 84 , 85 , 86 , 87 , 88 , 89 , 90 , 91 , 92 , 93 , 94 
                                                  //| , 95 , 96 , 97 , 98 , 99 , 100 )
}