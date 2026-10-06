scalaVersion := "3.9.0"

lazy val root = rootProject
  .settings(
    name := "StudentPerformanceAnalyzer",
    libraryDependencies ++= Seq(
      "com.github.tototoshi" %% "scala-csv" % "2.0.0",
      "org.scalanlp" %% "breeze" % "2.1.0",
      "org.knowm.xchart" % "xchart" % "4.0.4"
    )
  )
