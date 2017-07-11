
package com.freebird.stresstest

import com.freebird.util.logger.FreebirdLogger
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder

class RebookingFlow extends Simulation with FreebirdLogger with TestSetup {


  System.setProperty("gatling.core.directory.data", "/home/knoldus/Downloads/projects/thePlatform/gatling")

  val flightFeeder = csv(Requests.flight).circular

  setUp(
    getScenarioBuilder.rebookingFlow.inject(atOnceUsers(VIRTUAL_USERS)).protocols(baseUrlBuilder)
  )
}




