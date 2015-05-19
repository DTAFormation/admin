package com.dta.selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SuccesCreationUtilisateur {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://5.196.89.85:8084";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSuccesCreationUtilisateur() throws Exception {
	  
	Integer id = (int) (1000000 * Math.random());
	  
    driver.get(baseUrl + "/VenteEnligneAdmin-0.0.1-SNAPSHOT/authentification.xhtml");
    driver.findElement(By.xpath("//td[2]/input")).clear();
    driver.findElement(By.xpath("//td[2]/input")).sendKeys("root");
    driver.findElement(By.xpath("//tr[2]/td[2]/input")).clear();
    driver.findElement(By.xpath("//tr[2]/td[2]/input")).sendKeys("root");
    driver.findElement(By.xpath("//button")).click();
    driver.get(baseUrl + "/VenteEnligneAdmin-0.0.1-SNAPSHOT/views/utilisateur/ajoutUtilisateur.xhtml");
    driver.findElement(By.id("clientForm:NomInput")).clear();
    driver.findElement(By.id("clientForm:NomInput")).sendKeys("theBest");
    driver.findElement(By.id("clientForm:PrenomInput")).clear();
    driver.findElement(By.id("clientForm:PrenomInput")).sendKeys("toto");
    driver.findElement(By.xpath("//div[3]/span")).click();
    driver.findElement(By.xpath("//div[6]/div/ul/li[2]")).click();
    driver.findElement(By.id("clientForm:loginInput")).clear();
    driver.findElement(By.id("clientForm:loginInput")).sendKeys("truc" + id.toString());
    driver.findElement(By.xpath("//tr[2]/td[2]/div/div[3]/span")).click();
    driver.findElement(By.xpath("//div[7]/div/ul/li[2]")).click();
    driver.findElement(By.id("clientForm:EmailInput")).clear();
    driver.findElement(By.id("clientForm:EmailInput")).sendKeys("a"+ id.toString()+"@a.com");
    driver.findElement(By.xpath("//fieldset[3]/div/table/tbody/tr[4]/td[2]/input")).clear();
    driver.findElement(By.xpath("//fieldset[3]/div/table/tbody/tr[4]/td[2]/input")).sendKeys("toto2");
    driver.findElement(By.xpath("//fieldset[3]/div/table/tbody/tr[5]/td[2]/input")).clear();
    driver.findElement(By.xpath("//fieldset[3]/div/table/tbody/tr[5]/td[2]/input")).sendKeys("toto2");
    driver.findElement(By.xpath("//form/div/div/button")).click();
    try {
      assertEquals("Utilisateur enregistre", driver.findElement(By.cssSelector("span.ui-growl-title")).getText());
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
