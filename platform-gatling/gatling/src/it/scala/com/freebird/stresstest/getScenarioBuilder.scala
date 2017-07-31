package com.freebird.stresstest

import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.jdbc.Predef._


object getScenarioBuilder extends TestSetup {

  System.setProperty("gatling.core.directory.data", "/home/knoldus/Downloads/projects/thePlatform/gatling")

  val flightFeeder = csv(Requests.flight).circular

  val tripIdFeeder = csv(Requests.purchaseRequests).circular
  //  val jdbcFileFeeder = jdbcFeeder("jdbc:postgresql://fp1fy7mh5nqwaso.cfapp5cxcqxb.us-west-2.rds.amazonaws.com", "staging4DBUX3GsPa59y",  "passyXWsuarrzfZhf6", "select access_token from users where user_name = 'support+knoldussoftwarellp@getfreebird.com'")


  def authenticate: ScenarioBuilder =
    scenario("fetch accesstoken from RDBMS")
      //      .feed(jdbcFileFeeder)
      .exec(Scenario.authorizationRequestBuilder)


  def quotePriceWithSingleSegment: ScenarioBuilder =
    scenario("****************************Price For Trip with one segment and one leg")
      .exec(Scenario.outboundTripPricingRequestBuilderForOneLeg).pause(5)
      .exec(Scenario.returnTripPricingRequestBuilderForOneLegInEachSegment).pause(5)
      .exec(priceForTrip => {
        extractedPricingInfo(priceForTrip)
        priceForTrip
      })

  def quotePriceWithTwoSegments: ScenarioBuilder =
    scenario("****************************Price For Trip with two segments and one leg in each segment")
      .exec(Scenario.outboundTripPricingRequestBuilderForTwoSegmentsOneLeg).pause(5)
      .exec(Scenario.returnTripPricingRequestBuilderForTwoSegmentsOneLeg).pause(5)
      .exec(priceForTrip => {
        extractedPricingInfo(priceForTrip)
        priceForTrip
      })

  def quotePriceWithOneSegmentTwoLegs: ScenarioBuilder =
    scenario("****************************Price For Trip with one segment and two legs in each segment.")
      .exec(Scenario.outboundTripPricingRequestBuilderForTwoLegs).pause(5)
      .exec(Scenario.returnTripPricingRequestBuilderForTwoLegsInEachSegment).pause(5)
      .exec(priceForTrip => {
        extractedPricingInfo(priceForTrip)
        priceForTrip
      })

  def quotePriceWithTwoSegmentsTwoLegs: ScenarioBuilder =
    scenario("****************************Price For Trip with two segment and two legs in each segment.")
      .exec(Scenario.outboundTripPricingRequestBuilderForTwoSegmentsTwoLegs).pause(5)
      .exec(Scenario.returnTripPricingRequestBuilderForTwoSegmentsTwoLegs).pause(5)
      .exec(priceForTrip => {
        extractedPricingInfo(priceForTrip)
        priceForTrip
      })

  def purchaseOutBoundTrip: ScenarioBuilder =
    scenario("***************Purchase  Outbound Trip****************")
      .exec(Scenario.outboundTripPricingRequestBuilderForTwoSegmentsOneLeg).pause(5)
      .exec(Scenario.purchaseRequestBuilder)
      .exec(purchaseOutboundTrip => {
        val priceId = purchaseOutboundTrip("priceRequestId").as[String]
        val tripId = purchaseOutboundTrip("tripId").as[String]
        logger.info(s"Extracted Response attribute: ${priceId}, ${tripId}")
        purchaseOutboundTrip
      })

  def purchaseReturnTrip: ScenarioBuilder =
    scenario("***************Purchase Return Trip*******************")
      .exec(Scenario.returnTripPricingRequestBuilderForOneLegInEachSegment).pause(5)
      .exec(Scenario.purchaseRequestBuilder)
      .exec(purchaseReturnTrip => {
        val priceId = purchaseReturnTrip("priceRequestId").as[String]
        val tripId = purchaseReturnTrip("tripId").as[String]
        logger.info(s"Extracted Response attribute: ${priceId}, ${tripId}")
        purchaseReturnTrip
      })

