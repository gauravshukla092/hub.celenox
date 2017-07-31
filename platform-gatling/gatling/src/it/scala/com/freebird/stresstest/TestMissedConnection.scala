
package com.freebird.stresstest

import com.freebird.util.logger.FreebirdLogger
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._


/**
  * Created by knoldus on 25/5/17.
  */
class TestMissedConnection extends Simulation with TestSetup with FreebirdLogger {

//  val jdbcFileFeeder = jdbcFeeder("jdbc:postgresql://fp1fy7mh5nqwaso.cfapp5cxcqxb.us-west-2.rds.amazonaws.com", "staging4DBUX3GsPa59y",  "passyXWsuarrzfZhf6", "select access_token from users where user_name = 'support+knoldussoftwarellp@getfreebird.com'")

  val ruleId = "504836547"
  val airline = "AA"
  val flightNumber = "2570"
  val origin = "ATL"
  val destination = "DFW"
  val status = "delayed"
  val departureUTC = "2017-08-13T10:40:00Z"
  val arrivalUTC = "2017-08-13T12:37:00Z"
  val newDateUTC = "2017-08-13T14:15:00Z"
  val newArrivalDateUTC = "2017-08-13T17:55:00Z"
  val departureLocal = "2017-08-13T07:00:00"
  val arrivalLocal = "2017-08-13T08:06:00"
  val newDateLocal = "2017-08-13T10:15:00Z"
  val newArrivalDateLocal = "2017-08-13T11:35:00"

