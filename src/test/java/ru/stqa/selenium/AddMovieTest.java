package ru.stqa.selenium;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.openqa.selenium.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddMovieTest extends TestNgTestBase{

  @Test
    public void testAddMovieMinimumParameters() throws Exception {
    //login
    LoginTest.testLogin(driver);

    //add movie
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    WebDriverWait wait = new WebDriverWait(driver,30);
    List<WebElement> moviesBefore = driver.findElements(By.xpath("//*[@class='movie_box']"));
    driver.findElement(By.cssSelector("img[alt=\"Add movie\"]")).click();
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("Выживший");
    driver.findElement(By.name("year")).clear();
    driver.findElement(By.name("year")).sendKeys("2015");
    driver.findElement(By.id("submit")).click();
    driver.findElement(By.linkText("Home")).click();

    //check movies counter
    wait.until(ExpectedConditions. //wait for home page to load
            presenceOfElementLocated(By.
                    xpath("//*[@class='searchbox']")));
    List<WebElement> moviesAfter = driver.findElements(By.xpath("//*[@class='movie_box']"));
    if (moviesAfter.size() != moviesBefore.size()+1)
      throw new Error("Фильм не добавлен");
  }
  @Test
  public void testAddMovieNegative() throws Exception {
    //no "name" parameter
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    WebDriverWait wait = new WebDriverWait(driver,30);
    List<WebElement> moviesBefore = driver.findElements(By.xpath("//*[@class='movie_box']"));
    driver.findElement(By.cssSelector("img[alt=\"Add movie\"]")).click();
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("");
    driver.findElement(By.name("year")).clear();
    driver.findElement(By.name("year")).sendKeys("2015");
    driver.findElement(By.id("submit")).click();
    wait.until(ExpectedConditions.
            presenceOfElementLocated(By.
                    xpath("//*[@for='name'][contains(text(),'This field is required')]")));

    //no "year" parameter
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("Невыживший");
    driver.findElement(By.name("year")).clear();
    driver.findElement(By.name("year")).sendKeys("");
    driver.findElement(By.id("submit")).click();

    wait.until(ExpectedConditions.
            presenceOfElementLocated(By.
                    xpath("//*[@for='year'][contains(text(),'This field is required')]")));
    driver.findElement(By.linkText("Home")).click();

    //check movies counter
    wait.until(ExpectedConditions. //wait for home page to load
            presenceOfElementLocated(By.
                    xpath("//*[@class='searchbox']")));
    List<WebElement> moviesAfter = driver.findElements(By.xpath("//*[@class='movie_box']"));
    if (moviesAfter.size() > moviesBefore.size())
      throw new Error("Ошибочное добавление фильма");
  }
}
