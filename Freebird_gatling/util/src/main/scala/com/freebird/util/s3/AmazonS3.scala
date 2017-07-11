package com.freebird.util.s3

import java.io.{File, InputStream}

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.{GeneratePresignedUrlRequest, GetObjectRequest, ObjectMetadata}
import com.amazonaws.services.s3.transfer.TransferManager
import com.amazonaws.{ClientConfiguration, HttpMethod}
import com.freebird.util.logger.FreebirdLogger

import scala.util.control.NonFatal




trait AmazonS3 {

  def upload(bucket: String, file: File): Boolean

  def download(bucket: String, fileName: String, downloadTo: String): Boolean

  def uploadStream(bucket: String, fileName:String,stream:  InputStream,contentLength:Long): Boolean

  def getS3Url(bucket: String, key: String,days:Int=15): String
}

trait AmazonS3Api extends AmazonS3 with FreebirdLogger {

  /** The amount of time to wait (in milliseconds) when initially establishing a
    * connection before giving up and timing out.
    */
  private val timeout = 600

  private val config = new ClientConfiguration()
  config.setConnectionTimeout(timeout)
  config.setMaxErrorRetry(5)


  private val amazonS3Client: AmazonS3Client = new AmazonS3Client(new DefaultAWSCredentialsProviderChain, config)

  def download(bucket: String, fileName: String, downloadTo: String): Boolean = {
    val transferManager = new TransferManager(amazonS3Client)
    try {
      val downloadedFile = new File(downloadTo)
      val downloadObject = transferManager.download(new GetObjectRequest(bucket, fileName), downloadedFile)
      downloadObject.waitForCompletion
      true
    } catch {
      case NonFatal(ex: Exception) =>
        error(s"UNABLE TO DOWNLOAD FILE [ FROM : $bucket$fileName] [TO : $downloadTo]", ex)
        false
    } finally {
      transferManager.shutdownNow(false)
    }
  }

  def upload(bucket: String, file: File): Boolean = {
    val transferManager = new TransferManager(amazonS3Client)
    try {
      info(s"[ Bucket : $bucket ] [ Local File  : ${file.getPath()} ]")
      val uploadObject = transferManager.upload(bucket, file.getName, file)
      uploadObject.waitForCompletion()
      true
    } catch {
      case NonFatal(ex: Exception) =>
        error("UNABLE TO UPLOAD FILE [ FROM :" + file.getName + "] ", ex)
        false
    } finally {
      transferManager.shutdownNow(false)
    }
  }


  def uploadStream(bucket: String, fileName:String,stream:  InputStream,contentLength:Long): Boolean = {
    val transferManager = new TransferManager(amazonS3Client)
    try {
      info(s"[ Bucket : $bucket ] [ Local File  : ${fileName} ]")
      val objectMetadata=new ObjectMetadata()
      objectMetadata.setContentLength(contentLength)
      val uploadObject = transferManager.upload(bucket, fileName, stream, objectMetadata)
      uploadObject.waitForCompletion()
      true
    } catch {
      case NonFatal(ex: Exception) =>
        error("UNABLE TO UPLOAD FILE [ FROM :" + fileName + "] ", ex)
        false
    } finally {
      transferManager.shutdownNow(false)
    }
  }
  def getS3Url(bucket: String, key: String,days:Int): String = {
    val expiration = new java.util.Date()
    val durationInMillis = System.currentTimeMillis() + days * 24 * 60 * 60 * 1000 // convert into millis
    expiration.setTime(durationInMillis)
    val generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, key)
    generatePresignedUrlRequest.setMethod(HttpMethod.GET)
    generatePresignedUrlRequest.setExpiration(expiration)
    amazonS3Client.generatePresignedUrl(generatePresignedUrlRequest).toString
  }

}