  val DISRUPTION_REQUEST : String =
    s"""{"alert":{"event":{"type":"TIME_ADJUSTMENT"},"dataSource":"ASDI","dateTimeRecorded":"${departureUTC}","rule":{"id":"${ruleId}","name":"Default","carrierFsCode":"${airline}","flightNumber":"${flightNumber}","departureAirportFsCode":"${origin}","arrivalAirportFsCode":"${destination}","departure":"${departureLocal}","arrival":"${arrivalLocal}","ruleEvents":{"ruleEvent":{"type":"ALL_CHANGES"}},"nameValues":null,"delivery":{"format":"json","destination":"http://54.210.133.53:9000/flightstats_endpoint"}},"flightStatus":{"flightId":"677848987","carrierFsCode":"${airline}","flightNumber":"${flightNumber}","departureAirportFsCode":"${origin}","arrivalAirportFsCode":"${destination}","departureDate":{"dateLocal":"${departureLocal}","dateUtc":"${departureUTC}"},"arrivalDate":{"dateLocal":"${arrivalLocal}","dateUtc":"${arrivalUTC}"},"status":"${status}","schedule":{"flightType":"J","serviceClasses":"FY","restrictions":"","downlines":{"downline":{"fsCode":"${destination}","flightId":"677853656"}}},"operationalTimes":{"publishedDeparture":{"dateLocal":"${departureLocal}","dateUtc":"${departureUTC}"},"publishedArrival":{"dateLocal":"${arrivalLocal}","dateUtc":"${arrivalUTC}"},"scheduledGateDeparture":{"dateLocal":"${departureLocal}","dateUtc":"${departureUTC}"},"estimatedGateDeparture":{"dateLocal":"${departureLocal}","dateUtc":"${departureUTC}"},"flightPlanPlannedDeparture":{"dateLocal":"${departureLocal}","dateUtc":"${departureUTC}"},"scheduledGateArrival":{"dateLocal":"${arrivalLocal}","dateUtc":"${arrivalUTC}"},"estimatedGateArrival":{"dateLocal":"${arrivalLocal}","dateUtc":"${arrivalUTC}"},"flightPlanPlannedArrival":{"dateLocal":"${arrivalLocal}","dateUtc":"${arrivalUTC}"}},"flightDurations":{"scheduledBlockMinutes":"107","scheduledAirMinutes":"77","scheduledTaxiOutMinutes":"2","scheduledTaxiInMinutes":"28"},"airportResources":{"departureTerminal":"C","departureGate":"31"},"flightEquipment":{"scheduledEquipmentIataCode":"319","tailNumber":"N9019F"},"flightStatusUpdates":{"flightStatusUpdate":[{"updatedAt":{"dateUtc":"${departureUTC}"},"source":"Innovata","updatedTextFields":{"updatedTextField":[{"field":"STS","newText":"S"},{"field":"SQP","newText":"319"}]},"updatedDateFields":{"updatedDateField":[{"field":"SGA","newDateLocal":"${newDateLocal}","newDateUtc":"${newDateUTC}"},{"field":"SGD","newDateLocal":"${newArrivalDateLocal}","newDateUtc":"${newArrivalDateUTC}"}]}},{"updatedAt":{"dateUtc":"${departureUTC}"},"source":"Airline Direct","updatedTextFields":{"updatedTextField":[{"field":"DTM","newText":"C"},{"field":"DGT","newText":"31"}]},"updatedDateFields":{"updatedDateField":[{"field":"EGA","newDateLocal":"${newDateLocal}","newDateUtc":"${newDateUTC}"},{"field":"SRA","newDateLocal":"${newDateLocal}","newDateUtc":"${newDateUTC}"},{"field":"EGD","newDateLocal":"${newDateLocal}","newDateUtc":"${newDateUTC}"},{"field":"SRD","newDateLocal":"${newDateLocal}","newDateUtc":"${newDateUTC}"}]}},{"updatedAt":{"dateUtc":"2016-02-29T14:45:59.830Z"},"source":"Airline Direct","updatedTextFields":{"updatedTextField":{"field":"TAL","newText":"N9019F"}}},{"updatedAt":{"dateUtc":"2016-02-29T14:50:53.096Z"},"source":"ASDI","updatedDateFields":{"updatedDateField":[{"field":"SRA","originalDateLocal":"2017-08-16T07:00:00","newDateLocal":"${newDateLocal}","newDateUtc":"${newDateUTC}","originalDateUtc":"2017-08-16T11:00:00Z"},{"field":"SRD","originalDateLocal":"2016-03-01T09:03:00.000","newDateLocal":"${newDateLocal}","newDateUtc":"${newDateUTC}","originalDateUtc":"2016-03-01T15:03:00.000Z"}]}}]},"operatingCarrierFsCode":"NK","primaryCarrierFsCode":"NK"}},"appendix":{"airlines":{"airline":[{"fs":"NK","iata":"NK","icao":"AAL","name":"American Airlines","phoneNumber":"1-800-433-7300","active":"true"},{"fs":"AS","iata":"AS","icao":"ASA","name":"Alaska Airlines","phoneNumber":"1-800-252-7522","active":"true"},{"fs":"BA","iata":"BA","icao":"BAW","name":"British Airways","phoneNumber":"1-800-AIRWAYS","active":"true"}]},"airports":{"airport":[{"fs":"MSP","iata":"MSP","icao":"KELP","faa":"MSP","name":"El Paso International Airport","street1":"6701 Convair Dr.","city":"El Paso","cityCode":"MSP","stateCode":"TX","postalCode":"79925","countryCode":"US","countryName":"United States","regionName":"North America","timeZoneRegionName":"America/Denver","weatherZone":"TXZ055","localTime":"2016-02-29T07:51:17.352","utcOffsetHours":"-7.0","latitude":"31.798949","longitude":"-106.396003","elevationFeet":"3958","classification":"3","active":"true"},{"fs":"ORD","iata":"ORD","icao":"KDFW","faa":"ORD","name":"Dallas/Fort Worth International Airport","street1":"International Pkwy","city":"Dallas","cityCode":"ORD","stateCode":"TX","postalCode":"75261","countryCode":"US","countryName":"United States","regionName":"North America","timeZoneRegionName":"America/Chicago","localTime":"2016-02-29T08:51:17.352","utcOffsetHours":"-6.0","latitude":"32.897462","longitude":"-97.036128","elevationFeet":"603","classification":"1","active":"true"}]}}}""".stripMargin


  val disruptLeg = http("Disrupt a leg for multi leg segment.")
    .post("v1/flight/alert/received").basicAuth(ENV.apiUserName, ENV.apiPassword)
    .body(StringBody(DISRUPTION_REQUEST)).asJSON


  def createDisruptionForLeg: ScenarioBuilder = scenario("--------'CREATE DISRUPTION FOR REAL TIME FLIGHT'--------")
    .exec(disruptLeg)



  setUp(createDisruptionForLeg.inject(atOnceUsers(VIRTUAL_USERS))).protocols(baseUrl)

}


