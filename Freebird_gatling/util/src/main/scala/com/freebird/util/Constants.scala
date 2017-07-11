package com.freebird.util


object Constants {

  val DEFAULT_PARTNER = "Freebird"

  val pccCode = "pccCode"

  val AIRLINE_CODE = "airlineCode"
  val FLIGHT_NUMBER = "flightNumber"
  val DATE = "date"
  val SPIRIT_AIRLINE_CODE = "NK"
  val AIRPORT_NOT_FOUND = "Airport not found"
  val ARR_AIRPORT_NOT_EXISTS = "Arrival Airport does not exist"
  val DEP_AIRPORT_NOT_EXISTS = "Departure Airport does not exist"
  val DEP_AIRPORT_ZONE_NOT_EXISTS = "Departure Airport's time zone not found from the AirportTimeZoneMap"
  val ARR_AIRPORT_ZONE_NOT_EXISTS = "Arrival Airport's time zone not found from the AirportTimeZoneMap"
  val DIV_AIRPORT_NOT_EXISTS = "Diversion Airport does not exist"
  val ARR_NOT_EXISTS = "Arrival Vertex does not exist"
  val DEP_NOT_EXISTS = "Departure Vertex does not exist"
  val LEG_NOT_EXISTS = "Leg not found"
  val ARRIVES_AT = "arrivesAt"
  val DEPARTS_FROM = "departsFrom"
  val CONNECTING_TIME_EDGE_NOT_FOUND = "Connecting edge from arrival/departure to airport with arrival/departure time does not exist"
  /**
    * Magic numbers
    */
  val NUMBER_7 = 7
  val NUMBER_3 = 3
  val NUMBER_9 = 9
  val NUMBER_13 = 13
  val NUMBER_34 = 34
  val NUMBER_19 = 19
  val NUMBER_48 = 48
  val NUMBER_57 = 57
  val NUMBER_62 = 62
  val NUMBER_83 = 83
  val AGE_2 = 2
  val AGE_16 = 16
  val AGE_60 = 60

  /**
    * Person types
    */
  val PERSON_INFANT = "infant"
  val PERSON_CHILD = "child"
  val PERSON_ADULT = "adult"
  val PERSON_SENIOR = "senior"

  /**
    * Template Codes
    */
  val REBOOKING_RESPONSE_TO_PARTNER_TEMPLATE_CODE = 10
  val REBOOKING_PASSENGER_CONFIRMATION_RECEIPT_TEMPLATE_CODE = 11
  val ALERT_TO_TRIP_MANAGER_TEMPLATE_CODE = 12
//  val D2C_REBOOKING_PASSENGER_CONFIRMATION_RECEIPT = 13
  val NOTIFY_FREEBIRD_ADMIN_FOR_TRIP_DISRUPTION = 14
  val ALERT_TO_TRIP_MANAGER_TEMPLATE_CODE_FOR_RE_REBOOKING = 15
  val NOTIFY_FREEBIRD_CUSTOMERS_FOR_TRIP_PURCHASE = 16
  val NOTIFY_AGENTS_FOR_REBOOKING = 17

  /**
    * Gender type
    */
  val MALE = "Male"
  val FEMALE = "Female"

  /**
    * To be moved into configuration file
    */

  val FREEBIRD_API_HOST = "0.0.0.0"
  val FREEBIRD_API_PORT = 8181

  val PARTNER_API_HOST = "0.0.0.0"
  val PARTNER_API_PORT = 8383

  val FREEBIRD_INGESTION_PORT = 8282

  val FLIGHT_STATS_API_HOST = "0.0.0.0"
  val FLIGHT_STATS_API_PORT = 9191

  val STANDARD_TEMPLATE_ENV = "platform"
  val STAGING_TEMPLATE_ENV = "staging"
  val PRODUCTION_TEMPLATE_ENV1= "prod"
  val PRODUCTION_TEMPLATE_ENV2 = "production"

