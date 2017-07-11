package com.freebird.stresstest


import com.freebird.stresstest.Scenario.{CONTENT_TYPE, ENV, RAW_JSON, route}
import com.freebird.stresstest.getScenarioBuilder.{extractedResponseForRebookedTrip, logger, tripIdFeeder}
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef.{bodyString, http, jsonPath, status}
import io.gatling.http.request.builder.HttpRequestBuilder
import io.gatling.jdbc.Predef.jdbcFeeder

/**
  * Created by knoldus on 7/7/17.
  */
class Authenticate extends Simulation with TestSetup {

val flightfeeder = jdbcFeeder("jdbc:postgresql://fpymkpwiux5qjk.cfapp5cxcqxb.us-west-2.rds.amazonaws.com:5432/freebird_bi_staging", "staging4DBUX3GsPa59y",  "passyXWsuarrzfZhf6", "select airline_code, flight_number, flight_date, origin_airport, destination_airport from flight order by id desc limit 10")

  val PRICING_REQUEST_FOR_1_SLICE_1_SEGMENT_1_LEG = """{"passengerCounts":{"adult":1,"child":0},"metaData":{"data":"abc"},"slices":[{"segments":[{"flight":{"airlineCode":"${airline_code}","flightNumber":"${flight_number}"},"cabin":"C","legs":[{"departure":"2017-07-18","origin":"${origin_airport}","destination":"${destination_airport}"}]}]}]}"""
  val outboundTripPricingRequestBuilderForOneLeg: HttpRequestBuilder = http("QUOTE PRICE FOR OUTBOUND TRIP WITH ONE SLICE ONE SEGMENT ONE LEG.")
    .post(route.pricingUri).basicAuth(stg.apiUserName, stg.apiPassword)
    .header(CONTENT_TYPE, RAW_JSON)
    .body(StringBody(PRICING_REQUEST_FOR_1_SLICE_1_SEGMENT_1_LEG)).asJSON
    .check(status.is(200), bodyString.saveAs("pricing_response"))
    .check(jsonPath("$..code").saveAs("response_code"), jsonPath("$..id").saveAs("priceRequestId"), jsonPath("$..tripId").saveAs("tripId"))

  val purchaseRequestBuilder: HttpRequestBuilder = http("PURCHASE A TRIP FOR QUOTED PRICE.")
    .post(route.purchaseUri).basicAuth(stg.apiUserName, stg.apiPassword)
    .header(CONTENT_TYPE, RAW_JSON)
    .body(StringBody(Requests.PURCHASE_REQUEST))
    .check(status.is(200))

  val getTripInfo: HttpRequestBuilder = http("FETCH TRIP INFO OF MY PURCHASED TRIP.")
    .get(route.tripInfoUri).basicAuth(stg.apiUserName, stg.apiPassword)
    .check(status.is(200))
    .check(jsonPath("$.data.status").saveAs("status"))
    .check(jsonPath("$.data.travelers[*].firstName").findAll.saveAs("firstName"))
    .check(jsonPath("$.data.travelers[*].lastName").findAll.saveAs("lastName"))
    .check(jsonPath("$.data.travelers[*].phone").findAll.saveAs("phone"))
    .check(jsonPath("$.data.travelers[*].email").findAll.saveAs("email"))
    .check(jsonPath("$.data.itinerary[*].segments[*].legs[*].legId").saveAs("legId"))
    .check(jsonPath("$.data.itinerary[*].segments[*].flight.airlineCode").saveAs("airlineCode"))
    .check(jsonPath("$.data.itinerary[*].segments[*].flight.flightNumber").saveAs("flightNumber"))
    .check(jsonPath("$.data.itinerary[*].segments[*].legs[*].origin").saveAs("origin"))
    .check(jsonPath("$.data.itinerary[*].segments[*].legs[*].destination").saveAs("destination"))
    .check(jsonPath("$.data.itinerary[*].segments[*].legs[*].arrival").saveAs("itineraryArrival"))
    .check(jsonPath("$.data.itinerary[*].segments[*].legs[*].departure").saveAs("itineraryDeparture"))
    .check(jsonPath("$.data.itinerary[*].segments[*].legs[*].arrivalLocal").saveAs("itineraryArrivalLocal"))
    .check(jsonPath("$.data.itinerary[*].segments[*].legs[*].departureLocal").saveAs("itineraryDepartureLocal"))
    .check(jsonPath("$.data.partner.partnerName").saveAs("partner"))


  def purchaseOutBoundTrip: ScenarioBuilder =
    scenario("***************Purchase  Outbound Trip****************")
      .feed(flightfeeder)
      .exec(outboundTripPricingRequestBuilderForOneLeg).pause(5)
      .exec(purchaseRequestBuilder)
      .exec(purchaseOutboundTrip => {
        val priceId = purchaseOutboundTrip("priceRequestId").as[String]
        val tripId = purchaseOutboundTrip("tripId").as[String]
        logger.info(s"Extracted Response attribute: ${priceId}, ${tripId}")
        purchaseOutboundTrip
      })

  def tripInfo: ScenarioBuilder =
    scenario("GET TRIP INFO")
      .feed(tripIdFeeder)
      .exec(getTripInfo).pause(15)
      .exec(tripInfo => {
        extractedResponseForRebookedTrip(tripInfo)
        tripInfo
      })



  def quotePriceWithSingleSegment: ScenarioBuilder =
    scenario("****************************Price For Trip with one segment and one leg")
//      .feed(jdbcFileFeeder)
      .exec(outboundTripPricingRequestBuilderForOneLeg).pause(5)
      .exec(priceForTrip => {
        extractedPricingInfo(priceForTrip)
        priceForTrip
      })
      .exec(purchaseRequestBuilder)




  setUp(
    purchaseOutBoundTrip.inject(atOnceUsers(10)).protocols(stgBaseUrlBuilder)

  )


}
