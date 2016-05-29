package ru.stqa.selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchMovieTest extends TestNgTestBase{

  @Test
    public void testSearchMovie() throws Exception {
    //login
    LoginTest.testLogin(driver);

    //search movie
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    WebDriverWait wait = new WebDriverWait(driver, 30);
    List<WebElement> movieBoxesBefore = driver.findElements(By.xpath("//*[@class='movie_box']"));
    driver.findElement(By.xpath("//*[@class='movie_box']")).click();
    driver.findElement(By.xpath("//*[@title='Remove']")).click();
    wait.until(ExpectedConditions.alertIsPresent());
    Alert alert = driver.switchTo().alert();
    alert.accept();

    //check movies counter
    driver.findElement(By.linkText("Home")).click();
    wait.until(ExpectedConditions. //wait for home page to load
            presenceOfElementLocated(By.
                    xpath("//*[@class='searchbox']")));
    List<WebElement> movieBoxesAfter = driver.findElements(By.xpath("//*[@class='movie_box']"));
    if (movieBoxesAfter.size() != movieBoxesBefore.size() - 1)
      throw new Error("Фильм не удален");
  }
}
