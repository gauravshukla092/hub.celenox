package com.freebird.util.exception


class FreebirdException(msg: String) extends IllegalArgumentException(msg)

class FreebirdRetryException(msg: String) extends IllegalArgumentException(msg)

case class AirportNotFoundException(msg: String) extends FreebirdException(msg)

case class ArrivalNotFoundException(msg: String) extends FreebirdException(msg)

case class DepartureNotFoundException(msg: String) extends FreebirdException(msg)

case class AlertRuleNotFoundException(msg: String) extends FreebirdException(msg)

case class InvalidPricingRequestException(msg: String) extends FreebirdException(msg)

case class InvalidDateFormatException(msg: String) extends FreebirdException(msg)

case class OpportunityNotFoundException(msg: String) extends FreebirdException(msg)

case class CouldNotCreateOpportunityException(msg: String) extends FreebirdException(msg)

case class OptionNotFoundException(msg: String) extends FreebirdException(msg)

case class FlightNotFoundException(msg: String) extends FreebirdRetryException(msg)

case class TripNotPurchasedException(msg: String) extends FreebirdException(msg)

case class FlightStatsLookUpException(msg: String) extends FreebirdException(msg)

case class UtcToLocalDateConversionException(msg: String) extends FreebirdException(msg)

case class FreebirdRDException(msg: String) extends FreebirdException(msg)

case class FlightStatusAlreadyExistsRDException(msg: String) extends FreebirdException(msg)

case class InvalidParentPartnerIdException(msg:String) extends FreebirdException(msg)

case class InvalidPartnerIdException(msg:String) extends FreebirdException(msg)
