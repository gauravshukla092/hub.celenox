import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation

class AddProjectReport extends Simulation with TestSetUp {

setUp(

  getScenarioBuilder.addReportScenarioBuilder.inject(atOnceUsers(1)).protocols(httpConf)

  )
}
