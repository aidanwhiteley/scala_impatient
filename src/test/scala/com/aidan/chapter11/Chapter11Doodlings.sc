package com.aidan.chapter11

object Chapter11Doodlings {
  val test = 3 + 4 -> 5                           //> test  : (Int, Int) = (7,5)
  val result = (3 -> 4) + "Fred"                  //> result  : String = (3,4)Fred
  def gcd(a: Int, b: Int) : Int = if (b == 0) scala.math.abs(a) else gcd(b, a % b)
                                                  //> gcd: (a: Int, b: Int)Int
  gcd(3, 6)                                       //> res0: Int = 3
 
}