package com.freebird.stresstest

import com.freebird.util.logger.FreebirdLogger
import io.gatling.http.Predef._
import io.gatling.core.Predef._


trait TestSetup extends FreebirdLogger {

  import com.freebird.util.config.ConfigManager._

  case class UAT(
                  apiUrl: String,
                  apiUserName: String,
                  apiPassword: String,
                  partnerAPIHost: String,
                  flightStats: String
                )

  case class Uri(
                  authenticateUri: String,
                  pricingUri: String,
                  purchaseUri: String,
                  userUri: String,
                  rebookingOppUri: String,
                  rebookingSelectionUri: String,
                  tripInfoUri: String,
                  createPartnerUri: String,
                  viewPartnerUri: String,
                  updatePartnerUri: String,
                  manualDisruptionUri: String,
                  adminSearchUri: String
                )


  val getEnv = System.getenv("APP_ENV")
  val commonHeader = Map(
    "content-Type" -> """application/json""",
    "Accept" -> """[text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8]""",
    "User-Agent" -> """[Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0]""",
    "Accept-Language" -> """[en-US,en;q=0.5]""",
    "Accept-Encoding" -> """[gzip, deflate]"""
  )

  def prodBaseUrlBuilder = http.baseURL(prod.apiUrl)

  def stgBaseUrlBuilder = http.baseURL(stg.apiUrl)

  def baseUrlBuilder = http.baseURL(ENV.apiUrl)
    .headers(commonHeader)
    .check(status.is(200))

  def baseUrl = http.baseURL(ENV.flightStats)
    .headers(commonHeader)
    .check(status.is(200))

  def partnerServiceBaseUrlBuilder = http.baseURL(ENV.partnerAPIHost)

  val CONTENT_TYPE = "Content-Type"
  val RAW_JSON = "application/json"
  val VIRTUAL_USERS = 10
  val OK = 200
  val FLIGHT_STATS_SCHEDULE = "http://52.32.106.42:9191/"

  val ENV = UAT({
    config.getString("env.uat")
  }, {
    config.getString("userName")
  }, {
    config.getString("UATpassword")
  }, {
    config.getString("env.partnerhostuat")
  }, {
    config.getString("env.flightStatsScheduleUAT")
  })

  val prod = UAT({
    config.getString("env.prod")
  }, {
    config.getString("userName")
  }, {
    config.getString("PRODpassword")
  }, {
    config.getString("env.partnerhostprod")
  }, {
    config.getString("env.flightStatsSchedulePROD")
  })

  val stg = UAT({
    config.getString("env.stg")
  }, {
    config.getString("userName")
  }, {
    config.getString("STGpassword")
  }, {
    config.getString("env.partnerhoststg")
  }, {
    config.getString("env.flightStatsScheduleSTG")
  })

  val route = Uri({
    config.getString("url.authenticate")
  }, {
    config.getString("url.pricing")
  }, {
    config.getString("url.purchase")
  }, {
    config.getString("url.createUser")
  }, {
    config.getString("url.rebooking_Opp")
  }, {
    config.getString("url.rebooking_selection")
  }, {
    config.getString("url.tripInfo")
  }, {
    config.getString("url.createPartner")
  }, {
    config.getString("url.getPartner")
  }, {
    config.getString("url.createPartner")
  }, {
    config.getString("url.manual_Disruption")
  }, {
    config.getString("url.admin_search")
  })

  val nearByBWI = List("BWI", "DCA", "IAD").sortWith(_ < _).mkString(",")
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

