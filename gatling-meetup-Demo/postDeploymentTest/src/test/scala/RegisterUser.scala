
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class RegisterUser extends Simulation with TestSetUp {


  val httpConfs: HttpProtocolBuilder = http
    .baseURL("http://34.214.155.246:8080")
    .disableWarmUp


  val registerUser: ScenarioBuilder = scenario("Register User")
    .feed(numbers)
    .exec(
      http("Registration Request")
        .post("/user/registration")
        .body(StringBody(RequestTemplate.RegisterRequest)).asJSON
        .check(jsonPath("$.status").is("success"))
    )

  setUp(registerUser.inject(atOnceUsers(1)).protocols(httpConfs)).assertions(
    global.responseTime.max.lt(10000),
    global.successfulRequests.percent.lt(1000)
  )
}
