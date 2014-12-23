import play.PlayScala
import play.core.PlayVersion
import mohiva.sbt.Helper._

//*******************************
// Play settings
//*******************************

name := "play-silhouette"

version := "2.0-SNAPSHOT"

resolvers += Resolver.jcenterRepo

libraryDependencies ++= Seq(cache, ws,
  "org.mindrot"       % "jbcrypt"       % "0.3m",
  "com.atlassian.jwt" % "jwt-core"      % "1.2.3",
  "com.atlassian.jwt" % "jwt-api"       % "1.2.3",
  //TODO should we create a silhouette-test module to remove this optional dependency?
  "com.typesafe.play" %% "play-test"    % "2.3.7"       % "optional",
  "org.mockito"       % "mockito-core"  % "1.9.5"       % "test",
  "net.codingwell"    %% "scala-guice"  % "4.0.0-beta5" % "test",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.8"       % "test"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)

//*******************************
// Coveralls settings
//*******************************

CoverallsPlugin.coverallsSettings

//*******************************
// Test settings
//*******************************

parallelExecution in Test := false

//*******************************
// Compiler settings
//*******************************

scalaVersion := "2.11.4"

crossScalaVersions := Seq("2.10.4", "2.11.4")

scalacOptions ++= Seq(
  "-deprecation", // Emit warning and location for usages of deprecated APIs.
  "-feature", // Emit warning and location for usages of features that should be imported explicitly.
  "-unchecked", // Enable additional warnings where generated code depends on assumptions.
  "-Xfatal-warnings", // Fail the compilation if there are any warnings.
  "-Xlint", // Enable recommended additional warnings.
  "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver.
  "-Ywarn-dead-code", // Warn when dead code is identified.
  "-Ywarn-inaccessible", // Warn about inaccessible types in method signatures.
  "-Ywarn-nullary-override", // Warn when non-nullary overrides nullary, e.g. def foo() over def foo.
  "-Ywarn-numeric-widen" // Warn when numerics are widened.
)

// Allow dead code in tests (to support using mockito).
scalacOptions in Test ~= { _.filter(_ != "-Ywarn-dead-code") }

//*******************************
// ScalaDoc settings
//*******************************

autoAPIMappings := true

apiURL := Some(url(s"http://silhouette.mohiva.com/api/$version/"))

apiMappings ++= {
  implicit val cp = (fullClasspath in Compile).value
  Map (
    jarFor("com.typesafe.play", "play") -> url(s"http://www.playframework.com/documentation/${PlayVersion.current}/api/scala/"),
    scalaInstance.value.libraryJar      -> url(s"http://www.scala-lang.org/api/${scalaVersion.value}/")
  )
}
