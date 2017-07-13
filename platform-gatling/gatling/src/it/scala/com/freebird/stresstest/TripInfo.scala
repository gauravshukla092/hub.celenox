
package com.freebird.stresstest

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation

class TripInfo extends Simulation with TestSetup {

  setUp(
    getScenarioBuilder.tripInfo.inject(atOnceUsers(10)).protocols(baseUrlBuilder)
  )
}

