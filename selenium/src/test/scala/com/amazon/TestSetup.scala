package com.amazon

import java.util.concurrent.TimeUnit

import org.openqa.selenium.{By, Keys}
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}

/**
  * Created by knoldus on 28/5/17.
  */
trait TestSetup {


  val driverPath = "/home/knoldus/hub.celenox/chromedriver"
  System.setProperty("webdriver.chrome.driver", driverPath)
  val driver = new ChromeDriver()
  val webDriverWait = new WebDriverWait(driver, 30)
  val action = new Actions(driver)

  def getBaseUrl(baseUrl: String) = {
    driver.manage().window().maximize()
    driver.get(baseUrl)
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
  }

  def loginOnAWSConsole(userName: String, password: String): Unit = {
    driver.findElementById("username").sendKeys(userName)
    driver.findElementById("password").sendKeys(password)
    driver.findElementById("signin_button").click()
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-box-input")));
  }

  def searchAndSelectDesiredItem(searchCriteria: String, desiredBucket: String): Unit = {
    driver.findElementById("search-box-input").sendKeys(searchCriteria)
    driver.findElementById("search-box-item-0-title").click()
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elements.searchBox)));
    driver.findElementByXPath(elements.searchBox).sendKeys(desiredBucket)
    driver.findElementByXPath(elements.firstElementFromTheList).click()
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elements.bucketWithCSV)));
    driver.findElementByXPath(elements.bucketWithCSV).click()
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elements.yearPageSearchBox)));
  }

  def searchBucketForDesiredDate(year: String, month: String, day: String): Unit = {
    driver.findElementByXPath(elements.yearPageSearchBox).sendKeys(year)
    driver.findElementByXPath(elements.yearPageSearchBox).sendKeys(Keys.ENTER)
    driver.findElementByXPath(elements.year).click()
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elements.yearPageSearchBox)));
    driver.findElementByXPath(elements.yearPageSearchBox).sendKeys(month)
    driver.findElementByXPath(elements.yearPageSearchBox).sendKeys(Keys.ENTER)
    driver.findElementByXPath(elements.month).click()
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elements.yearPageSearchBox)));
    driver.findElementByXPath(elements.yearPageSearchBox).sendKeys(day)
    driver.findElementByXPath(elements.yearPageSearchBox).sendKeys(Keys.ENTER)
    driver.findElementByXPath(elements.day).click()
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elements.yearPageSearchBox)));

  }


}
