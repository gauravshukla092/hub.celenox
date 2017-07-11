package com.freebird.util

import java.io.{PrintWriter, StringWriter}

import akka.actor.ActorSystem
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.ExceptionHandler
import com.freebird.util.logger.FreebirdLogger

trait ActorExceptionHandler extends FreebirdLogger{

  implicit def myExceptionHandler: ExceptionHandler = {
    ExceptionHandler {
      case ex: Exception =>
        extractUri { uri =>
          error(s"Request to $uri could not be handled normally. Stack trace ->\n ${ex.getStackTrace.mkString("\n")}")
          complete(HttpResponse(InternalServerError, entity = "There was an internal server error."))
        }
    }
  }

}
