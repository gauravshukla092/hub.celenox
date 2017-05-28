package com.amazon

import org.scalatest._


//   Created by knoldus on 28/5/17.
class downloadFilesFromS3 extends FlatSpec with TestSetup {

  val BASE_URL = "https://getfreebird.signin.aws.amazon.com/console"

  "User" should "Login to Amazon S3 Console" in {
    getBaseUrl(BASE_URL)
    loginOnAWSConsole("gaurav.shukla", "gaurav@548")
    searchAndSelectDesiredItem("S3", "oag-status")
    searchBucketForDesiredDate("2017", "5", "18")
    driver.quit()
  }


}

