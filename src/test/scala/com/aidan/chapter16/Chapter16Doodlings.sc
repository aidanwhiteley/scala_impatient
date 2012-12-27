package com.aidan.chapter16

object Chapter16Doodlings {
  val doc = <html></html>                         //> doc  : scala.xml.Elem = <html></html>
  val test = <fred/>(0)                           //> test  : scala.xml.Node = <fred></fred>
  
  import scala.xml.Text
  import scala.xml.Atom
  val test2 = <li>Fred</li> match { case <li>{Text(t)}</li> => t}
                                                  //> test2  : String = Fred
  
  val test3 = <li>{Text("Fred")}</li> match { case <li>{Text(t)}</li> => t}
                                                  //> test3  : String = Fred

}