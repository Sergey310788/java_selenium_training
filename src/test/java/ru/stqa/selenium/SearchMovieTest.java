package ru.stqa.selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.applogic2.ApplicationManager2;
import ru.stqa.selenium.model.Film;
import ru.stqa.selenium.model.User;
import ru.stqa.selenium.pages.TestBase;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class SearchMovieTest extends TestBase {

  @BeforeMethod

  public static void mayBeLoginAndFilmPresent(ApplicationManager2 app){
    AddMovieTest.mayBeLogIn(app);
    if (app.getNavigationHelper().isNotHomePage()){
      app.getNavigationHelper().openMainPage();
    }
    if (app.getFilmHelper().noFilmsAdded()){
      app.getNavigationHelper().gotoAddFilmForm();
      Film movie = new Film().setTitle("Начало").setYear("2010");
      app.getFilmHelper().create(movie);
      assertTrue(app.getFilmHelper().isFilmAdded(movie));
    }
  }


  @Test
    public void testSearchMoviePositive() throws Exception {
      app.getFilmHelper().search("Начало");
      assertTrue(app.getFilmHelper().isFilmFinded());





//    //add movie
//    AddMovieTest.addMovie(driver);
//
//    //search movie
//    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
//    WebDriverWait wait = new WebDriverWait(driver, 30);
//    driver.findElement(By.xpath("//input[@id='q']")).sendKeys("Начало" + Keys.RETURN);
//    wait.until(ExpectedConditions.visibilityOfElementLocated(
//            By.xpath("//div[contains(text(),'Начало')]")));
  }
  @Test
    public void testSearchMovieNegative() throws Exception {
      app.getFilmHelper().search("Тест");
      assertTrue(app.getFilmHelper().isFilmNotFinded());




//    //login
//    LoginTest.testLogin(driver);
//
//    //search movie
//    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
//    WebDriverWait wait = new WebDriverWait(driver, 30);
//    wait.until(ExpectedConditions.visibilityOfElementLocated(
//            By.xpath("//input[@id='q']")));
//    driver.findElement(By.xpath("//input[@id='q']")).clear();
//    driver.findElement(By.xpath("//input[@id='q']")).sendKeys("Тест" + Keys.RETURN);
//    wait.until(ExpectedConditions.visibilityOfElementLocated(
//            By.xpath("//div[contains(text(),'No movies where found.')]")));
//    driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
  }
}
