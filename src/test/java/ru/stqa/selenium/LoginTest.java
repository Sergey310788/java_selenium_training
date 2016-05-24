package ru.stqa.selenium;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;

public class LoginTest extends TestNgTestBase{

  @Test
  public void testLogin() throws Exception {
    driver.get(baseUrl + "/php4dvd/");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("submit")).click();
    assertEquals(driver.findElement(By.xpath("//title")).getText(), " My movie collection - php4dvd v2.0 ");
  }
}