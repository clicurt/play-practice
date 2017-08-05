name := """play-java"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
 javaJdbc,
  cache,
  javaWs,
  "commons-io" % "commons-io" % "2.5",
  "com.orientechnologies" % "orientdb-core" % "2.2.20",
  "org.jodconverter" % "jodconverter-core" % "4.0.0-RELEASE",
  "net.sourceforge.jchardet" % "jchardet" % "1.0"
)


fork in run := false

fork in run := true