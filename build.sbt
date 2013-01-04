name := "ScalaForImpatient"

version := "0.2"

scalaVersion := "2.10.0"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.0.M5b" % "test",
  "junit" % "junit" % "4.10" % "test",
  "org.scala-lang" % "scala-actors-migration_2.10" % "1.0.0"
)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

org.scalastyle.sbt.ScalastylePlugin.Settings
