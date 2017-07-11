package com.freebird.util.mail

import java.util.Properties
import javax.mail.{Address, Message, Session}
import javax.mail.internet.{InternetAddress, MimeMessage}

import akka.http.scaladsl.model.headers.LinkParams.title
import com.freebird.util.config.ConfigManager._
import com.freebird.util.logger.FreebirdLogger

import scala.annotation.tailrec


trait MailApi extends FreebirdLogger {

  private val host = config.getString("mail.host").trim
  private val protocol = config.getString("mail.transport.protocol").trim
  private val sender = config.getString("mail.user").trim
  private val password = config.getString("mail.password").trim
  private val port = config.getString("mail.smtp.port").trim
  private val starttls = config.getString("mail.smtp.starttls.enable").trim

  def send(to: List[String], title: String, message: String): Boolean = {
    send(to, title, message, sender, password)
  }

  @tailrec
  final def send(to: List[String], title: String, message: String, emailSender: String, emailPassword: String, attempt: Int = 0): Boolean = {
      try {
        val props = new Properties
        props.put("mail.smtp.port", port)
        props.setProperty("mail.transport.protocol", protocol)
        props.setProperty("mail.smtp.starttls.enable", starttls)
        props.setProperty("mail.host", host)
        props.setProperty("mail.user", emailSender)
        props.setProperty("mail.password", emailPassword)
        props.put("mail.smtp.connectiontimeout", "10000")
        props.put("mail.smtp.timeout", "10000")

        val session = Session.getDefaultInstance(props)
        val msg = new MimeMessage(session)
        val recipientAddress: Array[Address] = (to map { recepient => new InternetAddress(recepient) }).toArray
        msg.setFrom(new InternetAddress(emailSender))
        msg.addRecipients(Message.RecipientType.TO, recipientAddress);
        msg.setSubject(title)
        msg.setContent(message, "text/html")
        val transport = session.getTransport(protocol);
        transport.connect(host, emailSender, emailPassword)
        transport.sendMessage(msg, msg.getAllRecipients)
        true
      } catch {
        case exception: Exception =>
          warn("Error in Mail sending")
          info(s"Trying to send mail again with attempt: $attempt, to to: $to and Title: $title")
          if(attempt > 2) {
            error("Error found in sending mail. Dropping mail sending, with exception", exception)
            false
          }else{
            send(to, title, message, emailSender, emailPassword, attempt + 1)
          }
      }
  }

}