  def verifyRebookingOptionsOrigin(tripOrigin: String, nonStopOrigin: String, oneStopOrigin: String) {
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

  def verifyRebookingOptionsDestination(tripDestination: String, nonStopDestination: String, oneStopDestination: String) {
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




  def extractedPricingInfo(priceForTrip: Session): Unit = {
    val priceId = priceForTrip("priceRequestId").as[String]
    val tripId = priceForTrip("tripId").as[String]
    logger.info(s"Extracted Response attribute: ${priceId}, ${tripId}")
  }


  def extractedResponseForTripInfo(tripInfo: Session) = {
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
    val rebookingOpportunity = tripInfo("rebookingOpportunity").asOption[String]
    val partner = tripInfo("partner").as[String]
    logger.info(s"tripId -> ${tripId},\n status -> ${status}, \n LegId -> ${legId},\n airline -> ${airline},\n flightNumber -> ${flightNumber},\n origin -> ${origin},\n destination -> ${destination},\n arrival -> ${arrival},\n departure -> ${departure},\n arrivalLocal -> ${arrivalLocal},\n departureLocal -> ${departureLocal},\n rebookingOpportunity -> ${rebookingOpportunity},\n partnerName -> ${partner}")
    verifyNearBy(origin, destination)
  }

  def extractedSameDayRebookingOption(rebookingOption: Session) = {
    val origin = rebookingOption("origin").as[String]
    val destination = rebookingOption("destination").as[String]
    val sameDayDate = rebookingOption("sameDayDate").as[String]
    val sameDayNonStopOptionId = rebookingOption("sameDayNonStopOptionOrigin").as[List[String]].mkString(",")
    val sameDayOneStopOptionId = rebookingOption("sameDayNonStopOptionOrigin").as[List[String]].mkString(",")
    val nextDayNonStopOptionId = rebookingOption("sameDayNonStopOptionOrigin").as[List[String]].mkString(",")
    val nextDayOneStopOptionId = rebookingOption("sameDayNonStopOptionOrigin").as[List[String]].mkString(",")
    val sameDayOptionOrigin = rebookingOption("sameDayNonStopOptionOrigin").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    val sameDayOptionDestination = rebookingOption("sameDayNonStopOptionDestination").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    val sameDayOneStopFirstSegmentOptionOrigin = rebookingOption("SameDayOneStopFirstSegmentOrigin").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    val sameDayOneStopFirstSegmentOptionDestination = rebookingOption("SameDayOneStopFirstSegmentDestination").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    val sameDayOneStopOptionSecondSegmentOrigin = rebookingOption("sameDayOneStopSecondSegmentOrigin").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    val sameDayOneStopOptionSecondSegmentDestination = rebookingOption("sameDayOneStopSecondSegmentDestination").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    if (sameDayOneStopFirstSegmentOptionDestination != sameDayOneStopOptionSecondSegmentOrigin) {
      logger.info(s"\n Same Day available Rebooking options : \n Date -> ${sameDayDate}, \n Origin -> ${origin},\n destination -> ${destination}, \n sameDayOptionOrigin -> ${sameDayOptionOrigin},\n sameDayOptionDestination -> ${sameDayOptionDestination},\n sameDayOneStopFirstSegmentOptionOrigin -> ${sameDayOneStopFirstSegmentOptionOrigin},\n sameDayOneStopFirstSegmentOptionDestination -> ${sameDayOneStopFirstSegmentOptionDestination},\n sameDayOneStopOptionSecondSegmentOrigin -> ${sameDayOneStopOptionSecondSegmentOrigin},\n sameDayOneStopOptionSecondSegmentDestination -> ${sameDayOneStopOptionSecondSegmentDestination}")
    } else {
      logger.info(s"\n Same Day available Rebooking options : \n Date -> ${sameDayDate}, \nOrigin -> ${origin},\n destination -> ${destination}, \n sameDayOptionOrigin -> ${sameDayOptionOrigin},\n sameDayOptionDestination -> ${sameDayOptionDestination},\n sameDayOneStopOptionOrigin -> ${sameDayOneStopFirstSegmentOptionOrigin},\n sameDayOneStopOptionSecondLegOrigin -> ${sameDayOneStopOptionSecondSegmentOrigin},\n sameDayOneStopOptionSecondLegDestination -> ${sameDayOneStopOptionSecondSegmentDestination}")
    }
  }

  def extractedResponseForRebookingSelection(rebookingSelectedOption: Session)={
    val rebookingSelectionId = rebookingSelectedOption("RebookingSelection").as[String]
    logger.info(s"Rebooking Selection vertex Id : ${rebookingSelectionId}")
  }

  def extractedNextDayRebookingOption(rebookingOption:Session) ={
    val nextDayDate = rebookingOption("nextDayDate").as[String]
    val nextDayOptionOrigin =rebookingOption("nextDayNonStopOptionOrigin").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    val nextDayOptionDestination = rebookingOption("nextDayNonStopOptionDestination").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    val nextDayOneStopOptionOrigin = rebookingOption("nextDayOneStopOrigin").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    val nextDayOneStopOptionDestination = rebookingOption("nextDayOneStopDestination").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    val nextDayOneStopOptionSecondLegOrigin = rebookingOption("nextDayOneStopSecondLegOrigin").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    val nextDayOneStopOptionSecondLegDestination = rebookingOption("nextDayOneStopSecondLegDestination").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    if (nextDayOneStopOptionDestination != nextDayOneStopOptionSecondLegOrigin) {
      logger.info(s"\n Next Day available Rebooking options : \n Date -> ${nextDayDate},\n nextDayOptionOrigin -> ${nextDayOptionOrigin},\n nextDayOptionDestination -> ${nextDayOptionDestination},\n nextDayOneStopOptionOrigin -> ${nextDayOneStopOptionOrigin},\n nextDayOneStopOptionDestination -> ${nextDayOneStopOptionDestination},\n nextDayOneStopOptionSecondLegOrigin -> ${nextDayOneStopOptionSecondLegOrigin},\n nextDayOneStopOptionSecondLegDestination -> ${nextDayOneStopOptionSecondLegDestination}")
    } else {
      logger.info(s"\n Next Day available Rebooking options : \n Date -> ${nextDayDate},\n nextDayOptionOrigin -> ${nextDayOptionOrigin},\n nextDayOptionDestination -> ${nextDayOptionDestination},\n nextDayOneStopOptionOrigin -> ${nextDayOneStopOptionOrigin},\n nextDayOneStopOptionSecondLegOrigin -> ${nextDayOneStopOptionSecondLegOrigin},\n nextDayOneStopOptionSecondLegDestination -> ${nextDayOneStopOptionSecondLegDestination}")
    }
  }







  def extractedResponseForDirectedNearBy(rebookingInfo: Session) {
    val tripId = rebookingInfo("tripId").as[String]
    val rebookingOpp = rebookingInfo("rebookingOpportunity").as[String]
    val origin = rebookingInfo("origin").as[String]
    val destination = rebookingInfo("destination").as[String]
    val sameDayOptionOrigin = rebookingInfo("sameDayNonStopOptionOrigin").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    val sameDayOptionDestination = rebookingInfo("sameDayNonStopOptionDestination").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    val sameDayOneStopOptionOrigin = rebookingInfo("SameDayOneStopOrigin").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    val sameDayOneStopOptionDestination = rebookingInfo("sameDayOneStopSecondLegDestination").as[List[String]].distinct.sortWith(_ < _).mkString(",")
    verifyNearBy(origin, destination)
    verifyRebookingOptionsOrigin(origin, sameDayOptionOrigin, sameDayOneStopOptionOrigin)
    verifyRebookingOptionsDestination(destination, sameDayOptionDestination, sameDayOneStopOptionDestination)
  }

def verifySameDayRebookingOptionWithNearByAirport(date:String, tripScheduleDate : String): Unit ={

  if (tripScheduleDate == date) {
    logger.info("Verifying Same Day Rebooking Options")
  }




}


}



