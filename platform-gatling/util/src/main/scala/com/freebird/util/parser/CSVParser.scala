package com.freebird.util.parser

import java.io.InputStreamReader

import au.com.bytecode.opencsv.CSVReader


case class CSVIterator(reader: CSVReader) extends Iterator[Array[String]] {
  private var current = reader.readNext

  def hasNext: Boolean = Option(current).isDefined

  def next: Array[String] = {
    val line = current
    current = reader.readNext
    line
  }
}

trait CSVParser {

  def parseData(inputStreamReader: InputStreamReader, separator: Char = ',', header: Boolean = true): CSVIterator = {
    val reader = if(header) {
      new CSVReader(inputStreamReader, separator, '\'', 1)
    } else {
      new CSVReader(inputStreamReader, separator, '\'', 0)
    }
    CSVIterator(reader)
  }

}

object CSVParser extends CSVParser
