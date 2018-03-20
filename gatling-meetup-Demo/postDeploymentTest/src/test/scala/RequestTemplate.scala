object RequestTemplate extends TestSetUp {


  val RegisterRequestTemplate =
    """{"userName":"${userName}","emailId":"${email}","password":"123456789"}""".stripMargin

  val RegisterRequest =
    """{"userName":"grvv${numb}","emailId":"gaurav.celenox${numb}@knoldus.com","password":"123456789"}"""

  val LoginTemplate =
    """{"userName": "gaurav","password": "987654321"}"""


}
