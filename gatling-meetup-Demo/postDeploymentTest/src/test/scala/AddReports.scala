import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

class AddReports extends Simulation with TestSetUp {

  val addReport : ScenarioBuilder = scenario("User login")

    .exec(
      http("Login Request")
        .put("/add/reports")
        .formParamSeq(
          Seq(
            ("projectName","Celenox India"),
              ("moduleName","GuestAccount"),
                ("registrationKey","8fd92c96-c7d1-4993-b5a8-6f8747b337d3"),
                  ("organisation","celenoxIndia"),
          ))
        .bodyPart(RawFileBodyPart("file", "stats2.log")
        .transferEncoding("binary")).asMultipartForm
        .check(jsonPath("$.status").is("Success"))
    )

  setUp(addReport.inject(atOnceUsers(1)).protocols(httpConf)).assertions(
    global.responseTime.max.gt(100),
    global.successfulRequests.percent.gt(95)
  )

}
