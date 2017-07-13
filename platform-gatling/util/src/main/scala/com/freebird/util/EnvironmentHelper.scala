package com.freebird.util

import com.freebird.util.Constants._
import com.freebird.util.config.ConfigManager



trait EnvironmentHelper {

  val mandrillTemplateEnvironment: String = {
    val mandrillTemplateEnv = ConfigManager.config.getString("mandrillmail.env").trim.toLowerCase
    if(mandrillTemplateEnv.equals(STAGING_TEMPLATE_ENV) || mandrillTemplateEnv.equals(PRODUCTION_TEMPLATE_ENV1) ||
      mandrillTemplateEnv.equals(PRODUCTION_TEMPLATE_ENV2)){
      STANDARD_TEMPLATE_ENV
    } else{
      mandrillTemplateEnv
    }
  }

  def getMandrillTemplateKey(templateCode: Int): String = s"freebird_${templateCode}_$mandrillTemplateEnvironment"

}
