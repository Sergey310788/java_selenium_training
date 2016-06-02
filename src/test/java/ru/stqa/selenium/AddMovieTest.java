package ru.stqa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.model.Film;
import ru.stqa.selenium.model.User;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class AddMovieTest extends ru.stqa.selenium.pages.TestBase{

  @BeforeMethod
  public void mayBeLogIn(){
    if (app.getUserHelper().isNotLoggedIn()) {
      User user = new User().setLogin("admin").setPassword("admin");
      app.getUserHelper().loginAs(user);
    }
  }

  @Test
    public void testAddFilmPositive() throws Exception {
    User user = new User().setLogin("admin").setPassword("admin");
    app.getUserHelper().loginAs(user);
    Film revenant = new Film().setTitle("Выживший").setYear("2015");
    app.getFilmHelper().create(revenant);
    assertTrue(app.getFilmHelper().isFilmAdded(revenant));
  }

//  @Test
//  public void testAddMovieNegative() throws Exception {
//    Film noYearFilm = new Film().setTitle("Невыживший");
//    app.getFilmHelper().create(noYearFilm);
//    assertTrue(app.getFilmHelper().isFilmNotAdded());
//    Film noTitleFilm = new Film().setYear("2015");
//    app.getFilmHelper().create(noTitleFilm);
//    assertTrue(app.getFilmHelper().isFilmNotAdded());









//    List<WebElement> moviesBefore = null;
//    List<WebElement> moviesAfter = null;
//    //no "name" parameter
//    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
//    WebDriverWait wait = new WebDriverWait(driver,30);
//    wait.until(ExpectedConditions. //wait for home page to load
//            presenceOfAllElementsLocatedBy(
//                      By.xpath("//div[@class='title']")));
//    moviesBefore = driver.findElements(By.xpath("//div[@class='title']"));
//    driver.findElement(By.cssSelector("img[alt=\"Add movie\"]")).click();
//    driver.findElement(By.name("name")).clear();
//    driver.findElement(By.name("name")).sendKeys("");
//    driver.findElement(By.name("year")).clear();
//    driver.findElement(By.name("year")).sendKeys("2015");
//    driver.findElement(By.id("submit")).click();
//    wait.until(ExpectedConditions.
//            presenceOfElementLocated(By.
//                    xpath("//*[@for='name'][contains(text(),'This field is required')]")));
//
//    //no "year" parameter
//    driver.findElement(By.name("name")).clear();
//    driver.findElement(By.name("name")).sendKeys("Невыживший");
//    driver.findElement(By.name("year")).clear();
//    driver.findElement(By.name("year")).sendKeys("");
//    driver.findElement(By.id("submit")).click();
//
//    wait.until(ExpectedConditions.
//            presenceOfElementLocated(By.
//                    xpath("//*[@for='year'][contains(text(),'This field is required')]")));
//    driver.findElement(By.linkText("Home")).click();
//
//    //check movies counter
//    wait.until(ExpectedConditions. //wait for home page to load
//            presenceOfAllElementsLocatedBy(
//            By.xpath("//div[@class='title']")));
//    moviesAfter = driver.findElements(By.xpath("//div[@class='title']"));
//    if (moviesAfter.size() > moviesBefore.size())
//      throw new Error("Ошибочное добавление фильма");

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