  def tripInfo: ScenarioBuilder =
    scenario("GET TRIP INFO")
      .feed(tripIdFeeder)
      .exec(Scenario.getTripInfo).pause(10)
      .exec(tripInfo => {
//        extractedResponseForTripInfo(tripInfo)
        tripInfo
      })

  def createManualDisruption: ScenarioBuilder =
    scenario("***************Disrupt Purchase Trip****************")
      .exec(Scenario.outboundTripPricingRequestBuilderForOneLeg).pause(5)
      .exec(Scenario.purchaseRequestBuilder)
      .exec(Scenario.getTripInfo).pause(10)
      .exec(Scenario.disruptionRequestBuilder).pause(30)
      .exec(Scenario.getRebookingOpportunity)
      .exec(disruptedTripInfo => {
        val priceId = disruptedTripInfo("priceRequestId").as[String]
        val tripId = disruptedTripInfo("tripId").as[String]
        logger.info(s"Extracted Response attribute: ${priceId}, ${tripId}")
        disruptedTripInfo
      })

  def rebookingFlow: ScenarioBuilder =
    scenario("PURCHASE TRIP FROM FREEBIRD.")
      .exec(Scenario.outboundTripPricingRequestBuilderForOneLeg)
      .exec(Scenario.purchaseRequestBuilder).pause(2)
      .exec(Scenario.getTripInfo).pause(5)
      .exec(Scenario.disruptionRequestBuilder).pause(5)
        .tryMax(6) {
          pause(50)
            .exec(Scenario.getRebookingOpportunity)
        }.exitHereIfFailed
      .exec(Scenario.getRebookingOptions).pause(5)
      .exec(Scenario.rebookingOptionSelectionBuilder)
      .exec(rebookingInfo => {
        extractedResponseForTripInfo(rebookingInfo)
        extractedSameDayRebookingOption(rebookingInfo)
        extractedNextDayRebookingOption(rebookingInfo)
        extractedResponseForDirectedNearBy(rebookingInfo)
        extractedResponseForRebookingSelection(rebookingInfo)
        rebookingInfo
      })


  def directedNearBy: ScenarioBuilder =

    scenario("PURCHASE TRIP FROM FREEBIRD.")

      .exec(Scenario.outboundTripPricingRequestBuilderForOneLeg).pause(5)
      .exec(Scenario.purchaseRequestBuilder).pause(20)
      .exec(Scenario.getTripInfo)
      .exec(Scenario.disruptionRequestBuilder).pause(30)
      .exec(Scenario.getRebookingOpportunity).pause(5)
      .exec(Scenario.getRebookingOptions).pause(10)
      .exec(rebookingInfo => {
        extractedResponseForDirectedNearBy(rebookingInfo)
        rebookingInfo
      })

  def searchTrip: ScenarioBuilder =
    scenario("SEARCH QUERY WITH DIFFERENT PARAMETERS")
      //      .exec(Scenario.getTripSearchWithFirstName)
      .exec(Scenario.searchWithMultipleParameters)
      .exec(tripId => {
        val tripIdentifier = tripId("tripId").as[List[String]].mkString("\n")
        logger.info(s"**********************************************${tripIdentifier}")
        Requests.purchaseWrite(tripIdentifier)
        tripId
      }).pause(5)


  /* .exec(Scenario.getTripSearchWithLastName)
   .exec(Scenario.getTripSearchWithEmail)
   .exec(Scenario.getTripSearchWithPhone)
   .exec(Scenario.getTripSearchWithDepartureLocal)
   .exec(Scenario.getTripSearchWithUTCDepartureDate)
   .exec(Scenario.getTripSearchWithPartnerName)
   .exec(Scenario.getTripSearchWithConfirmationNumber)
   .exec(Scenario.getTripSearchWithPurchaseDate)
   */


  def createMyPartner: ScenarioBuilder =
    scenario("Partner Integration with freebird")
      .exec(Scenario.createPartnerRequestBuilder).pause(3)
      .exec(Scenario.viewPartnerRequestBuilder)
      .exec(partnerInfo => {
        val partnerId = partnerInfo("partnerId").as[String]
        val partnerName = partnerInfo("partnerName").as[String]
        logger.info(s"Extracted Response attribute: ${partnerId}, ${partnerName}")
        partnerInfo
      })


}
