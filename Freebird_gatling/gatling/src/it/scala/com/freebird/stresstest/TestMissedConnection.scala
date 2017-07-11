
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

  val ruleId = "487698176"
  val airline = "NK"
  val flightNumber = "761"
  val origin = "ORD"
  val destination = "MSP"
  val departureLocal = "2017-06-30 09:33"
  val departureUTC = "2017-06-30T14:33:00.000Z"
  val arrivalLocal = "2017-06-30 11:03"
  val arrivalUTC = "2017-06-30T16:03:00.000Z"

  val DISRUPTION_REQUEST =
    s"""{
  "alert":{
    "event":{
      "type":"TIME_ADJUSTMENT"
    },
    "dataSource":"ASDI",
    "dateTimeRecorded":"2017-06-29T14:50:53.096Z",
    "rule":{
      "id":"${ruleId}",
      "name":"Default",
      "carrierFsCode":"${airline}",
      "flightNumber":"${flightNumber}",
      "departureAirportFsCode":"${origin}",
      "arrivalAirportFsCode":"${destination}",
      "departure":"${departureLocal}",
      "arrival":"${arrivalLocal}",
      "ruleEvents":{
        "ruleEvent":{
          "type":"ALL_CHANGES"
        }
      },
      "nameValues":null,
      "delivery":{
        "format":"json",
        "destination":"http://54.210.133.53:9000/flightstats_endpoint"
      }
    },
    "flightStatus":{
      "flightId":"677848987",
      "carrierFsCode":"${airline}",
      "flightNumber":"${flightNumber}",
      "departureAirportFsCode":"${origin}",
      "arrivalAirportFsCode":"${destination}",
      "departureDate":{
        "dateLocal":"${departureLocal}",
        "dateUtc":"${departureUTC}"
      },
      "arrivalDate":{
        "dateLocal":"${arrivalLocal}",
        "dateUtc":"${arrivalUTC}"
      },
      "status":"C",
      "schedule":{
        "flightType":"J",
        "serviceClasses":"FY",
        "restrictions":"",
        "downlines":{
          "downline":{
            "fsCode":"ORD",
            "flightId":"677853656"
          }
        }
      },
      "operationalTimes":{
        "publishedDeparture":{
          "dateLocal":"${departureLocal}",
          "dateUtc":"${departureUTC}"
        },
        "publishedArrival":{
          "dateLocal":"${arrivalLocal}",
          "dateUtc":"${arrivalUTC}"
        },
        "scheduledGateDeparture":{
          "dateLocal":"${departureLocal}",
          "dateUtc":"${departureUTC}"
        },
        "estimatedGateDeparture":{
          "dateLocal":"${departureLocal}",
          "dateUtc":"${departureUTC}"
        },
        "flightPlanPlannedDeparture":{
          "dateLocal":"${departureLocal}",
          "dateUtc":"${departureUTC}"
        },
        "scheduledGateArrival":{
          "dateLocal":"${arrivalLocal}",
          "dateUtc":"${arrivalUTC}"
        },
        "estimatedGateArrival":{
          "dateLocal":"${arrivalLocal}",
          "dateUtc":"${arrivalUTC}"
        },
        "flightPlanPlannedArrival":{
          "dateLocal":"${arrivalLocal}",
          "dateUtc":"${arrivalUTC}"
        }
      },
      "codeshares":{
        "codeshare":[
          {
            "fsCode":"AS",
            "flightNumber":"1442",
            "relationship":"L"
          },
          {
            "fsCode":"BA",
            "flightNumber":"1649",
            "relationship":"L"
          }
        ]
      },
      "flightDurations":{
        "scheduledBlockMinutes":"107",
        "scheduledAirMinutes":"77",
        "scheduledTaxiOutMinutes":"2",
        "scheduledTaxiInMinutes":"28"
      },
      "airportResources":{
        "departureTerminal":"C",
        "departureGate":"31"
      },
      "flightEquipment":{
        "scheduledEquipmentIataCode":"319",
        "tailNumber":"N9019F"
      },
      "flightStatusUpdates":{
        "flightStatusUpdate":[
          {
            "updatedAt":{
              "dateUtc":"2016-02-27T23:57:57.099Z"
            },
            "source":"Innovata",
            "updatedTextFields":{
              "updatedTextField":[
                {
                  "field":"STS",
                  "newText":"S"
                },
                {
                  "field":"SQP",
                  "newText":"319"
                }
              ]
            },
            "updatedDateFields":{
              "updatedDateField":[
                {
                  "field":"SGA",
                  "newDateLocal":"2017-05-28T11:32:00.000",
                  "newDateUtc":"2016-03-01T16:32:00.000Z"
                },
                {
                  "field":"SGD",
                  "newDateLocal":"2017-05-28T09:32:00.000",
                  "newDateUtc":"2016-03-01T14:45:00.000Z"
                }
              ]
            }
          },
          {
            "updatedAt":{
              "dateUtc":"2016-02-29T01:17:58.371Z"
            },
            "source":"Airline Direct",
            "updatedTextFields":{
              "updatedTextField":[
                {
                  "field":"DTM",
                  "newText":"C"
                },
                {
                  "field":"DGT",
                  "newText":"31"
                }
              ]
            },
            "updatedDateFields":{
              "updatedDateField":[
                {
                  "field":"EGA",
                  "newDateLocal":"2017-05-28T11:32:00.000",
                  "newDateUtc":"2016-03-01T16:32:00.000Z"
                },
                {
                  "field":"SRA",
                  "newDateLocal":"2016-03-01T09:36:00.000",
                  "newDateUtc":"2016-03-01T16:36:00.000Z"
                },
                {
                  "field":"EGD",
                  "newDateLocal":"2017-05-28T09:32:00.000",
                  "newDateUtc":"2016-03-01T14:45:00.000Z"
                },
                {
                  "field":"SRD",
                  "newDateLocal":"2016-03-01T09:03:00.000",
                  "newDateUtc":"2016-03-01T15:03:00.000Z"
                }
              ]
            }
          },
          {
            "updatedAt":{
              "dateUtc":"2016-02-29T14:45:59.830Z"
            },
            "source":"Airline Direct",
            "updatedTextFields":{
              "updatedTextField":{
                "field":"TAL",
                "newText":"N9019F"
              }
            }
          },
          {
            "updatedAt":{
              "dateUtc":"2016-02-29T14:50:53.096Z"
            },
            "source":"ASDI",
            "updatedDateFields":{
              "updatedDateField":[
                {
                  "field":"SRA",
                  "originalDateLocal":"2016-03-01T09:36:00.000",
                  "newDateLocal":"2016-03-01T09:04:00.000",
                  "originalDateUtc":"2016-03-01T16:36:00.000Z",
                  "newDateUtc":"2016-03-01T16:04:00.000Z"
                },
                {
                  "field":"SRD",
                  "originalDateLocal":"2016-03-01T09:03:00.000",
                  "newDateLocal":"2016-03-01T08:47:00.000",
                  "originalDateUtc":"2016-03-01T15:03:00.000Z",
                  "newDateUtc":"2016-03-01T14:47:00.000Z"
                }
              ]
            }
          }
        ]
      },
      "operatingCarrierFsCode":"NK",
      "primaryCarrierFsCode":"NK"
    }
  },
  "appendix":{
    "airlines":{
      "airline":[
        {
          "fs":"NK",
          "iata":"NK",
          "icao":"AAL",
          "name":"American Airlines",
          "phoneNumber":"1-800-433-7300",
          "active":"true"
        },
        {
          "fs":"AS",
          "iata":"AS",
          "icao":"ASA",
          "name":"Alaska Airlines",
          "phoneNumber":"1-800-252-7522",
          "active":"true"
        },
        {
          "fs":"BA",
          "iata":"BA",
          "icao":"BAW",
          "name":"British Airways",
          "phoneNumber":"1-800-AIRWAYS",
          "active":"true"
        }
      ]
    },
    "airports":{
      "airport":[
        {
          "fs":"MSP",
          "iata":"MSP",
          "icao":"KELP",
          "faa":"MSP",
          "name":"El Paso International Airport",
          "street1":"6701 Convair Dr.",
          "city":"El Paso",
          "cityCode":"MSP",
          "stateCode":"TX",
          "postalCode":"79925",
          "countryCode":"US",
          "countryName":"United States",
          "regionName":"North America",
          "timeZoneRegionName":"America/Denver",
          "weatherZone":"TXZ055",
          "localTime":"2016-02-29T07:51:17.352",
          "utcOffsetHours":"-7.0",
          "latitude":"31.798949",
          "longitude":"-106.396003",
          "elevationFeet":"3958",
          "classification":"3",
          "active":"true"
        },
        {
          "fs":"ORD",
          "iata":"ORD",
          "icao":"KDFW",
          "faa":"ORD",
          "name":"Dallas/Fort Worth International Airport",
          "street1":"International Pkwy",
          "city":"Dallas",
          "cityCode":"ORD",
          "stateCode":"TX",
          "postalCode":"75261",
          "countryCode":"US",
          "countryName":"United States",
          "regionName":"North America",
          "timeZoneRegionName":"America/Chicago",
          "localTime":"2016-02-29T08:51:17.352",
          "utcOffsetHours":"-6.0",
          "latitude":"32.897462",
          "longitude":"-97.036128",
          "elevationFeet":"603",
          "classification":"1",
          "active":"true"
        }
      ]
    }
  }
}""".stripMargin


  val disruptLeg = http("Disrupt a leg for multi leg segment.")
    .post("v1/flight/alert/received").basicAuth(stg.apiUserName, stg.apiPassword)
    .body(StringBody(DISRUPTION_REQUEST)).asJSON
    .check(status.is(200))


  def createDisruptionForLeg: ScenarioBuilder = scenario("--------'CREATE DISRUPTION FOR REAL TIME FLIGHT'--------")
    .exec(disruptLeg)

  setUp(createDisruptionForLeg.inject(atOnceUsers(VIRTUAL_USERS))).protocols(baseUrl)

}


