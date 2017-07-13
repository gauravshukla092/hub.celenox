package com.freebird.util.zip
import java.nio.file.Paths
import org.scalatest.WordSpec
class UnzipFileUtilTest extends WordSpec with UnzipFileUtil{

  "UnzipFileUtil" should {
    "be able to unzip any zipped file" in {
      pending
      warn("calling unzip method")
      unZip("/home/bharat/Desktop/","SIMFB.Z", "SIMFB", "/home/bharat/Desktop/")
      warn("Already called unzip method")
      assert(new java.io.File("/home/bharat/Desktop/SIMFB").exists)
    }
  }

}
