import sbt.Keys._
import sbt._
import sbtassembly.{PathList, MergeStrategy, AssemblyKeys}
import scoverage.ScoverageKeys._
import AssemblyKeys._
import de.johoop.cpd4sbt.CopyPasteDetector._

object
BuildSettings {

  val downloadCredentials = Credentials(
    "Bintray",
    "dl.bintray.com",
    System.getenv("BINTRAY_PRIVATE_USERNAME"),
    System.getenv("BINTRAY_PRIVATE_PASSWORD"))

  val projectSettings =  Defaults.defaultSettings  ++
  Seq(
      organization                          := "com.freebird",
      scalaVersion in ThisBuild             := "2.11.8",
      version 			                        := "1.0",
//    scapegoatVersion                      := "1.1.0",
      fork in Test 			                    := true,
	    fork in IntegrationTest 			        := true,
      parallelExecution in IntegrationTest 	:= false,
      parallelExecution in Test 	          := false,
      test in assembly                      := {},
      resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
      resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases/",
      resolvers += "bintray algd" at "http://dl.bintray.com/content/algd/maven",
      resolvers += Resolver.bintrayRepo("freebird", "private"),
      resolvers += "Elasticsearch Releases" at "https://maven.elasticsearch.org/releases",
      credentials += downloadCredentials/*,
      scalacOptions += "-Ylog-classpath"*/
  ) ++ cpdSettings

  val scoverageSettings = Seq(
    coverageExcludedPackages := "<empty>;controllers.javascript*;views.*;router;Reverse.*; com.freebird.util.json; com.freebird.ingestion;" +
      "com.freebird.util; com.freebird.util.logger; com.freebird.titan.serializer; com.freebird.flightstats.rest.FlightStatsRestService;" +
      "com.freebird.directive; com.freebird.api; com.freebird.util.decrypt; com.freebird.titan.connect.TitanDB;" +
      "com.freebird.titan.connect.TitanGraphFactory;" +
      "com.freebird.pricing.services.EnsembleControllerPriceRequestClient;com.freebird.pricing.services.PricingServiceImpl;" +
      "com.freebird.flight.app.FlightMonitorApp;" +
      "com.freebird.notifier.app.NotifierApp;" +
      "com.freebird.flight.app.DisruptedPassengerFinderApp;" +
      "com.freebird.afg.app.FlightAlternateGeneratorApp;" +
      "com.freebird.afg.infrastructure.*;" +
      "com.freebird.afg.services.WebClient;" +
      "com.freebird.afg.services.WebClientImpl;" +
      "com.freebird.purchase.services.PurchaseServiceImpl; com.freebird.airport.controllers.cors; com.freebird.errors;" +
      "com.freebird.notifier.infrastructure.mail; com.freebird.producer.RebookingKafkaProducer; com.freebird.notification.services.*;" +
      "com.freebird.util.*; com.freebird.purchase.producer.*; com.freebird.manualDistruption.producer.*; com.freebird.notification.producer.*;" +
      "com.freebird.event.ingestion.*; com.freebird.spiritDisruption.app.SpiritDisruptionFinderApp;" +
      "com.freebird.d2cModels.*;com.freebird.flightAlternates.*;" +
      "com.freebird.titan.connect.*;com.freebird.kafka.*;com.freebird.flight.producer.*" +
      "com.freebird.rdbms.mapping.* com.freebird.rdbms.settings.*;com.freebird.partner.app.*;" +
      "com.freebird.titan.connect.*;com.freebird.kafka.*;com.freebird.flight.producer.*" +
      "com.freebird.bootstrap.airport.actor.BootstrapActorRefFactory",
    coverageExcludedFiles := "",
    parallelExecution in ThisBuild := false/*,
    coverageMinimum := 80,
    coverageFailOnMinimum := true,
     coverageEnabled := true*/
  )


  lazy val sbtAssemblySettings =
    mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("org", "joda", "time", "base", "BaseDateTime.class") => MergeStrategy.first
    case PathList("javax", "annotation", "Syntax.class") => MergeStrategy.first
    case PathList("javax", "annotation", "Syntax.java") => MergeStrategy.first
    case PathList("javax", "annotation", "meta", "When.class") => MergeStrategy.first
    case "META-INF/groovy-release-info.properties" => MergeStrategy.first
    case "application.conf" => MergeStrategy.concat
    case "logback.xml" => MergeStrategy.first
    case x => old(x)
  }}

  /*lazy val sbtAssemblyExcludePackage =
    assemblyShadeRules in assembly := Seq(
      ShadeRule.rename("org.joda.time.**" -> "shadeio.@1").inLibrary(elasticSearch).inProject
    )*/

  def BaseProject(name: String): Project = (
    Project(name, file(name))
      settings (projectSettings: _*)
      settings (scoverageSettings: _*)
      settings (sbtAssemblySettings: _*)
    )

}
