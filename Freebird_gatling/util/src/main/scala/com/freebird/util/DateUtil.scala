package com.freebird.util

import java.sql.Timestamp

import com.freebird.util.http.{FlightStatsClient, FlightStatsClientImpl}
import com.freebird.util.logger.FreebirdLogger
import org.joda.time.format.DateTimeFormat
import org.joda.time.{DateTime, DateTimeZone, LocalDate}

import com.freebird.concurrent.ExecutionContext.IO.dbOperations
import scala.concurrent.Future

trait DateUtil extends FreebirdLogger{

  val flightStatsClient: FlightStatsClient

  implicit def dateTimeOrdering: Ordering[DateTime] = Ordering.fromLessThan(_ isBefore _)

  def dateTimeOrderingReverse: Ordering[DateTime] = Ordering.fromLessThan(_ isAfter  _)

  def getCurrentTimeInMillis: Long = System.currentTimeMillis()

  def getCurrentDateTime: DateTime = new DateTime(getCurrentTimeInMillis)

  def getCurrentLocalDate: LocalDate = new LocalDate()

  def getDateTime(dateString: String): DateTime = {
    DateTimeZone.setDefault(DateTimeZone.UTC)
    def getDateTime(dateFormats: Seq[String], dateString: String): DateTime= {
      try {
        val dtFormat = DateTimeFormat.forPattern(dateFormats.head)
        dtFormat.parseDateTime(dateString)
      } catch {
        case _ if dateFormats.nonEmpty => getDateTime(dateFormats.tail, dateString)
        case ex: Exception                =>
          throw  new IllegalArgumentException( "Unknown date format -> " + dateString ,ex)
      }
    }
    getDateTime(dateFormats, dateString)
  }

  def getLocalDate(dateString: String): LocalDate = getDateTime(dateString).toLocalDate

  private val dateFormats: Seq[String] = Seq( "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
    "yyyy-MM-dd'T'HH:mm:ssZZ", "yyyy-MM-dd'T'HH:mm:ss.SSS", "yyyy-MM-dd'T'HH:mm:ss-SSSS", "yyyy-MM-dd'T'HH:mm:ss+SSSS", "yyyy-MM-dd'T'HH:mmZ")

  def getCurrentTimestamp: Timestamp = new Timestamp(getCurrentTimeInMillis)

  def getAirportTimeZone(airportCode: String): Future[Option[String]] = flightStatsClient.lookUpAirportLocalTimeZone(airportCode)

  def getAirportTimeFromUTC(utc: DateTime, airportCode: String): Future[Option[DateTime]] = {
    getAirportTimeZone(airportCode: String).map{
      case Some(timezone) =>
        Some(getDateTime(utc.toString, DateTimeZone.UTC, DateTimeZone.forID(timezone)))
      case None => None
    }
  }

  def getUTCTimeFromLocal(local: String, airportCode: String): Future[Option[DateTime]] = {
    getAirportTimeZone(airportCode: String).map{
      case Some(timezone) =>
        Some(getDateTime(local, DateTimeZone.forID(timezone), DateTimeZone.UTC))
      case None => None
    }
  }

  def getDateTimeHHmm(dateString: String): Option[String] = {
    def getDateTime(dateFormats: Seq[String], dateString: String): Option[String] = {
      try {
        val dtf2 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm")
        val dtFormat = DateTimeFormat.forPattern(dateFormats.head)
        val mid = dtFormat.parseLocalDateTime(dateString)
        Some(dtf2.print(mid))
      } catch {
        case _ if dateFormats.nonEmpty => getDateTime(dateFormats.tail, dateString)
        case ex: Exception               =>
          warn("Unknown date format -> " + dateString ,ex)
          None
      }
    }
    getDateTime(dateFormats, dateString)
  }

  def getDateTime(dateString: String, fromDateTimeZone: DateTimeZone,toDateTimeZone: DateTimeZone): DateTime = {
    def getDateTime(dateFormats: Seq[String], dateString: String): DateTime= {
      try {
        val dtFormat = DateTimeFormat.forPattern(dateFormats.head)
        val mid = dtFormat.parseDateTime(dateString).withZoneRetainFields(fromDateTimeZone)
        new DateTime(mid).withZone(toDateTimeZone)
      } catch {
        case _ if dateFormats.nonEmpty => getDateTime(dateFormats.tail, dateString)
        case ex: Exception               =>
          throw  new IllegalArgumentException( "Unknown date format -> " + dateString ,ex)
      }
    }
    getDateTime(dateFormats, dateString)
  }

  def dateTimeToTimestamp(dateTime: DateTime): Timestamp = {
    new Timestamp(dateTime.getMillis)
  }

}

object DateUtil extends DateUtil {
  val flightStatsClient = FlightStatsClientImpl
}

object JodaDecreasing {
  implicit def dateTimeOrdering: Ordering[DateTime] = Ordering.fromLessThan(_ isAfter _)
}
