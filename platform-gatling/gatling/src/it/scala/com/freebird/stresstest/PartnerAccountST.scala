
package com.freebird.stresstest
import com.freebird.util.logger.FreebirdLogger
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder

class PartnerAccountST extends Simulation with FreebirdLogger with TestSetup {

  setUp(
    getScenarioBuilder.createMyPartner.inject(
      atOnceUsers(VIRTUAL_USERS)
    )
  ).protocols(partnerServiceBaseUrlBuilder)

}
