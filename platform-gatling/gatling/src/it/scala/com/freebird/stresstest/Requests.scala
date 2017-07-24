package com.freebird.stresstest

import java.io.{File, FileWriter, PrintWriter}
import java.util.Calendar

object Requests {

  case class Itinerary(
                        airlineCode: String,
                        flightNumber: String,
                        firstLegDeparture: String,
                        firstLegOrigin: String,
                        firstLegDestination: String,
                        secondLegDeparture: String,
                        secondLegOrigin:  String,
                        secondLegDestination: String,
                        departureLocal: Option[String] = None
                      )

  case class Person(
                     firstName: String,
                     middleName: Option[String] = None,
                     lastName: String, dateOfBirth: String,
                     gender: String, email: String,
                     phone: String, notifyMe: Boolean,
                     isPassenger: Boolean,
                     isPayer: Boolean,
                     isTravelAgent: Option[Boolean] = None
                   )

  case class Partner(
                      companyName: String,
                      isQpxModeActive: Boolean,
                      supportsVIPOffering: Boolean,
                      isSabreGDSModeActive: Boolean,
                      partnerPCCCode: String,
                      notificationTypePartner: String,
                      partnerTo: String,
                      notificationTypeTravelAgent: String,
                      travelAgentTo: String
                    )

  val legIds = "legId.csv"
  val purchaseRequests = "test.csv"
  val flight = "flight.csv"
  val CABIN = "Y"
  val YES = true
  val NO = false


  val knoldusIndia = Partner("Knoldus123", true, true, false, "FCUS121", "REBOOKING_RESPONSE_FOR_PARTNER", "gaurav@knoldus.com, gauravshukla092@gmail.com", "REBOOKING_NOTIFICATION_FOR_PARTNER_TRAVEL_AGENT", "celenoxindia@gmail.com, utkarsh.celenox@gmail.com")

  val traveler1 = Person("Gaurav[Test]", Some("Kumar[Test]"), "Shukla[Test]", "1978-08-11", "Male", "gaurav+test@knoldus.com", "+918505847053", true, true, false)
  val traveler2 = Person("Anurag[Test]", None, "Shukla[Test]", "1978-08-11", "Male", "gaurav+test@knoldus.com", "+918505847053", false, true, false)
  val travelAgent = Person("Anurag", None, "Shukla", "1978-08-11", "Male", "gaurav@knoldus.com", "+918505847053", false, false, false, Some(true))

  val firstItinerary =  Itinerary("UA", "387", "2017-08-11T00:00:00Z", "ORD", "BWI", "", "", "")
  val secondItinerary = Itinerary("DL", "1634", "2017-07-28T00:00:00Z", "ATL", "CAK", "", "", "")
  val thirdItinerary = Itinerary("AA", "1885", "2017-07-28T00:00:00Z", "DFW", "CLT", "", "", "")
  val fourthItinerary = Itinerary("DL", "2488", "2017-07-28T00:00:00Z", "BOS", "SFO", "", "", "")

  val fifthItinerary = Itinerary("AA", "1885", "2017-06-30T00:00:00Z", "DFW", "CLT",  "2017-06-T00:00:00Z", "CLT", "BUF")
  val sixthItinerary = Itinerary("WN", "5010", "2017-06-18T00:00:00Z", "HOU", "BWI", "2017-06-18T00:00:00Z", "BWI", "MHT")
  val seventhItinerary = Itinerary("DL", "1478", "2017-06-30T00:00:00Z", "ATL", "PHL", "2017-06-30T00:00:00Z", "PHL", "HOU")
  val eighthItinerary = Itinerary("WN", "1289", "2017-06-18T00:00:00Z", "MHT", "ORD", "2017-06-18T00:00:00Z", "ORD", "HOU")

