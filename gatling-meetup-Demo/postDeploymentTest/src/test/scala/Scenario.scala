import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Scenario extends TestSetUp {


  val loginAsAdmin =  http("Login Request")
    .post("/user/login")
    .body(StringBody(RequestTemplate.LoginTemplate)).asJSON
    .check(jsonPath("$..registrationKey").saveAs("accessKey"))
      .check(status.is(200))

val addReport =  http("Login Request")
  .put("/add/reports")
  .formParamSeq(
    Seq(
      ("projectName","Celenox India"),
      ("moduleName","GuestAccount"),
      ("registrationKey","${accessKey}"),
      ("organisation","celenoxIndia"),
    ))
  .bodyPart(RawFileBodyPart("file", "stats2.log")
    .transferEncoding("binary")).asMultipartForm
  .check(jsonPath("$.status").is("Success"))

}