  val AIRPORT_INGESTION_HOST = "0.0.0.0"
  val AIRPORT_INGESTION_PORT = 8187



  /**
    * Purchase status
    */
  val FALSE = false
  val TRUE = true

  /**
    * Vertex status
    */
  val VERTEX_ALREADY_EXISTS = "Vertex already exists"
  val VERTEX_NOT_FOUND_ERROR = "Vertex not found"
  val VERTEX_ADDED_SUCCESSFULLY = "Vertex added successfully"
  val EDGE_CREATED_SUCCESSFULLY = "Edge created successfully"
  val OPERATION_SUCCESSFUL_STATUS = "Operation successful"
  val OPERATION_FAILURE_STATUS = "Operation failed"

  /**
    * Default values for parameters
    */
  val DEFAULT_PARAMETER = ""
  val NONE = "None"

  val DEFAULT_AIRPORT_CSV_PATH = "/airports_world.csv"
  val ALL_AIRPORT_CSV_PATH = "/all_airports_world.csv"
  val NEW_LINE = "\n"
  val SINGLE_SPACE = " "
  /**
    * User doc error codes
    */
  final val RESPONSE_CODE_SUCCESS = 200
  final val RESPONSE_CODE_INTERNAL_SERVER_ERROR = 500
  final val RESPONSE_CODE_BAD_REQUEST = 400

  /**
    * OAG DATA
    */
  final val Y = "Y"
  final val N = "N"

  /**
    *
    */
  val REGULAR = "REGULAR"
  val GOLD = "GOLD"
  val PLATINUM = "PLATINUM"

  /**
    * Sources
    */
  val DIRECT = "direct"
  val INTERNAL = "internal"
  val NA = "NA"
  val SCHEDULED = "Scheduled"
  val COMPLETED = "Completed"
  val IN_PROGRESS = "In-Progress"
  val CANCELLED = "Cancelled"
  val NOT_PURCHASED = "Not Purchased"
  val DELAYED = "Delayed"
  val DISRUPTED = "Disrupted"
  /**
    * Pricing function names
    */
  val CREDIT_CARD_FLAT_FEE = "creditCardFlatFee"
  val CREDIT_CARD_CATEGORY = "creditCard"
  val MARKUP_CATEGORY = "markup"

  /**
    * Slice status
    */
  val SERVED = "served"
  val DEFERRED = "deferred"

  /**
    * Person type
    */
  val ADULT = "adult"
  val CHILD = "child"
  val INFANT_IN_LAP = "infantInLap"
  val INFANT_IN_SEAT = "infantInSeat"
  val SENIOR = "senior"

  val ZERO_COUNT = 0
  val ONE_COUNT = 1
  val TWO_COUNT = 2
  val TWENTY_COUNT = 20
  val ERROR_TOLERANCE = 0.0000001


  /**
    * Current flight status
    */
  val UNKNOWN = "Unknown"
  val ONE_WAY = "ONE WAY TRIP"
  val OUT_BOUND = "OUTBOUND"
  val ROUND_TRIP = "ROUND TRIP"
  val RETURN_TRIP = "RETURN"
  val EMPTY_STR = " "

  /**
    * Notification channels
    */
  val EMAIL = "email"
  val SMS = "sms"
  val SLACK = "slack"

  val ZENDESK_STATUS_PENDING = "pending"
  val ZENDESK_STATUS_OPEN = "open"
  val ZENDESK_STATUS_SOLVED = "solved"

  val SLICE_TICKET_REBOOKING_EDGE= "createsRebooking"
  val SLICE_TICKET_3HOUR_BEFORE_EDGE= "createsBeforeFlight"



  /**
    * Segment Cabins
    */
  val COACH = "COACH"
  val PREMIUM = "PREMIUM_COACH"
  val BUSINESS = "BUSINESS"
  val FIRST = "FIRST"



val STANDARD_CABIN_CLASSES = List( "P", "F", "J", "C", "W", "Y")

}
