package com.dta.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ConnexionAfficherProduitsUtilisateursTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://5.196.89.85:8084/VenteEnligneAdmin-0.0.1-SNAPSHOT/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testConnexionAfficherProduitsUtilisateurs() throws Exception {
    driver.get(baseUrl + "authentification.xhtml");
    driver.findElement(By.xpath("//td[2]/input")).clear();
    driver.findElement(By.xpath("//td[2]/input")).sendKeys("root");
    driver.findElement(By.xpath("//tr[2]/td[2]/input")).clear();
    driver.findElement(By.xpath("//tr[2]/td[2]/input")).sendKeys("root");
    driver.findElement(By.xpath("//button")).click();
    driver.get(baseUrl + "views/article/rechercherArticle.xhtml");
    assertEquals("No records found.", driver.findElement(By.cssSelector("div.ui-datalist-empty-message")).getText());
    driver.findElement(By.xpath("//button[2]")).click();
    try {
      assertTrue(isElementPresent(By.xpath("//td/a/img")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.get(baseUrl + "views/utilisateur/rechercherUtilisateur.xhtml");
    assertEquals("No records found.", driver.findElement(By.cssSelector("div.ui-datalist-empty-message")).getText());
    driver.findElement(By.xpath("//button[2]")).click();
    try {
      assertTrue(isElementPresent(By.xpath("//td/a/img")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
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
