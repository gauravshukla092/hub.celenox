package com.freebird.stresstest

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object Scenario extends TestSetup {

  val authorizationRequestBuilder: HttpRequestBuilder = http("AUTHENTICATE USER")
    .get(route.authenticateUri).basicAuth("accessToken", "access_token")
    .check(status.is(200))
    .check(jsonPath("$..message").saveAs("accessToken"))

  val outboundTripPricingRequestBuilderForOneLeg: HttpRequestBuilder = http("QUOTE PRICE FOR OUTBOUND TRIP WITH ONE SLICE ONE SEGMENT ONE LEG.")
    .post(route.pricingUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .header(CONTENT_TYPE, RAW_JSON)
    .body(StringBody(Requests.PRICING_REQUEST_FOR_1_SLICE_1_SEGMENT_1_LEG)).asJSON
    .check(status.is(200), bodyString.saveAs("pricing_response"))
    .check(jsonPath("$..code").saveAs("response_code"), jsonPath("$..id").saveAs("priceRequestId"), jsonPath("$..tripId").saveAs("tripId"))

  val outboundTripPricingRequestBuilderForTwoLegs: HttpRequestBuilder = http("QUOTE PRICE FOR OUTBOUND TRIP WITH ONE SLICE ONE SEGMENT TWO LEGS.")
    .post(route.pricingUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .header(CONTENT_TYPE, RAW_JSON)
    .body(StringBody(Requests.PRICING_REQUEST_FOR_1_SLICE_1_SEGMENT_2_LEGS)).asJSON
    .check(status.is(200), bodyString.saveAs("pricing_response"))
    .check(jsonPath("$..code").saveAs("response_code"), jsonPath("$..id").saveAs("priceRequestId"), jsonPath("$..tripId").saveAs("tripId"))

  val outboundTripPricingRequestBuilderForTwoSegmentsOneLeg: HttpRequestBuilder = http("QUOTE PRICE FOR OUTBOUND TRIP WITH ONE SLICE TWO SEGMENTS ONE LEG.")
    .post(route.pricingUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .header(CONTENT_TYPE, RAW_JSON)
    .body(StringBody(Requests.PRICING_REQUEST_FOR_1_SLICE_2_SEGMENTS_1_LEG)).asJSON
    .check(status.is(200), bodyString.saveAs("pricing_response"))
    .check(jsonPath("$..code").saveAs("response_code"), jsonPath("$..id").saveAs("priceRequestId"), jsonPath("$..tripId").saveAs("tripId"))

  val outboundTripPricingRequestBuilderForTwoSegmentsTwoLegs: HttpRequestBuilder = http("QUOTE PRICE FOR OUTBOUND TRIP WITH ONE SLICE TWO SEGMENTS TWO LEGS.")
    .post(route.pricingUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .header(CONTENT_TYPE, RAW_JSON)
    .body(StringBody(Requests.PRICING_REQUEST_FOR_1_SLICE_2_SEGMENTS_2_LEGS)).asJSON
    .check(status.is(200), bodyString.saveAs("pricing_response"))
    .check(jsonPath("$..code").saveAs("response_code"), jsonPath("$..id").saveAs("priceRequestId"), jsonPath("$..tripId").saveAs("tripId"))

  val returnTripPricingRequestBuilderForOneLegInEachSegment: HttpRequestBuilder = http("QUOTE PRICE FOR RETURN TRIP WITH TWO SLICE ONE SEGMENT ONE LEG.")
    .post(route.pricingUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .header(CONTENT_TYPE, RAW_JSON)
    .body(StringBody(Requests.PRICING_REQUEST_FOR_2_SLICE_1_SEGMENT_1_LEG)).asJSON
    .check(status.is(200), bodyString.saveAs("pricing_response"))
    .check(jsonPath("$..code").saveAs("response_code"), jsonPath("$..id").saveAs("priceRequestId"), jsonPath("$..tripId").saveAs("tripId"))

  val returnTripPricingRequestBuilderForTwoLegsInEachSegment: HttpRequestBuilder = http("QUOTE PRICE FOR RETURN TRIP WITH TWO SLICE ONE SEGMENT TWO LEG.")
    .post(route.pricingUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .header(CONTENT_TYPE, RAW_JSON)
    .body(StringBody(Requests.PRICING_REQUEST_FOR_2_SLICE_1_SEGMENT_2_LEGS)).asJSON
    .check(status.is(200), bodyString.saveAs("pricing_response"))
    .check(jsonPath("$..code").saveAs("response_code"), jsonPath("$..id").saveAs("priceRequestId"), jsonPath("$..tripId").saveAs("tripId"))

  val returnTripPricingRequestBuilderForTwoSegmentsOneLeg: HttpRequestBuilder = http("QUOTE PRICE FOR RETURN TRIP WITH TWO SLICE TWO SEGMENTS ONE LEG.")
    .post(route.pricingUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .header(CONTENT_TYPE, RAW_JSON)
    .body(StringBody(Requests.PRICING_REQUEST_FOR_2_SLICE_2_SEGMENTS_1_LEG)).asJSON
    .check(status.is(200), bodyString.saveAs("pricing_response"))
    .check(jsonPath("$..code").saveAs("response_code"), jsonPath("$..id").saveAs("priceRequestId"), jsonPath("$..tripId").saveAs("tripId"))

  val returnTripPricingRequestBuilderForTwoSegmentsTwoLegs: HttpRequestBuilder = http("QUOTE PRICE FOR RETURN TRIP WITH TWO SLICE TWO SEGMENTS TWO LEGS.")
    .post(route.pricingUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .header(CONTENT_TYPE, RAW_JSON)
    .body(StringBody(Requests.PRICING_REQUEST_FOR_2_SLICE_2_SEGMENTS_2_LEGS)).asJSON
    .check(status.is(200), bodyString.saveAs("pricing_response"))
    .check(jsonPath("$..code").saveAs("response_code"), jsonPath("$..id").saveAs("priceRequestId"), jsonPath("$..tripId").saveAs("tripId"))

  val purchaseRequestBuilder: HttpRequestBuilder = http("PURCHASE A TRIP FOR QUOTED PRICE.")
    .post(route.purchaseUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .header(CONTENT_TYPE, RAW_JSON)
    .body(StringBody(Requests.PURCHASE_REQUEST))
    .check(status.is(200))

  val getTripInfo: HttpRequestBuilder = http("FETCH TRIP INFO OF MY PURCHASED TRIP.")
    .get(route.tripInfoUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .check(status.is(200))
    .check(jsonPath("$.data.tripID").saveAs("tripId"))
    .check(jsonPath("$.data.status").saveAs("status"))
    .check(jsonPath("$.data.partner.partnerName").saveAs("partner"))
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

  val getRebookingOpportunity: HttpRequestBuilder = http("FETCH REBOOKING OPPORTUNITY FROM FREEBIRD.")
    .get(route.tripInfoUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .check(jsonPath("$..rebookingToken").saveAs("rebookingOpportunity"))

  val getRebookingOptions: HttpRequestBuilder = http("FETCH THE AVAILABLE REBOOKING OPTIONS FOR MY TRIP.")
    .get(route.rebookingOppUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .check(jsonPath("$.data.origin").saveAs("trip_origin"))
    .check(jsonPath("$.data.destination").saveAs("trip_destination"))
      .check(jsonPath("$.data.sameDay.date").saveAs("sameDayDate"))
    .check(jsonPath("$.data.nextDay.date").saveAs("nextDayDate"))
    .check(jsonPath("$.data.sameDay.nonStop.slices[*].id").findAll.saveAs("sameDayNonStopOptionsOptionId"))
    .check(jsonPath("$.data.sameDay.oneStop.slices[*].id").findAll.saveAs("sameDayOneStopOptionsOptionId"))
    .check(jsonPath("$.data.nextDay.nonStop.slices[*].id").findAll.saveAs("nextDayNonStopOptionsOptionId"))
    .check(jsonPath("$.data.nextDay.oneStop.slices[*].id").findAll.saveAs("nextDayNonStopOptionsOptionId"))
    .check(jsonPath("$.data.sameDay.nonStop.slices[*].segments[*].legs[*].origin").findAll.saveAs("sameDayNonStopOptionOrigin"))
    .check(jsonPath("$.data.sameDay.nonStop.slices[*].segments[*].legs[*].destination").findAll.saveAs("sameDayNonStopOptionDestination"))
    .check(jsonPath("$.data.sameDay.oneStop.slices[*].segments[0].legs[*].origin").findAll.saveAs("SameDayOneStopFirstSegmentOrigin"))
    .check(jsonPath("$.data.sameDay.oneStop.slices[*].segments[0].legs[*].destination").findAll.saveAs("SameDayOneStopFirstSegmentDestination"))
    .check(jsonPath("$.data.sameDay.oneStop.slices[*].segments[1].legs[*].origin").findAll.saveAs("sameDayOneStopSecondSegmentOrigin"))
    .check(jsonPath("$.data.sameDay.oneStop.slices[*].segments[1].legs[*].destination").findAll.saveAs("sameDayOneStopSecondSegmentDestination"))
    .check(jsonPath("$.data.nextDay.nonStop.slices[*].segments[*].legs[*].origin").findAll.saveAs("nextDayNonStopOptionOrigin"))
    .check(jsonPath("$.data.nextDay.nonStop.slices[*].segments[*].legs[*].destination").findAll.saveAs("nextDayNonStopOptionDestination"))
    .check(jsonPath("$.data.nextDay.oneStop.slices[*].segments[0].legs[*].origin").findAll.saveAs("nextDayOneStopFirstSegmentOrigin"))
    .check(jsonPath("$.data.nextDay.oneStop.slices[*].segments[0].legs[*].destination").findAll.saveAs("nextDayOneStopFirstSegmentDestination"))
    .check(jsonPath("$.data.nextDay.oneStop.slices[*].segments[1].legs[*].origin").findAll.saveAs("nextDayOneStopSecondSegmentOrigin"))
    .check(jsonPath("$.data.nextDay.oneStop.slices[*].segments[1].legs[*].destination").findAll.saveAs("nextDayOneStopSecondSegmentDestination"))


  val rebookingOptionSelectionBuilder: HttpRequestBuilder = http("SELECT A OPTION FROM AVAILABLE OPTIONS.")
    .post(route.rebookingSelectionUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .header(CONTENT_TYPE, RAW_JSON)
    .body(StringBody(Requests.REBOOKING_SELECTION))
    .check(jsonPath("$..rebookingSelectionId").saveAs("RebookingSelection"))

  val disruptionRequestBuilder: HttpRequestBuilder = http("DISRUPT PURCHASED TRIP.")
    .post(route.manualDisruptionUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .header(CONTENT_TYPE, RAW_JSON)
    .body(StringBody(Requests.MANUAL_DISRUPTION))


  val reqSyncTripsBuilder: HttpRequestBuilder = http("SYNC FAILED TRIPS FROM TITAN TO RDBMS")
    .get("v1/sync/trip").basicAuth(ENV.apiUserName, ENV.apiPassword)
    .queryParam("date", "2017-05-11")
    .check(jsonPath("$..message").is("Synchronization started..."))

  val createDisruptionForPurchasedLeg: HttpRequestBuilder = http("CREATE DISRUPTION FOR PURCHASED TRIP.")
    .post("/v1/flight/alert/received").basicAuth(ENV.apiUserName, ENV.apiPassword)
    .header(CONTENT_TYPE, RAW_JSON)
    .body(StringBody(Requests.DISRUPTION_REQUEST))
    .check(status.is(200))

  val createPartnerRequestBuilder: HttpRequestBuilder = http("Create a Partner")
    .post(route.createPartnerUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .header(CONTENT_TYPE, RAW_JSON)
    .body(StringBody(Requests.PARTNER_REQUEST)).asJSON
    .check(status.is(200), bodyString.saveAs("partner_response"))
    .check(jsonPath("$..id").saveAs("partnerId"))

  val viewPartnerRequestBuilder: HttpRequestBuilder = http("View partner By partner_id")
    .get(route.viewPartnerUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .check(status.is(200))
    .check(jsonPath("$..id").saveAs("partnerId"), jsonPath("$..companyName").saveAs("partnerName"))

  val getTripSearchWithFirstName: HttpRequestBuilder = http("Search Trip with traveller first name")
    .get(route.adminSearchUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .queryParam("partnerName", "Knoldus Testing Partner")
      .check(jsonPath("$.data[*].tripIdentifier").findAll.saveAs("tripId"))

  val getTripSearchWithLastName : HttpRequestBuilder= http("Search Trip with traveller Last name")
    .get(route.adminSearchUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .queryParam("lastName", "Shukla")
    .check(status.is(200))

  val getTripSearchWithEmail : HttpRequestBuilder = http("Search Trip with traveller email address")
    .get(route.adminSearchUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .queryParam("email", "gaurav@knoldus.com")
    .check(status.is(200))

  val getTripSearchWithConfirmationNumber = http("Search Trip with purchase confirmation Number")
    .get(route.adminSearchUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .queryParam("purchaseConfirmationNumber", "FqU-1pU-gN1")
    .check(status.is(200))

  val getTripSearchWithDepartureLocal = http("Search Trip with Local departure date")
    .get(route.adminSearchUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .queryParam("depDateLocal", "2017-06-06")
    .check(status.is(200))

  val getTripSearchWithUTCDepartureDate = http("Search Trip with UTC departure date")
    .get(route.adminSearchUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .queryParam("tripDepDate", "2017-06-06")
    .check(status.is(200))

  val getTripSearchWithPartnerName = http("Search Trip with partner name")
    .get(route.adminSearchUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .queryParam("partnerName", "knoldus Software llp")
    .check(status.is(200))

  val getTripSearchWithPhone = http("Search Trip with phone Number")
    .get(route.adminSearchUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .queryParam("phone", "918505847053")
    .check(status.is(200))

  val getTripSearchWithPurchaseDate = http("Search Trip with trip purcahse date")
    .get(route.adminSearchUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .queryParam("purchaseDate", "2017-06-21")
    .check(status.is(200))

  val searchWithMultipleParameters = http("Search Trip with multiple query parameter")
    .get(route.adminSearchUri).basicAuth(ENV.apiUserName, ENV.apiPassword)
    .queryParamMap(Map("partnerName" -> "knoldus Testing Partner", "purchaseDate" -> "2017-07-05", "email" -> "celenoxindia@gmail.com"))
    .check(jsonPath("$.data[*].tripIdentifier").findAll.saveAs("tripId"))



}

