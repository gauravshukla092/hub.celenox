package com.freebird.stresstest

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation

class AdminSearch extends Simulation with TestSetup {

  setUp(
    getScenarioBuilder.searchTrip.inject(atOnceUsers(VIRTUAL_USERS)).protocols(baseUrlBuilder)

  )
}


