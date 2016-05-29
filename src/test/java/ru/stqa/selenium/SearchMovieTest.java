package ru.stqa.selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchMovieTest extends TestNgTestBase{

  @Test
    public void testSearchMoviePositive() throws Exception {
    //add movie
    AddMovieTest.addMovie(driver);

    //search movie
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    WebDriverWait wait = new WebDriverWait(driver, 30);
    driver.findElement(By.xpath("//input[@id='q']")).sendKeys("Начало" + Keys.RETURN);
    wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(text(),'Начало')]")));
  }
  @Test
  public void testSearchMovieNegative() throws Exception {
    //login
    LoginTest.testLogin(driver);

    //search movie
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//input[@id='q']")));
    driver.findElement(By.xpath("//input[@id='q']")).clear();
    driver.findElement(By.xpath("//input[@id='q']")).sendKeys("Тест" + Keys.RETURN);
    wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(text(),'No movies where found.')]")));
    driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
  }
}
