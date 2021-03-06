name := """play-java"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  jdbc,
  evolutions,
  "commons-io" % "commons-io" % "2.5",
  "com.orientechnologies" % "orientdb-core" % "2.2.20",
  "org.jodconverter" % "jodconverter-core" % "4.0.0-RELEASE",
  "net.sourceforge.jchardet" % "jchardet" % "1.0",
  "org.codehaus.sonar-plugins.java" % "sonar-jacoco-listeners" % "2.1",
  "junit" % "junit" % "4.11" % "test",
  "org.apache.maven.plugins" % "maven-surefire-plugin" % "2.13",
  "org.sonarsource.java" % "sonar-jacoco-listeners" % "3.8" % "test",
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc41",
  "org.avaje.ebean" % "ebean" % "9.5.1" % "provided",
  "com.typesafe.play" %% "anorm" % "2.5.1",
  "mysql" % "mysql-connector-java" % "5.1.41"
  
)
jacoco.settings
parallelExecution in jacoco.Config := false
fork in run := true

fork in run := true