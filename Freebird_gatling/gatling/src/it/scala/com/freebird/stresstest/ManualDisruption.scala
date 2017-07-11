
package com.freebird.stresstest

import com.freebird.util.logger.FreebirdLogger
import io.gatling.core.Predef._
import io.gatling.core.structure.{ScenarioBuilder}
import io.gatling.http.request.builder.HttpRequestBuilder._


class ManualDisruption extends Simulation with TestSetup with FreebirdLogger {

  setUp(
    getScenarioBuilder.createManualDisruption.inject(atOnceUsers(VIRTUAL_USERS)).protocols(baseUrlBuilder)
  )
}




