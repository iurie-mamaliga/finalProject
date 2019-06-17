package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TeaiSeleniumTest {
	private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @Before
	  public void setUp() throws Exception {
	    driver = new ChromeDriver();
	    baseUrl = "https://www.katalon.com/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void teaiSeleniumTest() throws Exception {
	    driver.get("http://localhost:8080/final-capstone/");
	    driver.findElement(By.linkText("Home")).click();
	    driver.findElement(By.linkText("Sign Up")).click();
	    driver.findElement(By.id("firstName")).click();
	    driver.findElement(By.id("firstName")).clear();
	    driver.findElement(By.id("firstName")).sendKeys("John");
	    driver.findElement(By.id("lastName")).click();
	    driver.findElement(By.id("lastName")).clear();
	    driver.findElement(By.id("lastName")).sendKeys("Doe");
	    driver.findElement(By.id("email")).click();
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("john@mail.com");
	    driver.findElement(By.id("phoneNumber")).click();
	    driver.findElement(By.id("phoneNumber")).clear();
	    driver.findElement(By.id("phoneNumber")).sendKeys("4124224433");
	    driver.findElement(By.id("userName")).click();
	    driver.findElement(By.id("userName")).clear();
	    driver.findElement(By.id("userName")).sendKeys("john");
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("Mypasswordisstrong1");
	    driver.findElement(By.id("confirmPassword")).click();
	    driver.findElement(By.id("confirmPassword")).clear();
	    driver.findElement(By.id("confirmPassword")).sendKeys("Mypasswordisstrong1");
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Confirm Password:'])[1]/following::button[1]")).click();
	    driver.findElement(By.id("userName")).click();
	    driver.findElement(By.id("userName")).clear();
	    driver.findElement(By.id("userName")).sendKeys("john");
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("Mypasswordisstrong1");
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::button[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Home'])[1]/following::ul[1]")).click();
	    try {
	      assertEquals("Howdy john :)", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Home'])[1]/following::li[1]")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    driver.findElement(By.linkText("Home")).click();
	    driver.findElement(By.id("logoutLink")).click();
	    driver.findElement(By.linkText("Log In")).click();
	    driver.findElement(By.id("userName")).click();
	    driver.findElement(By.id("userName")).clear();
	    driver.findElement(By.id("userName")).sendKeys("adminuser");
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("passwordpasswordA1");
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::button[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Delete'])[1]/following::input[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Delete'])[1]/following::input[1]")).click();
	    // ERROR: Caught exception [ERROR: Unsupported command [doubleClick | xpath=(.//*[normalize-space(text()) and normalize-space(.)='Delete'])[1]/following::input[1] | ]]
	    driver.findElement(By.id("changeUserRole")).click();
	    new Select(driver.findElement(By.id("changeUserRole"))).selectByVisibleText("Student");
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Delete'])[3]/following::button[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Delete'])[1]/following::input[1]")).click();
	    driver.findElement(By.id("changeUserRole")).click();
	    new Select(driver.findElement(By.id("changeUserRole"))).selectByVisibleText("Administrator");
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Delete'])[3]/following::button[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Delete'])[1]/following::td[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Delete'])[1]/following::input[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Delete'])[3]/following::button[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='guest'])[2]/following::a[1]")).click();
	    driver.findElement(By.linkText("Events")).click();
	    driver.findElement(By.id("newEvent")).click();
	    driver.findElement(By.id("newEvent")).click();
	    driver.findElement(By.id("event_name")).click();
	    driver.findElement(By.id("event_name")).clear();
	    driver.findElement(By.id("event_name")).sendKeys("Test event");
	    driver.findElement(By.id("description")).click();
	    driver.findElement(By.id("description")).clear();
	    driver.findElement(By.id("description")).sendKeys("This is a test event");
	    driver.findElement(By.id("start_date")).click();
	    driver.findElement(By.id("start_date")).clear();
	    driver.findElement(By.id("start_date")).sendKeys("2018-12-21 09:00:00");
	    driver.findElement(By.id("end_date")).click();
	    driver.findElement(By.id("end_date")).clear();
	    driver.findElement(By.id("end_date")).sendKeys("2018-12-21 10:00:00");
	    driver.findElement(By.id("mandatory")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Copyright 2018 Team Exceptional Handlers.'])[1]/preceding::button[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='true'])[2]/following::input[1]")).click();
	    driver.findElement(By.id("delete")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Howdy adminuser :)'])[1]/following::div[5]")).click();
	    driver.findElement(By.id("showUpcoming")).click();
	    driver.findElement(By.id("showAllEvents")).click();
	    driver.findElement(By.linkText("Matchmaking")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='k.white@aeo.gov'])[1]/following::input[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Delete Position Available'])[1]/following::button[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='chriswoods@pnc.org'])[1]/following::input[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Delete Company'])[1]/following::button[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='sarahwills@pnc.org'])[1]/following::input[1]")).click();
	    driver.findElement(By.id("delete")).click();
	    driver.findElement(By.linkText("Resources")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Admin Resources'])[1]/following::div[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Name'])[1]/following::div[15]")).click();
	    driver.close();
	    driver.close();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Click here to view pathway resources'])[1]/following::div[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Name'])[1]/following::div[15]")).click();
	    driver.close();
	    driver.findElement(By.linkText("Chat")).click();
	    driver.findElement(By.id("message-input")).click();
	    driver.findElement(By.id("message-input")).clear();
	    driver.findElement(By.id("message-input")).sendKeys("What is next event");
	    driver.findElement(By.id("message-input")).sendKeys(Keys.ENTER);
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Howdy adminuser :)'])[1]/following::div[4]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Chat'])[1]/following::ul[1]")).click();
	    try {
	      assertEquals("Howdy adminuser :)", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Chat'])[1]/following::li[1]")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    driver.findElement(By.id("logoutLink")).click();
	    driver.findElement(By.linkText("Log In")).click();
	    driver.findElement(By.id("userName")).click();
	    driver.findElement(By.id("userName")).clear();
	    driver.findElement(By.id("userName")).sendKeys("studentuser");
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("passwordpasswordA1");
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::button[1]")).click();
	    driver.findElement(By.linkText("Home")).click();
	    driver.findElement(By.linkText("Events")).click();
	    driver.findElement(By.linkText("Matchmaking")).click();
	    driver.findElement(By.linkText("Resources")).click();
	    driver.findElement(By.linkText("Chat")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Chat'])[1]/following::ul[1]")).click();
	    try {
	      assertEquals("Howdy studentuser :)", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Chat'])[1]/following::li[1]")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    driver.findElement(By.id("message-input")).click();
	    driver.findElement(By.id("message-input")).clear();
	    driver.findElement(By.id("message-input")).sendKeys("Who is comint to matchmaking");
	    driver.findElement(By.id("message-input")).sendKeys(Keys.ENTER);
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Howdy studentuser :)'])[1]/following::div[4]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Howdy studentuser :)'])[1]/following::div[3]")).click();
	    driver.findElement(By.id("logoutLink")).click();
	  }

	  @After
	  public void tearDown() throws Exception {
	    //driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
}
