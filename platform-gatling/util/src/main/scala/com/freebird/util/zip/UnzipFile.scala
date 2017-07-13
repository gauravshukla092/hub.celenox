package com.freebird.util.zip

import java.io._
import org.apache.commons.compress.compressors.z.ZCompressorInputStream
import java.util.zip.{GZIPInputStream, ZipInputStream}
import com.freebird.util.logger.FreebirdLogger

trait UnzipFileUtil extends FreebirdLogger {

  def unZip(localDirectory: String, inputfileName: String, outputfileName: String, destination: String): Unit = {
    info("Going to extract .Z input file into destination directory..")
    info("input file path : " + localDirectory + inputfileName)
    info("output file path : " + destination + outputfileName)

    val fin = new FileInputStream(localDirectory + inputfileName)
    val in = new BufferedInputStream(fin)
    val out = new FileOutputStream(destination + outputfileName)
    val zIn = new ZCompressorInputStream(in)
    val buffer = new Array[Byte](1024)

    try {
      Stream.continually(zIn.read(buffer)).takeWhile(_ != -1).foreach(out.write(buffer, 0, _))
    } finally {
      out.close()
      zIn.close()
    }
  }
}

object UnzipFileUtilImpl extends UnzipFileUtil
