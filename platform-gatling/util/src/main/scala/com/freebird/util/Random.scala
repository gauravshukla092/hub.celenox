package com.freebird.util

import java.util.UUID

import Constants._

trait Random {


  def createString: String = UUID.randomUUID().toString


  def createToken(length: Int): String = {
    def nextChar: Char = {
      val rnd = util.Random.nextInt(NUMBER_62) + NUMBER_48
      rnd match {
        case random if random <= NUMBER_57 => random.toChar
        case random if random > NUMBER_57 && random <=NUMBER_83 => (random + NUMBER_7).toChar
        case _ => (rnd + NUMBER_13).toChar
      }
    }
    Seq.fill(length)(nextChar).mkString
  }


}

object Random extends Random
