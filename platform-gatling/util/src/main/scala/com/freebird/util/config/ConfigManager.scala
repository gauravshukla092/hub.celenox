package com.freebird.util.config

import com.typesafe.config.ConfigFactory

/**
  * Load the config file once and available for all the module.
  *
  */
object ConfigManager {

  lazy val config = ConfigFactory.load()

}
