package com.freebird.util.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.OutgoingConnection
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Flow
import com.freebird.util.config.ConfigManager._
import com.freebird.util.json.JsonHelper
import com.freebird.util.logger.FreebirdLogger

import scala.concurrent.Future
import scala.util.control.NonFatal

trait FlightStatsClient extends HttpsWebClient with JsonHelper with FreebirdLogger  {
  val SUCCESS_CODE = 200
  final val flightStatsHost = config.getString("flight.stats.host")
  final val flightStatsPort = config.getString("flight.stats.port").toInt

  final val FLIGHT_STATS_SERVICE_ADDRESS = config.getString("flight.stats.service.address").trim
  val FLIGHT_STATS_BASEURL = s"$FLIGHT_STATS_SERVICE_ADDRESS/v1/flightstats/timezone/airport/"

  implicit val system = ActorSystem("flight-stats-time-region-lookup")
  implicit val dispatcher = system.dispatcher
  implicit val materializer = ActorMaterializer()

  val connectionFlow: Flow[HttpRequest, HttpResponse, Future[OutgoingConnection]] =
    Http().outgoingConnection(host = flightStatsHost, port = flightStatsPort)

  def lookUpAirportLocalTimeZone(airportCode: String): Future[Option[String]] = {

    getRequest(FLIGHT_STATS_BASEURL + airportCode).flatMap {
      response =>
        unmarshal(response).map { responseCartOpt =>
          flightLookUpResponseHandler(responseCartOpt, airportCode)
        }
    }
  }.recover {
    case NonFatal(ex) => {
      warn(s"Error found while trying to lookup airport timezone via flight stats with Reason: ${ex.getMessage} + ${FLIGHT_STATS_BASEURL + airportCode}", ex)
      None
    }
  }

  def flightLookUpResponseHandler(responseCart: Option[ResponseCartUtil], airportCode: String): Option[String]= {
    responseCart match {
      case Some(response) => {
        if(response.code == SUCCESS_CODE){
          Some(response.message)
        }else{
          warn(s"Error during airport timezone look up for airport: $airportCode via FlightStats schedule api. Response found: ${write(response)}")
          None
        }
      }
      case None => {
        warn(s"Unexpected response cart received during airport timezone look up for airport: $airportCode, response cart state: $responseCart")
        None
      }
    }
  }

  private def unmarshal(response: HttpResponse): Future[Option[ResponseCartUtil]] = {
    Unmarshal(response.entity).to[String].map {
      flightStatsResponseJSON =>
        parse(flightStatsResponseJSON).extractOpt[ResponseCartUtil]
    }
  }

}

object FlightStatsClientImpl extends FlightStatsClient

case class ResponseCartUtil(
                             code: Int,
                             message: String
                             )
