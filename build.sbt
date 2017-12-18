name := "ScalaForImpatient"

version := "0.3"

scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-actors" % "2.11.4",
  "org.scala-lang.modules" %% "scala-xml" % "1.0.4",
  "org.scala-lang" % "scala-reflect" % "2.11.4",
  "org.scalatest" %% "scalatest" % "3.0.4" % "test",
  "junit" % "junit" % "4.10" % "test"
)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
