package com.freebird.util

import org.scalatest.FunSuite


class EnvironmentHelperTest extends FunSuite with EnvironmentHelper{

  test("mandrill environment ") {
    assert(mandrillTemplateEnvironment === "local")
  }

}
