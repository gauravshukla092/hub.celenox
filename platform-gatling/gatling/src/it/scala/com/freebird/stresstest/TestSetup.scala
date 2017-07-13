package com.freebird.stresstest


import com.freebird.util.logger.FreebirdLogger
import io.gatling.http.Predef._
import io.gatling.core.Predef._

trait TestSetup extends FreebirdLogger {

  import com.freebird.util.config.ConfigManager._

  def prodBaseUrlBuilder =  http.baseURL(prod.apiUrl)
  def stgBaseUrlBuilder = http.baseURL(stg.apiUrl)
  def baseUrlBuilder = http.baseURL(ENV.apiUrl)
  def baseUrl = http.baseURL(stg.flightStats)
  def partnerServiceBaseUrlBuilder = http.baseURL(ENV.partnerAPIHost)

  val CONTENT_TYPE = "Content-Type"
  val RAW_JSON = "application/json"
  val VIRTUAL_USERS = 1
  val OK = 200
  val FLIGHT_STATS_SCHEDULE = "http://52.32.106.42:9191/"

  case class UAT (
                   apiUrl : String,
                   apiUserName : String,
                   apiPassword :String,
                   partnerAPIHost : String,
                   flightStats: String
                 )

  case class Uri (
                   authenticateUri : String,
                   pricingUri: String,
                   purchaseUri : String,
                   userUri : String,
                   rebookingOppUri : String,
                   rebookingSelectionUri : String,
                   tripInfoUri : String,
                   createPartnerUri : String,
                   viewPartnerUri : String,
                   updatePartnerUri : String,
                   manualDisruptionUri: String,
                   adminSearchUri : String
                 )

  val ENV = UAT({config.getString("env.uat")}, {config.getString("userName")}, {config.getString("UATpassword")}, {config.getString("env.partnerhostuat")}, {config.getString("env.flightStatsScheduleUAT")})
  val prod = UAT({config.getString("env.prod")}, {config.getString("userName")}, {config.getString("PRODpassword")}, {config.getString("env.partnerhostprod")}, {config.getString("env.flightStatsSchedulePROD")})
  val stg = UAT({config.getString("env.stg")}, {config.getString("userName")}, {config.getString("STGpassword")}, {config.getString("env.partnerhoststg")}, {config.getString("env.flightStatsScheduleSTG")})

  val route = Uri(
    {config.getString("url.authenticate")}, {config.getString("url.pricing")}, {config.getString("url.purchase")}, {config.getString("url.createUser")},
    {config.getString("url.rebooking_Opp")}, {config.getString("url.rebooking_selection")}, {config.getString("url.tripInfo")}, {config.getString("url.createPartner")},
    {config.getString("url.getPartner")}, {config.getString("url.createPartner")}, {config.getString("url.manual_Disruption")},{config.getString("url.admin_search")}
  )

  val nearByBWI= List("BWI", "DCA", "IAD").sortWith(_ < _).mkString(",")
  val nearByEWR = List("EWR", "JFK", "LGA").sortWith(_ < _).mkString(",")
  val nearBySJC = List("SJC", "OAK", "SFO").sortWith(_ < _).mkString(",")
  val nearByORD = List("MDW", "ORD").sortWith(_ < _).mkString(",")
  val nearByDFW = List("MDW", "ORD").sortWith(_ < _).mkString(",")
  val nearByIAH = List("IAH", "HOU").sortWith(_ < _).mkString(",")
  val nearByMIA = List("FLL", "MIA").sortWith(_ < _).mkString(",")

  val nearByAirports = Map(

    "BWI" -> nearByBWI,
    "DCA" -> nearByBWI,
    "IAD" -> nearByBWI,
    "EWR" -> nearByEWR,
    "JFK" -> nearByEWR,
    "LGA" -> nearByEWR,
    "SJC" -> nearBySJC,
    "OAK" -> nearBySJC,
    "SFO" -> nearBySJC,
    "ORD" -> nearByORD,
    "MDW" -> nearByORD,
    "DFW" -> nearByDFW,
    "DAL" -> nearByDFW,
    "IAH" -> nearByIAH,
    "HOU" -> nearByIAH,
    "MIA" -> nearByMIA,
    "FLL" -> nearByMIA

  )

  def verifyNearBy(origin: String, destination: String) {

    val nearByAirportOrigin = nearByAirports.contains(origin)
    val nearByAirportDestination = nearByAirports.contains(destination)

    if (nearByAirportOrigin && nearByAirportDestination) {
      val originNearByAirport = nearByAirports(origin)
      val destinationNearByAirport = nearByAirports(destination)
      println(s"Origin and destination Both has nearBy airports ${origin} -> ${originNearByAirport}, ${destination} -> ${destinationNearByAirport}")
    } else {
      if (nearByAirports.contains(origin)) {
        val originNearBy = nearByAirports(origin)
        println(s"Origin of your trip is ${origin}, which has nearBy airports ${originNearBy}")
      } else {
        if (nearByAirports.contains(destination)) {
          val destinationNearBy = nearByAirports(destination)
          println(s"Destination of your trip ${destination}, which has nearBy Airports ${destinationNearBy}")
        } else {
          println(s"There are no nearBy airports for ${origin}, ${destination}")
        }
      }
    }
  }

