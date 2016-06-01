package ru.stqa.selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.Helpers.Helpers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class SearchMovieTest extends TestNgTestBase{

  @Test
    public void testSearchMoviePositive() throws Exception {
    //search movie
    List<WebElement> foundMovies = null;
    List<WebElement> allMovies = null;
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    WebDriverWait wait = new WebDriverWait(driver, 5);
    driver.findElement(By.xpath("//input[@id='q']")).clear();
    driver.findElement(By.xpath("//input[@id='q']")).sendKeys("Начало" + Keys.RETURN);
    try{
      wait.until(ExpectedConditions.visibilityOfElementLocated(
              By.xpath("//div[@class='title']")));
    }
    catch(Exception e){
      throw new Error("Ошибка поиска фильма");
    }
    foundMovies = driver.findElements(By.xpath("//div[contains(text(),'Начало')]"));
    allMovies = driver.findElements(By.xpath("//div[@class='title']"));
    if (foundMovies.size() != allMovies.size())
      throw new Error("Поиск работает неправильно");
  }
  @Test
  public void testSearchMovieNegative() throws Exception {
    //search movie
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    WebDriverWait wait = new WebDriverWait(driver, 30);
    Helpers.mayBeLogin(driver);
    Helpers.addMovie(driver);
    wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//input[@id='q']")));
    driver.findElement(By.xpath("//input[@id='q']")).clear();
    driver.findElement(By.xpath("//input[@id='q']")).sendKeys("Тест" + Keys.RETURN);
    wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(text(),'No movies where found.')]")));
    driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
  }
}
