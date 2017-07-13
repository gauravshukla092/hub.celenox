//package com.freebird.util.ws
//
//import com.freebird.concurrent.ExecutionContext.IO.dbOperations
//import com.freebird.util.json.JsonHelper
//import com.freebird.util.logger.FreebirdLogger
//import play.api.libs.ws.ahc.AhcWSClient
//
//import scala.concurrent.Future
//
//
//// $COVERAGE-OFF$Disabling highlighting by default until a workaround for https://issues.scala-lang.org/browse/SI-8596 is found
//
///**
//  * Test case for this trait are in pricing-api
//  */
//trait WsHelper extends FreebirdLogger with JsonHelper {
//
//  val ws: AhcWSClient
//
//  def httpGet(uri: String, headers: Seq[(String, String)] = Nil): Future[String] = {
//    ws.url(uri).withHeaders(headers: _*).get() map { resp =>
//      resp.body
//    }
//  }
//
//  def httpPost(uri: String, headers: Seq[(String, String)] = Nil, body: String): Future[String] = {
//    ws.url(uri).withHeaders(headers: _*).post(body) map { resp =>
//      resp.body
//    }
//  }
//
//}
//// $COVERAGE-ON$
