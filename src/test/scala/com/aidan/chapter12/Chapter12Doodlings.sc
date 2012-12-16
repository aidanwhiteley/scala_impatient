package com.aidan.chapter12

object Chapter12Doodlings {
  import scala.math._
  val num = 3.14                                  //> num  : Double = 3.14
  val fun = ceil _                                //> fun  : Double => Double = <function1>
  fun(num)                                        //> res0: Double = 4.0
  
  (1 to 9).reduceLeft(_ * _)                      //> res1: Int = 362880
  
  def mulBy(factor: Double) = (x: Double) => factor * x
                                                  //> mulBy: (factor: Double)Double => Double
  val triple = mulBy(3)                           //> triple  : Double => Double = <function1>
  val half = mulBy(0.5)                           //> half  : Double => Double = <function1>
  println(triple(14) + " " + half(14))            //> 42.0 7.0
  
  def mul(x: Int, y: Int) = x * y                 //> mul: (x: Int, y: Int)Int
  mul(4, 5)                                       //> res2: Int = 20
 
 	def mulOneAtATime(x: Int) = (y: Int) => x * y
                                                  //> mulOneAtATime: (x: Int)Int => Int
  mulOneAtATime(4)(5)                             //> res3: Int = 20
 	 
}