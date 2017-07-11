package com.freebird.util

import java.sql.Timestamp

import com.freebird.util.http.FlightStatsClient
import com.freebird.util.logger.FreebirdLogger
import org.joda.time.DateTime
import org.scalatest.FunSuite
import org.scalatest.concurrent.ScalaFutures._
import org.scalatest.mockito.MockitoSugar

import scala.concurrent.Future
import org.mockito.Mockito._

class DateUtilTest extends FunSuite with FreebirdLogger with DateUtil with MockitoSugar {
  val flightStatsClient = mock[FlightStatsClient]

  when(flightStatsClient.lookUpAirportLocalTimeZone("DEL"))thenReturn(Future.successful(Some("Asia/Kolkata")))

  test("parse string into datetime(yyyy-MM-dd HH:mm)") {

    assert(getDateTime("2016-02-10 20:53").toString === "2016-02-10T20:53:00.000Z")

  }

  test("parse string into datetime(yyyy-MM-dd)") {

    assert(getDateTime("2016-02-09").toString === "2016-02-09T00:00:00.000Z")

  }

  test("parse string into datetime(yyyy-MM-dd HH:mm:ss)") {

    assert(getDateTime("2016-02-10 19:00:00").toString === "2016-02-10T19:00:00.000Z")

  }

  test("convert into airport time from utc"){
    whenReady(getAirportTimeFromUTC(new DateTime("2016-11-04T22:40:00.000Z"),"DEL")){
      res =>
        assert(res.get.toString === "2016-11-05T04:10:00.000+05:30")
    }
  }

  test("convert airport time into UTC"){
    whenReady(getUTCTimeFromLocal("2016-11-04 22:40","DEL")){
      res =>
        assert(res.get.toString === "2016-11-04T17:10:00.000Z")
    }
  }

  test("convert airport time into HH:mm format"){
    val res = getDateTimeHHmm("2016-11-04T22:40:00.000")
    assert(res.get.toString === "2016-11-04 22:40")
  }

  test("get current timestamp should be less than the time in test") {
    val timestamp = new Timestamp(System.currentTimeMillis())
    val utilTimestamp = getCurrentTimestamp
    info(timestamp + "......." + utilTimestamp)
    assert(timestamp.before(utilTimestamp) || timestamp.equals(utilTimestamp))

  }

}
