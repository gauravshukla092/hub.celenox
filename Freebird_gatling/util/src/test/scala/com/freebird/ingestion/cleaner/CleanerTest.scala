package com.freebird.ingestion.cleaner

import com.freebird.util.cleaner.Cleaner
import org.scalatest.FunSuite


class CleanerTest extends FunSuite {

  test("Clean the raw data "){
    val cleaner = new Cleaner
    val cleanedData = cleaner.clean("raw data")
    assert( cleanedData ==="raw data" )

  }

}