  val PRICING_REQUEST_FOR_1_SLICE_1_SEGMENT_1_LEG: String = s"""{"passengerCounts":{"adult":1,"child":0},"metaData":{"data":"abc"},"slices":[{"segments":[{"flight":{"airlineCode":"${firstItinerary.airlineCode}","flightNumber":"${firstItinerary.flightNumber}"},"cabin":"${CABIN}","legs":[{"departure":"${firstItinerary.firstLegDeparture}","origin":"${firstItinerary.firstLegOrigin}","destination":"${firstItinerary.firstLegDestination}"}]}]}]}"""
  val PRICING_REQUEST_FOR_1_SLICE_1_SEGMENT_2_LEGS: String = s"""{"passengerCounts":{"adult":1,"child":0},"metaData":{"data":"abc"},"slices":[{"segments":[{"flight":{"airlineCode":"${fifthItinerary.airlineCode}","flightNumber":"${fifthItinerary.flightNumber}"},"cabin":"${CABIN}","legs":[{"departure":"${fifthItinerary.firstLegDeparture}","origin":"${fifthItinerary.firstLegOrigin}","destination":"${fifthItinerary.firstLegDestination}"},{"departure":"${fifthItinerary.secondLegDeparture}","origin":"${fifthItinerary.secondLegOrigin}","destination":"${fifthItinerary.secondLegDestination}"}]}]}]}"""
  val PRICING_REQUEST_FOR_1_SLICE_2_SEGMENTS_1_LEG: String = s"""{"passengerCounts":{"adult":1,"child":0},"metaData":{"data":"abc"},"slices":[{"segments":[{"flight":{"airlineCode":"${firstItinerary.airlineCode}","flightNumber":"${firstItinerary.flightNumber}"},"cabin":"${CABIN}","legs":[{"departure":"${firstItinerary.firstLegDeparture}","origin":"${firstItinerary.firstLegOrigin}","destination":"${firstItinerary.firstLegDestination}"}]},{"flight":{"airlineCode":"${secondItinerary.airlineCode}","flightNumber":"${secondItinerary.flightNumber}"},"cabin":"${CABIN}","legs":[{"departure":"${secondItinerary.firstLegDeparture}","origin":"${secondItinerary.firstLegOrigin}","destination":"${secondItinerary.firstLegDestination}"}]}]}]}"""
  val PRICING_REQUEST_FOR_1_SLICE_2_SEGMENTS_2_LEGS: String = s"""{"passengerCounts":{"adult":1,"child":0},"metaData":{"data":"abc"},"slices":[{"segments":[{"flight":{"airlineCode":"${fifthItinerary.airlineCode}","flightNumber":"${fifthItinerary.flightNumber}"},"cabin":"${CABIN}","legs":[{"departure":"${fifthItinerary.firstLegDeparture}","origin":"${fifthItinerary.firstLegOrigin}","destination":"${fifthItinerary.firstLegDestination}"},{"departure":"${fifthItinerary.secondLegDeparture}","origin":"${fifthItinerary.secondLegOrigin}","destination":"${fifthItinerary.secondLegDestination}"}]},{"flight":{"airlineCode":"${sixthItinerary.airlineCode}","flightNumber":"${sixthItinerary.flightNumber}"},"cabin":"${CABIN}","legs":[{"departure":"${sixthItinerary.firstLegDeparture}","origin":"${sixthItinerary.firstLegOrigin}","destination":"${sixthItinerary.firstLegDestination}"},{"departure":"${sixthItinerary.secondLegDeparture}","origin":"${sixthItinerary.secondLegOrigin}","destination":"${sixthItinerary.secondLegDestination}"}]}]}]}"""
  val PRICING_REQUEST_FOR_2_SLICE_1_SEGMENT_1_LEG: String = s"""{"passengerCounts":{"adult":1,"child":0},"metaData":{"data":"abc"},"slices":[{"segments":[{"flight":{"airlineCode":"${firstItinerary.airlineCode}","flightNumber":"${firstItinerary.flightNumber}"},"cabin":"${CABIN}","legs":[{"departure":"${firstItinerary.firstLegDeparture}","origin":"${firstItinerary.firstLegOrigin}","destination":"${firstItinerary.firstLegDestination}"}]}]},{"segments":[{"flight":{"airlineCode":"${thirdItinerary.airlineCode}","flightNumber":"${thirdItinerary.flightNumber}"},"cabin":"${CABIN}","legs":[{"departure":"${thirdItinerary.firstLegDeparture}","origin":"${thirdItinerary.firstLegOrigin}","destination":"${thirdItinerary.firstLegDestination}"}]}]}]}"""
  val PRICING_REQUEST_FOR_2_SLICE_1_SEGMENT_2_LEGS: String = s"""{"passengerCounts":{"adult":1,"child":0},"metaData":{"data":"abc"},"slices":[{"segments":[{"flight":{"airlineCode":"${fifthItinerary.airlineCode}","flightNumber":"${fifthItinerary.flightNumber}"},"cabin":"${CABIN}","legs":[{"departure":"${fifthItinerary.firstLegDeparture}","origin":"${fifthItinerary.firstLegOrigin}","destination":"${fifthItinerary.firstLegDestination}"},{"departure":"${fifthItinerary.secondLegDeparture}","origin":"${fifthItinerary.secondLegOrigin}","destination":"${fifthItinerary.secondLegDestination}"}]}]},{"segments":[{"flight":{"airlineCode":"${seventhItinerary.airlineCode}","flightNumber":"${seventhItinerary.flightNumber}"},"cabin":"${CABIN}","legs":[{"departure":"${seventhItinerary.firstLegDeparture}","origin":"${seventhItinerary.firstLegOrigin}","destination":"${seventhItinerary.firstLegDestination}"},{"departure":"${seventhItinerary.secondLegDeparture}","origin":"${seventhItinerary.secondLegOrigin}","destination":"${seventhItinerary.secondLegDestination}"}]}]}]}"""
  val PRICING_REQUEST_FOR_2_SLICE_2_SEGMENTS_1_LEG: String = s"""{"passengerCounts":{"adult":1},"metaData":{"data":"abc"},"slices":[{"segments":[{"flight":{"airlineCode":"${firstItinerary.airlineCode}","flightNumber":"${firstItinerary.flightNumber}"},"cabin":"Y","legs":[{"departure":"${firstItinerary.firstLegDeparture}","origin":"${firstItinerary.firstLegOrigin}","destination":"${firstItinerary.firstLegDestination}"}]},{"duration":10,"flight":{"airlineCode":"${secondItinerary.airlineCode}","flightNumber":"${secondItinerary.flightNumber}"},"cabin":"Y","legs":[{"departure":"${secondItinerary.firstLegDeparture}","origin":"${secondItinerary.firstLegOrigin}","destination":"${secondItinerary.firstLegDestination}"}]}]},{"segments":[{"flight":{"airlineCode":"${thirdItinerary.airlineCode}","flightNumber":"${thirdItinerary.flightNumber}"},"cabin":"Y","legs":[{"departure":"${thirdItinerary.firstLegDeparture}","origin":"${thirdItinerary.firstLegOrigin}","destination":"${thirdItinerary.firstLegDestination}"}]},{"duration":10,"flight":{"airlineCode":"${fourthItinerary.airlineCode}","flightNumber":"${fourthItinerary.flightNumber}"},"cabin":"Y","legs":[{"departure":"${fourthItinerary.firstLegDeparture}","origin":"${fourthItinerary.firstLegOrigin}","destination":"${fourthItinerary.firstLegDestination}"}]}]}]}"""
  val PRICING_REQUEST_FOR_2_SLICE_2_SEGMENTS_2_LEGS: String = s"""{"passengerCounts":{"adult":1},"metaData":{"data":"abc"},"slices":[{"segments":[{"flight":{"airlineCode":"${fifthItinerary.airlineCode}","flightNumber":"${fifthItinerary.flightNumber}"},"cabin":"Y","legs":[{"departure":"${fifthItinerary.firstLegDeparture}","origin":"${fifthItinerary.firstLegOrigin}","destination":"${fifthItinerary.firstLegDestination}"},{"departure":"${fifthItinerary.secondLegDeparture}","origin":"${fifthItinerary.secondLegOrigin}","destination":"${fifthItinerary.secondLegDestination}"}]},{"duration":10,"flight":{"airlineCode":"${sixthItinerary.airlineCode}","flightNumber":"${sixthItinerary.flightNumber}"},"cabin":"Y","legs":[{"departure":"${sixthItinerary.firstLegDeparture}","origin":"${sixthItinerary.firstLegOrigin}","destination":"${sixthItinerary.firstLegDestination}"},{"departure":"${sixthItinerary.secondLegDeparture}","origin":"${sixthItinerary.secondLegOrigin}","destination":"${sixthItinerary.secondLegDestination}"}]}]},{"segments":[{"flight":{"airlineCode":"${seventhItinerary.airlineCode}","flightNumber":"${seventhItinerary.flightNumber}"},"cabin":"Y","legs":[{"departure":"${seventhItinerary.firstLegDeparture}","origin":"${seventhItinerary.firstLegOrigin}","destination":"${seventhItinerary.firstLegDestination}"},{"departure":"${seventhItinerary.secondLegDeparture}","origin":"${seventhItinerary.secondLegOrigin}","destination":"${seventhItinerary.secondLegDestination}"}]},{"duration":10,"flight":{"airlineCode":"${eighthItinerary.airlineCode}","flightNumber":"${eighthItinerary.flightNumber}"},"cabin":"Y","legs":[{"departure":"${eighthItinerary.firstLegDeparture}","origin":"${eighthItinerary.firstLegOrigin}","destination":"${eighthItinerary.firstLegDestination}"},{"departure":"${eighthItinerary.secondLegDeparture}","origin":"${eighthItinerary.secondLegOrigin}","destination":"${eighthItinerary.secondLegDestination}"}]}]}]}"""
  val PURCHASE_REQUEST: String = """{"priceRequestId":"${priceRequestId}","priceTier":"GOLD","trip":{"id":"${tripId}","personInfo":[{"gender":"male","firstName":"GAURAV","lastName":"SHUKLA","middleName":"KUMAR","dateOfBirth":"1989-03-26","phone":"+918505847053","email":"celenoxindia@gmail.com","isPayer":true,"isPassenger":true,"notifyMe":true},{"gender":"male","firstName":"Celenox","lastName":"agent","middleName":"travel","dateOfBirth":"1989-03-26","phone":"+919990544491","email":"anurag@knoldus.com","isPayer":false,"isPassenger":false,"notifyMe":false,"isTravelAgent":true}]},"pciTransactionId":"dsfsf34aswds"}"""
  val PARTNER_REQUEST: String = s"""{"companyName":"${knoldusIndia.companyName}","agreedToTerms":true,"numUsers":0,"onHold":false,"isDisabled":false,"isQpxModeActive":${knoldusIndia.isQpxModeActive},"supportsVIPOffering":${knoldusIndia.supportsVIPOffering},"isSabreGDSModeActive":${knoldusIndia.isSabreGDSModeActive},"partnerPCCCode":"${knoldusIndia.partnerPCCCode}","partnerEmailInfo":[{"notificationType":${knoldusIndia.notificationTypeTravelAgent},"partnerEmail":{"to":"${knoldusIndia.travelAgentTo}","cc":"","bcc":""}},{"notificationType":"${knoldusIndia.notificationTypePartner}","partnerEmail":{"to":"${knoldusIndia.partnerTo}","cc":"","bcc":""}}]}"""
  val MANUAL_DISRUPTION: String = """{"legId":"${legId}","disruptAll":false}"""
  val REBOOKING_SELECTION: String = """{"optionId": "${optionId}"}"""
  val DISRUPTION_REQUEST: String = """{"alert":{"event":{"type":"TIME_ADJUSTMENT"},"dataSource":"ASDI","dateTimeRecorded":"2016-02-29T14:50:53.096Z","rule":{"id":"480193377","name":"Default","carrierFsCode":"${airlineCode}","flightNumber":"${flightNumber}","departureAirportFsCode":"${origin}","arrivalAirportFsCode":"${destination}","departure":"${itineraryDepartureLocal}","arrival":"${itineraryArrivalLocal}","ruleEvents":{"ruleEvent":{"type":"ALL_CHANGES"}},"nameValues":null,"delivery":{"format":"json","destination":"http://54.210.133.53:9000/flightstats_endpoint"}},"flightStatus":{"flightId":"677848987","carrierFsCode":"${airlineCode}","flightNumber":"${flightNumber}","departureAirportFsCode":"${origin}","arrivalAirportFsCode":"${destination}","departureDate":{"dateLocal":"${itineraryDepartureLocal}","dateUtc":"${itineraryDeparture}"},"arrivalDate":{"dateLocal":"${itineraryArrivalLocal}","dateUtc":"${itineraryArrival}"},"status":"C","schedule":{"flightType":"J","serviceClasses":"FY","restrictions":"","downlines":{"downline":{"fsCode":"ORD","flightId":"677853656"}}},"operationalTimes":{"publishedDeparture":{"dateLocal":"${itineraryDepartureLocal}","dateUtc":"${itineraryDeparture}"},"publishedArrival":{"dateLocal":"${itineraryArrivalLocal}","dateUtc":"${itineraryArrival}"},"scheduledGateDeparture":{"dateLocal":"${itineraryDepartureLocal}","dateUtc":"${itineraryDeparture}"},"estimatedGateDeparture":{"dateLocal":"${itineraryDepartureLocal}","dateUtc":"${itineraryDeparture}"},"flightPlanPlannedDeparture":{"dateLocal":"${itineraryDepartureLocal}","dateUtc":"${itineraryDeparture}"},"scheduledGateArrival":{"dateLocal":"${itineraryArrivalLocal}","dateUtc":"${itineraryArrival}"},"estimatedGateArrival":{"dateLocal":"${itineraryArrivalLocal}","dateUtc":"${itineraryArrival}"},"flightPlanPlannedArrival":{"dateLocal":"${itineraryArrivalLocal}","dateUtc":"${itineraryArrival}"}},"codeshares":{"codeshare":[{"fsCode":"AS","flightNumber":"1442","relationship":"L"},{"fsCode":"BA","flightNumber":"1649","relationship":"L"}]},"flightDurations":{"scheduledBlockMinutes":"107","scheduledAirMinutes":"77","scheduledTaxiOutMinutes":"2","scheduledTaxiInMinutes":"28"},"airportResources":{"departureTerminal":"C","departureGate":"31"},"flightEquipment":{"scheduledEquipmentIataCode":"319","tailNumber":"N9019F"},"flightStatusUpdates":{"flightStatusUpdate":[{"updatedAt":{"dateUtc":"2016-02-27T23:57:57.099Z"},"source":"Innovata","updatedTextFields":{"updatedTextField":[{"field":"STS","newText":"S"},{"field":"SQP","newText":"319"}]},"updatedDateFields":{"updatedDateField":[{"field":"SGA","newDateLocal":"2017-05-28T11:32:00.000","newDateUtc":"2016-03-01T16:32:00.000Z"},{"field":"SGD","newDateLocal":"2017-05-28T09:32:00.000","newDateUtc":"2016-03-01T14:45:00.000Z"}]}},{"updatedAt":{"dateUtc":"2016-02-29T01:17:58.371Z"},"source":"Airline Direct","updatedTextFields":{"updatedTextField":[{"field":"DTM","newText":"C"},{"field":"DGT","newText":"31"}]},"updatedDateFields":{"updatedDateField":[{"field":"EGA","newDateLocal":"2017-05-28T11:32:00.000","newDateUtc":"2016-03-01T16:32:00.000Z"},{"field":"SRA","newDateLocal":"2016-03-01T09:36:00.000","newDateUtc":"2016-03-01T16:36:00.000Z"},{"field":"EGD","newDateLocal":"2017-05-28T09:32:00.000","newDateUtc":"2016-03-01T14:45:00.000Z"},{"field":"SRD","newDateLocal":"2016-03-01T09:03:00.000","newDateUtc":"2016-03-01T15:03:00.000Z"}]}},{"updatedAt":{"dateUtc":"2016-02-29T14:45:59.830Z"},"source":"Airline Direct","updatedTextFields":{"updatedTextField":{"field":"TAL","newText":"N9019F"}}},{"updatedAt":{"dateUtc":"2016-02-29T14:50:53.096Z"},"source":"ASDI","updatedDateFields":{"updatedDateField":[{"field":"SRA","originalDateLocal":"2016-03-01T09:36:00.000","newDateLocal":"2016-03-01T09:04:00.000","originalDateUtc":"2016-03-01T16:36:00.000Z","newDateUtc":"2016-03-01T16:04:00.000Z"},{"field":"SRD","originalDateLocal":"2016-03-01T09:03:00.000","newDateLocal":"2016-03-01T08:47:00.000","originalDateUtc":"2016-03-01T15:03:00.000Z","newDateUtc":"2016-03-01T14:47:00.000Z"}]}}]},"operatingCarrierFsCode":"NK","primaryCarrierFsCode":"NK"}},"appendix":{"airlines":{"airline":[{"fs":"NK","iata":"NK","icao":"AAL","name":"American Airlines","phoneNumber":"1-800-433-7300","active":"true"},{"fs":"AS","iata":"AS","icao":"ASA","name":"Alaska Airlines","phoneNumber":"1-800-252-7522","active":"true"},{"fs":"BA","iata":"BA","icao":"BAW","name":"British Airways","phoneNumber":"1-800-AIRWAYS","active":"true"}]},"airports":{"airport":[{"fs":"MSP","iata":"MSP","icao":"KELP","faa":"MSP","name":"El Paso International Airport","street1":"6701 Convair Dr.","city":"El Paso","cityCode":"MSP","stateCode":"TX","postalCode":"79925","countryCode":"US","countryName":"United States","regionName":"North America","timeZoneRegionName":"America/Denver","weatherZone":"TXZ055","localTime":"2016-02-29T07:51:17.352","utcOffsetHours":"-7.0","latitude":"31.798949","longitude":"-106.396003","elevationFeet":"3958","classification":"3","active":"true"},{"fs":"ORD","iata":"ORD","icao":"KDFW","faa":"ORD","name":"Dallas/Fort Worth International Airport","street1":"International Pkwy","city":"Dallas","cityCode":"ORD","stateCode":"TX","postalCode":"75261","countryCode":"US","countryName":"United States","regionName":"North America","timeZoneRegionName":"America/Chicago","localTime":"2016-02-29T08:51:17.352","utcOffsetHours":"-6.0","latitude":"32.897462","longitude":"-97.036128","elevationFeet":"603","classification":"1","active":"true"}]}}}""".stripMargin

  def removePurchseData(): Unit = {
    val purchaseFile = new File("test.csv")
    try {
      purchaseFile.delete()
    } catch {
      case ex: Exception => println("Oops....")
    }
  }

  def purchaseWrite(purchaseJson: String): Unit = {
    val fileAlreadyExist = new File(purchaseRequests).exists()
    try {
      val purchaseWriter = new PrintWriter(new FileWriter(purchaseRequests, true))
      if (fileAlreadyExist == false) {
        purchaseWriter.append(purchaseJson + "\n")
        purchaseWriter.close()
      } else {
        purchaseWriter.append(purchaseJson + "\n")
        purchaseWriter.close()
      }
    } catch {
      case ex: Exception => println("oops...")
    }
  }



  def legIdWriter(legId: String): Unit = {
    val fileAlreadyExist = new File(legIds).exists()
    try {
      val legIdWriter = new PrintWriter(new FileWriter(legIds, true))
      if (fileAlreadyExist == false) {
        legIdWriter.write("legId" + "\n")
        legIdWriter.append(legId + "\n")
        legIdWriter.close()
      } else {
        legIdWriter.append(legId + "\n")
        legIdWriter.close()
      }
    } catch {
      case ex: Exception => println("oops...")
    }
  }

}
