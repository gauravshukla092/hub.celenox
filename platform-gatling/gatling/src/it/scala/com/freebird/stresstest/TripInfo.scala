
package com.freebird.stresstest
import com.freebird.stresstest.getScenarioBuilder.tripIdFeeder
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef.{http, jsonPath, status}
import io.gatling.http.request.builder.HttpRequestBuilder


class TripInfo extends Simulation with TestSetup {

  val getTripInfo: HttpRequestBuilder = http("FETCH TRIP INFO OF MY PURCHASED TRIP.")
    .get(route.tripInfoUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .check(status.is(200))
    .check(jsonPath("$.data.tripID").saveAs("tripId"))
    .check(jsonPath("$.data.status").saveAs("status"))
    .check(jsonPath("$.data.travelers[*].firstName").saveAs("firstName"))
    .check(jsonPath("$.data.travelers[*].lastName").saveAs("lastName"))
    .check(jsonPath("$.data.travelers[*].phone").saveAs("phone"))
    .check(jsonPath("$.data.travelers[*].email").saveAs("email"))
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

  def extractedResponseForTripInformation(tripInfo: Session) = {

    val tripId = tripInfo("tripId").as[String]
    val status = tripInfo("status").as[String]
    val legId = tripInfo("legId").as[String]
    val airline = tripInfo("airlineCode").as[String]
    val flightNumber = tripInfo("flightNumber").as[String]
    val origin = tripInfo("origin").as[String]
    val destination = tripInfo("destination").as[String]
    val arrival = tripInfo("itineraryArrival").as[String]
    val departure = tripInfo("itineraryDeparture").as[String]
    val arrivalLocal = tripInfo("itineraryArrivalLocal").as[String]
    val departureLocal = tripInfo("itineraryDepartureLocal").as[String]
    logger.info(s"tripId -> ${tripId},\n status -> ${status}.\n LegId -> ${legId},\n airline -> ${airline},\n flightNumber -> ${flightNumber},\n origin -> ${origin},\n destination -> ${destination},\n arrival -> ${arrival},\n departure -> ${departure},\n arrivalLocal -> ${arrivalLocal},\n departureLocal -> ${departureLocal}\n")
    verifyNearBy(origin, destination)
    }

  def tripInfo: ScenarioBuilder =
    scenario("GET TRIP INFO")
      .feed(tripIdFeeder)
      .exec(Scenario.getTripInfo).pause(10)
      .exec(tripInfo => {
        extractedResponseForTripInformation(tripInfo)
        tripInfo
      })

  setUp(
    tripInfo.inject(atOnceUsers(1)).protocols(baseUrlBuilder)
  )
}
