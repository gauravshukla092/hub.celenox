package com.freebird.stresstest

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation


class DirectedNearBy extends Simulation with TestSetup {

  setUp(
    getScenarioBuilder.directedNearBy.inject(atOnceUsers(VIRTUAL_USERS)).protocols(baseUrlBuilder)
  )
}



