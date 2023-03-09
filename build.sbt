ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val `system-monitor` = (project in file(".")).aggregate(
  `front-end`, `back-end`, common.js, common.jvm
)

lazy val common = (crossProject(JVMPlatform, JSPlatform).crossType(CrossType.Pure) in file("common")).settings(
  name := "common"
).settings(
  libraryDependencies ++= Seq(
    "dev.zio" %% "zio-json" % D.Version.ZioJson, //
    "org.scalatest" %% "scalatest" % "3.2.15" % Test
  )
)

lazy val `front-end` = (project in file("front-end")).settings(
  libraryDependencies ++= Seq(
    "com.raquo" %%% "laminar" % D.Version.Laminar, // NOTE the %%% (instead of the usual %%) which will add the current Scala.js version to the artifact name.
    "io.laminext" %%% "fetch" % D.Version.Fetch,
    "dev.zio" %%% "zio-json" % D.Version.ZioJson
  ),
  scalaJSUseMainModuleInitializer := true
).enablePlugins(ScalaJSPlugin)
  .dependsOn(common.js)

lazy val `back-end` = (project in file("back-end")).settings(
  libraryDependencies ++= Seq(
    "dev.zio" %% "zio-http" % D.Version.ZioHttp,
    "com.github.oshi" % "oshi-core" % "6.4.0"
  ),
).dependsOn(common.jvm)
