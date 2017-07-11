package com.freebird.util

import org.mindrot.jbcrypt.BCrypt


trait Hasher {

  def getHash(value: String): String = {
    BCrypt.hashpw(value, BCrypt.gensalt())
  }

  def compare(plainPassword: String, hashedPassword: String): Boolean = {
    BCrypt.checkpw(plainPassword, hashedPassword)
  }
}

object Hasher extends Hasher
