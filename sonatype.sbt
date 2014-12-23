xerial.sbt.Sonatype.sonatypeSettings

organization := "com.mohiva"

description := "Authentication library for Play Framework applications that supports several authentication methods, including OAuth1, OAuth2, OpenID, Credentials or custom authentication schemes"

homepage := Some(url("http://silhouette.mohiva.com/"))

licenses := Seq("Apache License" -> url("https://github.com/mohiva/play-silhouette/blob/master/LICENSE"))

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := <scm>
  <url>git@github.com:mohiva/play-silhouette.git</url>
  <connection>scm:git:git@github.com:mohiva/play-silhouette.git</connection>
</scm>
  <developers>
    <developer>
      <id>akkie</id>
      <name>Christian Kaps</name>
      <url>http://mohiva.com</url>
    </developer>
    <developer>
      <id>fernandoacorreia</id>
      <name>Fernando Correia</name>
      <url>http://www.fernandocorreia.info/</url>
    </developer>
  </developers>
