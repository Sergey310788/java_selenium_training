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
    String movieTitle = "Выживший";
    String movieYear = "2015";
    List<WebElement> moviesBefore = null;
    List<WebElement> moviesAfter = null;
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    WebDriverWait wait = new WebDriverWait(driver,30);
    wait.until(ExpectedConditions. //wait for home page to load
            presenceOfAllElementsLocatedBy(
                  By.xpath("//div[@class='title']")));
    moviesBefore = driver.findElements(By.xpath("//div[@class='title']"));
    driver.findElement(By.cssSelector("img[alt=\"Add movie\"]")).click();
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys(movieTitle);
    driver.findElement(By.name("year")).clear();
    driver.findElement(By.name("year")).sendKeys(movieYear);
    driver.findElement(By.id("submit")).click();
    driver.findElement(By.linkText("Home")).click();

    //check movies counter
    wait.until(ExpectedConditions. //wait for home page to load
            presenceOfAllElementsLocatedBy(
                    By.xpath("//div[@class='title']")));
    moviesAfter = driver.findElements(By.xpath("//div[@class='title']"));
    if (moviesAfter.size() != moviesBefore.size()+1)
      throw new Error("Фильм не добавлен");
  }
  @Test
  public void testAddMovieNegative() throws Exception {
    List<WebElement> moviesBefore = null;
    List<WebElement> moviesAfter = null;
    //no "name" parameter
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    WebDriverWait wait = new WebDriverWait(driver,30);
    wait.until(ExpectedConditions. //wait for home page to load
            presenceOfAllElementsLocatedBy(
                      By.xpath("//div[@class='title']")));
    moviesBefore = driver.findElements(By.xpath("//div[@class='title']"));
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
            presenceOfAllElementsLocatedBy(
            By.xpath("//div[@class='title']")));
    moviesAfter = driver.findElements(By.xpath("//div[@class='title']"));
    if (moviesAfter.size() > moviesBefore.size())
      throw new Error("Ошибочное добавление фильма");
  }
  public static void addMovie(WebDriver driver) throws Exception {
    //add movie
    List<WebElement> moviesBefore = null;
    List<WebElement> moviesAfter = null;
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    WebDriverWait wait = new WebDriverWait(driver,30);
    wait.until(ExpectedConditions. //wait for home page to load
            presenceOfAllElementsLocatedBy(
            By.xpath("//div[@class='title']")));
    moviesBefore = driver.findElements(By.xpath("//div[@class='title']"));
    driver.findElement(By.cssSelector("img[alt=\"Add movie\"]")).click();
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("Начало");
    driver.findElement(By.name("year")).clear();
    driver.findElement(By.name("year")).sendKeys("2010");
    driver.findElement(By.id("submit")).click();
    driver.findElement(By.linkText("Home")).click();

    //check movies counter
    wait.until(ExpectedConditions. //wait for home page to load
            presenceOfAllElementsLocatedBy(
            By.xpath("//div[@class='title']")));
    moviesAfter = driver.findElements(By.xpath("//div[@class='title']"));
    if (moviesAfter.size() != moviesBefore.size()+1)
      throw new Error("Фильм не добавлен");
  }
}
