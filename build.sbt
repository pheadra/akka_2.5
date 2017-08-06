enablePlugins(JavaServerAppPackaging)

name := "akkaPlayground"

version := "1.0"

organization := "io.haru"


libraryDependencies ++= {
  val akkaVersion = "2.5.3"
  val akkaHttpVersion = "10.0.9"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-http"       % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json"  % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-xml"         % akkaHttpVersion,

    "com.typesafe.akka" %% "akka-slf4j"      % akkaVersion,
    "ch.qos.logback"    %  "logback-classic" % "1.2.3",

    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
    "org.scalatest"     %% "scalatest"       % "3.0.1"       % Test
  )
}