  def verifyRebookingOptionsOrigin(tripOrigin: String, nonStopOrigin: String, oneStopOrigin: String){
    try {
      val expectedRebookingOptionsOrigin = nearByAirports(tripOrigin)

      if (expectedRebookingOptionsOrigin.contains(tripOrigin)) {
        if (expectedRebookingOptionsOrigin.contains(nonStopOrigin)) {
          println(s"The itinerary was schedule from ${tripOrigin}, The first Segment of available options are depart from ${nonStopOrigin} ")
          if (expectedRebookingOptionsOrigin.contains(oneStopOrigin)) {
            println(s"itinerary was schedule from ${tripOrigin}, The first Segment of available options for oneStop flight are depart from ${oneStopOrigin} ")
          } else {
            println(s"The itinerary was schedule from ${tripOrigin}, There are some Other options except ${expectedRebookingOptionsOrigin}")
          }
        } else {
          println(s"The origin of your nonstop flight should ${expectedRebookingOptionsOrigin}, It has some other options as well ${nonStopOrigin}")
        }
      } else {
        println(s"The origin of your expected rebooking options are different than schedule itinerary.")
      }
    } catch {
      case ex: Exception => println(s"Origin of your itinerary is ${tripOrigin}, which has no nearBY airport.")
    }
  }

  def verifyRebookingOptionsDestination(tripDestination: String, nonStopDestination: String, oneStopDestination: String){
    try {
      val expectedRebookingOptionsDestination = nearByAirports(tripDestination)

      if (expectedRebookingOptionsDestination.contains(tripDestination)) {

        if (expectedRebookingOptionsDestination.contains(nonStopDestination)) {
          println(s"The itinerary was scheduled to ${nonStopDestination}")
          if (expectedRebookingOptionsDestination.contains(oneStopDestination)) {
            println(s"The arrival of available options for one stop flight should be  ${oneStopDestination} ")
          } else {
            println(s"The arrival of available options for one stop flight should be ${expectedRebookingOptionsDestination}, There are some other options as well ${oneStopDestination}.")
          }
        } else {
          println(s"The arrival of your nonstop flight is different from expected arrival ${expectedRebookingOptionsDestination}, It has some other options as well ${nonStopDestination}.")
        }
      } else {
        println(s"There is No nearBy airports for ${tripDestination}")
      }
    }
    catch {
      case ex: Exception => println(s"Your destination ${tripDestination} has no nearBY airports")
    }
  }

  def extractedResponseForDirectedNearBy(rebookingInfo: Session) {
    val tripId = rebookingInfo("tripId").as[String]
    val rebookingOpp = rebookingInfo("rebookingOpportunity").as[String]
    val purchaseTripOrigin = rebookingInfo("Original_Departure_Airport").as[String]
    val purchaseTripDestination = rebookingInfo("Original_Arrival_Airport").as[String]
    logger.info(s"Disrupted segment trip info: TripId -> ${tripId}\t, RebookingOpportunity -> ${rebookingOpp}\t, TripOrigin -> ${purchaseTripOrigin}\t, TripDestination ->${purchaseTripDestination}")
    val nonStopOptionsOrigin = rebookingInfo("Non_stop_options_origins").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    val nonStopOptionsDestination = rebookingInfo("Non_stop_options_destination").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    val oneStopFirstSegmentOrigin = rebookingInfo("one_stop_first_leg_origin").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    val oneStopFirstSegmentDestination = rebookingInfo("one_stop_first_leg_destination").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    val oneStopSecondSegmentOrigin = rebookingInfo("one_stop_second_leg_origin").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    val oneStopSecondSegmentDestination = rebookingInfo("one_stop_second_leg_destination").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    verifyNearBy(purchaseTripOrigin, purchaseTripDestination)
    verifyRebookingOptionsOrigin(purchaseTripOrigin, nonStopOptionsOrigin, oneStopFirstSegmentOrigin)
    verifyRebookingOptionsDestination(purchaseTripDestination, nonStopOptionsDestination, oneStopSecondSegmentDestination)
  }

  def extractedResponseForRebookedTrip(rebookingInfo: Session){
    val tripId = rebookingInfo("tripId").as[String]
    val rebookingOpp = rebookingInfo("rebookingOpportunity").as[String]
    val purchaseTripOrigin = rebookingInfo("Original_Departure_Airport").as[String]
    val purchaseTripDestination = rebookingInfo("Original_Arrival_Airport").as[String]
    logger.info(s"Disrupted segment trip info: ${tripId}, ${rebookingOpp}, ${purchaseTripOrigin}, ${purchaseTripDestination}")
    val nonStopOptionsOrigin = rebookingInfo("Non_stop_options_origins").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    val nonStopOptionsDestination = rebookingInfo("Non_stop_options_destination").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    val oneStopFirstSegmentOrigin = rebookingInfo("one_stop_first_leg_origin").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    val oneStopFirstSegmentDestination = rebookingInfo("one_stop_first_leg_destination").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    val oneStopSecondSegmentOrigin = rebookingInfo("one_stop_second_leg_origin").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    val oneStopSecondSegmentDestination = rebookingInfo("one_stop_second_leg_destination").as[List[String]].distinct.sortWith(_ < _).mkString(",")
  }

  def extractedPricingInfo(priceForTrip: Session): Unit ={
    val priceId = priceForTrip("priceRequestId").as[String]
    val tripId = priceForTrip("tripId").as[String]
    logger.info(s"Extracted Response attribute: ${priceId}, ${tripId}")
  }




}
