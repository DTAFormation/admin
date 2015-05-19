package com.dta.selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class EchecCreationUtilisateur {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://5.196.89.85:8084/VenteEnligneAdmin-0.0.1-SNAPSHOT";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testEchecCreationUtilisateur() throws Exception {
    driver.get(baseUrl + "/authentification.xhtml");
    driver.findElement(By.xpath("//td[2]/input")).clear();
    driver.findElement(By.xpath("//td[2]/input")).sendKeys("root");
    driver.findElement(By.xpath("//tr[2]/td[2]/input")).clear();
    driver.findElement(By.xpath("//tr[2]/td[2]/input")).sendKeys("root");
    driver.findElement(By.xpath("//button")).click();
    driver.get(baseUrl + "/views/utilisateur/ajoutUtilisateur.xhtml");
    driver.findElement(By.xpath("//form/div/div/button")).click();
    try {
      assertEquals("Veuillez préciser votre nom", driver.findElement(By.xpath("//li/span")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Veuillez préciser votre prenom", driver.findElement(By.xpath("//tr[2]/td[3]/div/div/ul/li/span")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Veuillez choisir une civilité", driver.findElement(By.xpath("//tr[3]/td[3]/div/div/ul/li/span")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Veuillez préciser votre login", driver.findElement(By.xpath("//fieldset[3]/div/table/tbody/tr/td[3]/div/div/ul/li/span")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Veuillez choisir un type utilisateur", driver.findElement(By.xpath("//fieldset[3]/div/table/tbody/tr[2]/td[3]/div/div/ul/li/span")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Veuillez préciser votre email", driver.findElement(By.xpath("//fieldset[3]/div/table/tbody/tr[3]/td[3]/div/div/ul/li/span")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Veuillez entrer un mot de passe", driver.findElement(By.xpath("//tr[4]/td[3]/div/div/ul/li/span")).getText());
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
