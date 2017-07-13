import Dependencies.{compile => _, test => _}
import io.gatling.sbt.{GatlingKeys, GatlingPlugin}
import sbt.Keys._
import sbt._
import sbtassembly.AssemblyKeys._

import scala.collection.JavaConverters._


object ThePlatform extends Build {

  import BuildSettings._
  import Dependencies._

  lazy val root = Project("ThePlatform", file("."))
    .settings(commands += Command.command("testUntilFailed") { state =>
      "test" :: "testUntilFailed" :: state
    })
    .settings(projectSettings: _*)
    .settings(scoverageSettings: _*)
    .settings(sbtAssemblySettings: _*)

  // ##############SERVICES #######################################

  lazy val gatling = BaseProject("gatling")
    .settings(Defaults.itSettings: _*)
    .enablePlugins(GatlingPlugin)
    .settings(libraryDependencies ++=
      it(akkaTestKit, scalaTest, akkaHttpTestKit, gatlingCore, gatlingHighcharts, gatlingTestFramework, mockito, akkaHttp))
	 .dependsOn(util)

 lazy val util = BaseProject("util")
    .settings(libraryDependencies ++=
      provided(json4sNative, json4sExt, typesafeConfig, akkaActor, logback, jbcrypt, s3WithDatabind, csvReader,
        mail, akkaHttp, akkaSlf4j, apacheCompress) ++ test(scalaTest, mockito)
    )

}
