package com.freebird.util.decrypt

import sun.misc.BASE64Decoder

/**
 * Decrypt the base64 encrypted data
 */
object Decryptor {

  private  val base64Decoder = new BASE64Decoder()

  def decode(inputString:String):Array[Byte] = {
    base64Decoder.decodeBuffer(inputString)
  }

  def decode(input:Array[Byte]):Array[Byte]= {
    val inputString = new String(input)
    base64Decoder.decodeBuffer(inputString)
  }

}
