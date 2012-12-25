package com.aidan.chapter14

object Chapter14Doodlings {
  import scala.collection.JavaConversions.propertiesAsScalaMap
  for ((k, v) <- System.getProperties() if v =="") println(k)
                                                  //> user.script
                                                  //| sun.os.patch.level
                                                  //| user.variant
                                                  //| user.timezone
}