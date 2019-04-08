name := """java-play-react-seed"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava).settings(
  watchSources ++= (baseDirectory.value / "public/ui" ** "*").get
)

scalaVersion := "2.12.2"

libraryDependencies += guice

libraryDependencies += "io.ebean" % "ebean" % "11.36.1"
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.2"
libraryDependencies += "com.konghq" % "unirest-java" % "2.1.02"
libraryDependencies += "com.google.code.gson" % "gson" % "2.8.5"

// Testing libraries for dealing with CompletionStage...
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test

libraryDependencies += "io.swagger" %% "swagger-play2" % "1.6.0"

// Make verbose tests
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))

lazy val myProject = (project in file("."))
  .enablePlugins(PlayJava, PlayScala, PlayEbean)