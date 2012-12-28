package com.aidan.chapter17

object Chapter17Doodlings {
  class Pair[T <% Ordered[T]](val first: T, val second: T) {
  	def smaller = if (first < second) first else second
  }
  
  val p = new Pair("Fred", "Books")               //> p  : com.aidan.chapter17.Chapter17Doodlings.Pair[java.lang.String] = com.aid
                                                  //| an.chapter17.Chapter17Doodlings$Pair@6c8d5190
  println(p.smaller)                              //> Books
   
  val p2 = new Pair(4, 2)                         //> p2  : com.aidan.chapter17.Chapter17Doodlings.Pair[Int] = com.aidan.chapter17
                                                  //| .Chapter17Doodlings$Pair@25648263
  println(p2.smaller)                             //> 2
  
  // ==================
  
  class Pair2[T](val first: T, val second: T) {
  	def smaller(implicit ev: T <:< Ordered[T]) =
  		if (first < second) first else second
  }
  
  val q = new Pair2('a', 'b')                     //> q  : com.aidan.chapter17.Chapter17Doodlings.Pair2[Char] = com.aidan.chapter1
                                                  //| 7.Chapter17Doodlings$$anonfun$main$1$Pair2$1@5c9ddf21
  //println(q.smaller)
  
}