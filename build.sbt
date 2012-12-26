name := "ScalaForImpatient"

version := "0.1"

scalaVersion := "2.9.2"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "1.8" % "test",
  "junit" % "junit" % "4.10" % "test"
)

scalacOptions ++= Seq("-unchecked", "-deprecation")
