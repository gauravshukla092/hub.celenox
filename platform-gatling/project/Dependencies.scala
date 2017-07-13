import sbt._

object Dependencies {

  val akkaVersion = "2.4.17"
  val akkaHttpVersion = "10.0.4"
  val mockitoVersion = "1.10.19"
  val scalaTestVersion = "3.0.1"
  val nscalaVersion = "2.6.0"
  val json4sVersion = "3.5.0"
  val gremlinVersion = "3.1.0-incubating"
  val gremlinScalaVersion = "3.1.0-incubating.2"
  val shapelessVersion = "2.3.0"
  val titanVersion = "1.1.0-SNAPSHOT"
  val elasticSearchVersion = "2.4.0"
  val freebirdRfc3339Version = "0.3.1"

  def compile(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "compile")

  def provided(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "provided")

  def test(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "test")

  def it(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "it")

  def gatling(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "gatling")

  val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion

  val akkaQuartz ="com.enragedginger" %% "akka-quartz-scheduler" % "1.5.0-akka-2.4.x"

  val akkaTestKit = "com.typesafe.akka" %% "akka-testkit" % akkaVersion

  val akkaHttp = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion

  val akkaHttpTestKit = "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion

  val akkaHttpJson4s = "de.heikoseeberger" %% "akka-http-json4s" % "1.12.0"

  val elasticSearch = "org.elasticsearch" % "elasticsearch" % elasticSearchVersion

  val elasticSearchShield = "org.elasticsearch.plugin" % "shield" % elasticSearchVersion

  val elasicSearchJNA = "net.java.dev.jna" % "jna" % "4.2.1"

  val freebirdGrpc = "com.freebird" % "grpc-java" % "0.4.0"

  val freebirdRfc3339 = "com.freebird" % "rfc3339_2.11" % "0.3.1"

  val gremlinScala = "com.michaelpollmeier" %% "gremlin-scala" % gremlinScalaVersion

  val gremlinCore = "org.apache.tinkerpop" % "gremlin-core" % gremlinVersion

  val grpcOkHttp = "io.grpc" % "grpc-okhttp" % "1.0.3"

  val json4sNative = ("org.json4s" %% "json4s-native" % json4sVersion)
    .exclude("joda-time", "joda-time")

  val json4sExt = ("org.json4s" %% "json4s-ext" % json4sVersion)
    .exclude("joda-time", "joda-time")

  val jodaDate = "joda-time" % "joda-time" % "2.9.2"

  val logback = "ch.qos.logback" % "logback-classic" % "1.1.6"

  val akkaSlf4j = "com.typesafe.akka" % "akka-slf4j_2.11" % "2.4.11"

  val mockito = "org.mockito" % "mockito-core" % mockitoVersion

  val nscalaTime = "com.github.nscala-time" %% "nscala-time" % nscalaVersion

  val saaj = "com.sun.xml.messaging.saaj" % "saaj-impl" % "1.3.25"

  val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVersion

  val shapeless = ("com.chuusai" %% "shapeless" % "2.2.5")
    .exclude("com.typesafe.akka", "*")

  val slack = ("com.github.gilbertw1" %% "slack-scala-client" % "0.1.8")
		.exclude("io.netty", "netty-common")
    .exclude("io.netty", "netty-handler")
    .exclude("io.netty", "netty-codec")
    .exclude("io.netty", "netty-codec-http")
    .exclude("io.netty", "netty-transport")
    .exclude("io.netty", "netty-buffer")
		.exclude("org.slf4j", "*")


  val swagger = "io.swagger" % "swagger-annotations" % "1.5.12"

  val jsr311 = "javax.ws.rs" % "jsr311-api" % "1.1.1"

  val typesafeConfig = "com.typesafe" % "config" % "1.3.0"

  // Breeze related dependencies

  val breeze = "org.scalanlp" %% "breeze" % "0.12"

  val breezeNatives = "org.scalanlp" %% "breeze-natives" % "0.12"

  val breezeViz = "org.scalanlp" %% "breeze-viz" % "0.12"

  // Titan related dependencies

  val tinkerGraphGremlin = "org.apache.tinkerpop" % "tinkergraph-gremlin" % gremlinVersion

  // exclude("org.slf4j", "slf4j-log4j12")
  val titanCore = ("com.thinkaurelius.titan" % "titan-core" % titanVersion)
    .exclude("org.slf4j", "slf4j-log4j12")
    .exclude("org.slf4j", "jcl-over-slf4j")
    .exclude("com.github.stephenc.high-scale-lib", "high-scale-lib")


  val titanCoreApi = ("com.thinkaurelius.titan" % "titan-core" % titanVersion)
    .exclude("org.slf4j", "slf4j-log4j12")
    .exclude("org.slf4j", "jcl-over-slf4j")

  val titanCassandra = ("com.thinkaurelius.titan" % "titan-cassandra" % titanVersion)
    .exclude("stax", "stax-api")
    .exclude("com.github.stephenc.high-scale-lib", "high-scale-lib")
    .exclude("org.slf4j", "slf4j-log4j12")


  val csvReader = "net.sf.opencsv" % "opencsv" % "2.3"

  // Performance test related dependencies

  val gatlingCore = "io.gatling" % "gatling-core" % "2.2.5"

  val gatlingHighcharts = "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.2.5"

 val gatlingTestFramework = "io.gatling" % "gatling-test-framework" % "2.2.5"

  val jbcrypt = "org.mindrot" % "jbcrypt" % "0.3m"

  val jwt = "com.jason-goodwin" % "authentikat-jwt_2.11" % "0.4.1"

  val s3WithDatabind = "com.amazonaws" % "aws-java-sdk-s3" % "1.11.7"
  val s3 =  ("com.amazonaws" % "aws-java-sdk-s3" % "1.11.7")
    .exclude("com.fasterxml.jackson.core","jackson-databind")

  val mail ="javax.mail" % "mail" % "1.4.7"

  val kafka = "org.apache.kafka" % "kafka-clients" % "0.10.0.0"

  val twilio = "com.twilio.sdk" % "twilio-java-sdk" % "6.3.0"

  //  val oAuth2scala = "io.github.algd" %% "oauth2-scala-akka-http" % "0.4.0"

  val yaml = "org.yaml" % "snakeyaml" % "1.17"
  val sentry = "com.getsentry.raven" % "raven-logback" % "8.0.2"


  val akkaSprayJson = "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion

  val slick = "com.typesafe.slick" % "slick_2.11" % "3.2.0"

  val slickHikari =  "com.typesafe.slick" % "slick-hikaricp_2.11" % "3.2.0"

  val postgresSql =  "org.postgresql" % "postgresql" % "9.4.1212"

  val h2Database =  "com.h2database" % "h2" % "1.4.194"

  val sftpClient = "com.hierynomus" % "sshj" % "0.13.0"

  val apacheCompress = "org.apache.commons" % "commons-compress" % "1.13"

  val jsonValue = "com.googlecode.json-simple" % "json-simple" % "1.1.1"

}
