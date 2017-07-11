package com.freebird.util.mail

import org.scalatest.FunSuite


class MailApiTest extends FunSuite with MailApi {

  //Marked as because this method is sending mail.
  test("Send csv download mail") {
    pending
    val link = "https://www.getfreebird.com"
    val userName = "Satendra kumar"
    val subject = "Finance Report CSV"
    val message = s"Dear $userName , <br><br> Your Finance report CSV has been created. Please download from <a href=${link}>here</a>."
    val result = send(List("pranjut@knoldus.com"), subject, message)
    assert(result)
  }
}
