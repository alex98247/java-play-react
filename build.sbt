name := """java-play-react-seed"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava).settings(
  watchSources ++= (baseDirectory.value / "public/ui" ** "*").get
)

scalaVersion := "2.11.2"

libraryDependencies += guice

libraryDependencies ++= Seq("com.typesafe.play" %% "play-cache" % "2.6.13", "org.postgresql" % "postgresql" % "42.2.2")
libraryDependencies += "ws.securesocial" %% "securesocial" % "3.0-M7"

// Testing libraries for dealing with CompletionStage...
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test

// Make verbose tests
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))

lazy val myProject = (project in file("."))
  .enablePlugins(PlayJava, PlayEbean)