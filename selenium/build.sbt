name := "test-automation-with-selenium"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "org.seleniumhq.selenium" % "selenium-java" % "3.4.0",
  "org.seleniumhq.selenium" % "selenium-server" % "3.4.0",
  "org.seleniumhq.selenium" % "selenium-firefox-driver" % "3.4.0",
  "org.seleniumhq.selenium" % "selenium-chrome-driver" % "3.4.0",
  "org.testng" % "testng" % "6.11",
  "org.scalatest" % "scalatest_2.12" % "3.0.3"

)



