package com.freebird.stresstest

import io.gatling.core.Predef._

class PurchaseServiceST extends Simulation  with TestSetup {

  setUp(
    getScenarioBuilder.purchaseOutBoundTrip.inject(atOnceUsers(VIRTUAL_USERS)).protocols(baseUrlBuilder),
    getScenarioBuilder.purchaseReturnTrip.inject(atOnceUsers(VIRTUAL_USERS)).protocols(baseUrlBuilder)
    //TODO: Before uncomment return trip scenario, please verify that both scenario should be comma separated.
  )
}