package com.aidan.chapter13

object Chapter13Doodlings {
  def digits(n: Int): Set[Int] =
    if (n < 0) digits(-n)
    else if (n < 10) Set(n)
    else digits(n / 10) + (n % 10)                //> digits: (n: Int)Set[Int]

  digits(5)                                       //> res0: Set[Int] = Set(5)
  digits(43)                                      //> res1: Set[Int] = Set(4, 3)

  for (i <- (0 to 20).par) print(i + " ")         //> 15 12 13 16 14 17 1 0 10 2 18 3 19 4 20 5 7 9 8 6 11 
  for (i <- (0 to 20).par) yield i                //> res2: scala.collection.parallel.immutable.ParSeq[Int] = ParVector(0, 1, 2, 3
                                                  //| , 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
  val testArray = Array("The", "sands", "of", "time").mkString(" ")
                                                  //> testArray  : String = The sands of time
  val lst = List(1, 2, 3, 4, 5, 6, 7, 8)          //> lst  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8)
  (lst :\ List[Int]())(_ :: _)                    //> res3: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8)
  (List[Int]() /: lst)(_ :+ _)                    //> res4: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8)
}