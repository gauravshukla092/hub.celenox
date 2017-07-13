package com.freebird.util


trait StringUtil {

  def camel2Underscore(text: String): String = {
    val stringWithSpace = text.drop(1).foldLeft(text.headOption.map(_.toLower + "") getOrElse "") {
      case (acc, c) if c.isUpper => acc + "_" + c.toLower
      case (acc, c) => acc + c
    }
    stringWithSpace.replaceAll("\\s", "")
  }
}

object StringUtil extends StringUtil
