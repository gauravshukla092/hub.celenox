package com.freebird.util.s3

import java.io.File

import org.scalatest.FunSuite


class AmazonS3ApiTest extends FunSuite with AmazonS3Api {


  test("upload file to S3") {
    pending
    val file = new File("src/test/resources/s3-upload-test.txt")
    info("File name: " + file.getName)
    val response = upload("finance-report-csv", file)
    assert(response)
  }


  test("Download from S3") {
    pending
    val response = download("finance-report-csv", "s3-test.txt", "src/test/resources/downloaded-s3-upload-test.txt")
    assert(response)
  }


}
