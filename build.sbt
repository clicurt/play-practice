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
  "net.sourceforge.jchardet" % "jchardet" % "1.0",
  "org.codehaus.sonar-plugins.java" % "sonar-jacoco-listeners" % "2.1",
  "junit" % "junit" % "4.11" % "test",
  "org.apache.maven.plugins" % "maven-surefire-plugin" % "2.13",
  "org.sonarsource.java" % "sonar-jacoco-listeners" % "3.8" % "test"
)


fork in run := false

fork in run := true