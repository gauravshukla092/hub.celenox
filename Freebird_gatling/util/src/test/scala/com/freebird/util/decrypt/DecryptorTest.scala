package com.freebird.util.decrypt

import java.nio.charset.StandardCharsets
import java.util.Base64


import org.scalatest.FunSuite

class DecryptorTest  extends  FunSuite{

  val TEST_STRING = "Freebird"
  val encryptedData = Base64.getEncoder.encodeToString(TEST_STRING.getBytes(StandardCharsets.UTF_8))

  test("Decrypt the raw data(string)"){
    val decryptedData = Decryptor.decode(new String(encryptedData))
    assert(decryptedData === TEST_STRING.getBytes)
  }

  test("Decrypt the raw data(Array[Byte])"){
    val decryptedData = Decryptor.decode(encryptedData)
    assert(decryptedData === TEST_STRING.getBytes)
  }

}

