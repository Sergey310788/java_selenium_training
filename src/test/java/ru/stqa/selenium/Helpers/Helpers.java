package ru.stqa.selenium.Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.TestNgTestBase;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Сергей on 01.06.2016.
 */
public class Helpers extends TestNgTestBase {
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
    public static void testLogin(WebDriver driver) throws Exception {
        driver.get(baseUrl + "/php4dvd/");
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("submit")).click();

}
