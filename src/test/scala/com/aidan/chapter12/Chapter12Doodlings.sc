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

  def mulOneAtATime(x: Int) = (y: Int) => x * y   //> mulOneAtATime: (x: Int)Int => Int
  mulOneAtATime(4)(5)                             //> res3: Int = 20

  def f1(x: Int) = (10 * x) - (x * x)             //> f1: (x: Int)Int
  f1(5)                                           //> res4: Int = 25

  val pairs = (1 to 10) zip (11 to 20)            //> pairs  : scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((1,11), 
                                                  //| (2,12), (3,13), (4,14), (5,15), (6,16), (7,17), (8,18), (9,19), (10,20))
  (1 to 10).map(_ * 3)                            //> res5: scala.collection.immutable.IndexedSeq[Int] = Vector(3, 6, 9, 12, 15, 1
                                                  //| 8, 21, 24, 27, 30)

}