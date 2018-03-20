import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder


trait TestSetUp {

  val httpConf: HttpProtocolBuilder = http
    .baseURL("http://34.214.155.246:8080")
    //.acceptHeader("image/png,image/*;q=0.8,*/*;q=0.5")
    //.acceptEncodingHeader("gzip, deflate")
    //.acceptLanguageHeader("en-US,en;q=0.5")
    .disableWarmUp
  //.userAgentHeader("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko)
  //("Chrome/34.0.1847.116) (Safari/537.36"))


  val feederFile = "/home/knoldus/Downloads/sample-Demo-projects/gatling-meetup-Demo/postDeploymentTest/src/test/resources/data/emailFeederFile.csv"
  val feeder = csv(feederFile).queue

  val numbers = Iterator.continually(Map("numb" -> scala.util.Random.nextInt(Int.MaxValue)))


}
