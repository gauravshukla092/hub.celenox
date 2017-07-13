package com.freebird.util

import com.freebird.util.logger.FreebirdLogger
import org.scalatest.FunSuite

class HasherTest extends FunSuite with FreebirdLogger{

  test("Hasher should generate a hash code which different from the input value"){
    val value = "password"
    val hashedValue = Hasher.getHash(value)
    logger.info(hashedValue)
    assert(value !== hashedValue)
  }

  test("Hasher should compare plain password and hashed password"){
    val plainPassword = "dummyPass"
    val hashedPassword = "$2a$10$9RBQM5b8Hp0SIcGH9qUMO.hsbDf6aO3j/vALuvnXm2zIwXoqTAiIK"
    val result = Hasher.compare(plainPassword, hashedPassword)
    assert(result)
  }

}
