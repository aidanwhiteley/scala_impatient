name := "ScalaForImpatient"

version := "0.2"

scalaVersion := "2.10.0"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-actors" % "2.10.0",
  "org.scalatest" %% "scalatest" % "2.0.M5b" % "test",
  "junit" % "junit" % "4.10" % "test"
)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

org.scalastyle.sbt.ScalastylePlugin.Settings
