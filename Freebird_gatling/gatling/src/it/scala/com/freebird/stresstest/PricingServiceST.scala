package com.freebird.stresstest
import io.gatling.core.Predef._

class PricingServiceST extends Simulation with TestSetup {

  setUp(
    getScenarioBuilder.quotePriceWithSingleSegment.inject(atOnceUsers(VIRTUAL_USERS)).protocols(baseUrlBuilder),
    getScenarioBuilder.quotePriceWithTwoSegments.inject(atOnceUsers(VIRTUAL_USERS)).protocols(baseUrlBuilder),
    getScenarioBuilder.quotePriceWithOneSegmentTwoLegs.inject(atOnceUsers(VIRTUAL_USERS)).protocols(baseUrlBuilder),
    getScenarioBuilder.quotePriceWithTwoSegmentsTwoLegs.inject(atOnceUsers(VIRTUAL_USERS)).protocols(baseUrlBuilder)
  )
}




