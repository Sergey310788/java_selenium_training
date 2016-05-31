package ru.stqa.selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.selenium.model.Film;
import ru.stqa.selenium.pages.TestBase;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class DeleteMovieTest extends TestNgTestBase {
//@BeforeClass
//public void mayBeFilmPresent(){
//  SearchMovieTest.mayBeLoginAndFilmPresent(app);
//}
  @Test
    public void testDeleteMovie() throws Exception {
//    String filmTitle = app.getFilmHelper().findFilmTitle();
//    Film film = new Film().setTitle(filmTitle);
//    app.getNavigationHelper().gotoFilmPage(film);
//    app.getFilmHelper().delete(film);
//    assertTrue(app.getFilmHelper().isFilmDeleted());



    //login
    LoginTest.testLogin(driver);

    //delete movie
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions. //wait for home page to load
            presenceOfAllElementsLocatedBy(
            By.xpath("//div[@class='title']")));
    List<WebElement> movieBoxesBefore = driver.findElements(By.xpath("//div[@class='title']"));
    driver.findElement(By.xpath("//*[@class='movie_box']")).click();
    driver.findElement(By.xpath("//*[@title='Remove']")).click();
    wait.until(ExpectedConditions.alertIsPresent());
    Alert alert = driver.switchTo().alert();
    alert.accept();
    wait.until(ExpectedConditions.visibilityOfElementLocated( //wait for home page to load
            By.xpath("//a[contains(text(),'Home')]")));
    //check movies counter
    driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
    wait.until(ExpectedConditions. //wait for home page to load
            presenceOfAllElementsLocatedBy(
            By.xpath("//div[@class='title']")));
    List<WebElement> movieBoxesAfter = driver.findElements(By.xpath("//div[@class='title']"));
    if (movieBoxesAfter.size() != movieBoxesBefore.size() - 1)
      throw new Error("Фильм не удален");
  }
}
