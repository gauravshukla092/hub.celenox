package com.freebird.util.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.OutgoingConnection
import akka.http.scaladsl.model.HttpHeader.ParsingResult
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}
import com.freebird.util.json.JsonHelper
import com.freebird.util.logger.FreebirdLogger
import com.typesafe.sslconfig.akka.AkkaSSLConfig

import scala.concurrent.{ExecutionContextExecutor, Future}


private[http] trait WebClient extends FreebirdLogger with JsonHelper {

  def connectionFlow(host: String)(implicit system: ActorSystem, materializer: ActorMaterializer, ec: ExecutionContextExecutor)
  : Flow[HttpRequest, HttpResponse, Future[OutgoingConnection]]

  def getRequest(url: String, header: Map[String, String] = Map.empty[String, String])
                (implicit system: ActorSystem, materializer: ActorMaterializer, ec: ExecutionContextExecutor): Future[HttpResponse] = {
    info(s"GET request................. $url")

    val httpRequest = HttpRequest(GET, uri = url, headers = getHeaderData(header))
    Http().singleRequest(httpRequest)
  }

  def postRequest(url: String, body: String, header: Map[String, String] = Map.empty[String, String])
                 (implicit system: ActorSystem, materializer: ActorMaterializer, ec: ExecutionContextExecutor): Future[HttpResponse] = {

    val httpRequest = HttpRequest(POST, uri = url, entity = HttpEntity(ContentTypes.`application/json`, body), headers = getHeaderData(header))

    info(s"POST request................. $url")
    Http().singleRequest(httpRequest)
  }

  def postRequestFormData(url: String, body: Map[String, String], header: Map[String, String] = Map.empty[String, String])
                 (implicit system: ActorSystem, materializer: ActorMaterializer, ec: ExecutionContextExecutor): Future[HttpResponse] = {
    val formData = FormData(body).toEntity
    val httpRequest = HttpRequest(POST, uri = url, entity = formData, headers = getHeaderData(header))
    info(s"POST form request................. $url")
    Http().singleRequest(httpRequest)
  }

  private def getHeaderData(header: Map[String, String]) = {
    (header flatMap {
      case (k, v) => HttpHeader.parse(k,v) match {
        case ParsingResult.Ok(h, errors) => Some(h)
        case _ => None
      }
    }).toList
  }

}

trait HttpWebClient extends WebClient {

  override def connectionFlow(host: String)(implicit system: ActorSystem, materializer: ActorMaterializer, ec: ExecutionContextExecutor)
  : Flow[HttpRequest, HttpResponse, Future[OutgoingConnection]] = Http().outgoingConnection(host)
}

trait HttpsWebClient extends WebClient {

  override def connectionFlow(host: String)(implicit system: ActorSystem, materializer: ActorMaterializer, ec: ExecutionContextExecutor)
  : Flow[HttpRequest, HttpResponse, Future[OutgoingConnection]] = {
    val badSslConfig = AkkaSSLConfig().mapSettings(s => s.withLoose(s.loose.withDisableSNI(true)))
    val badCtx = Http().createClientHttpsContext(badSslConfig)

    Http().outgoingConnectionHttps(host)
  }
}
