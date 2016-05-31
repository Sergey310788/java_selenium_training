package ru.stqa.selenium.applogic2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.selenium.applogic.FilmHelper;
import ru.stqa.selenium.applogic.NavigationHelper;
import ru.stqa.selenium.model.Film;
import ru.stqa.selenium.pages.AddMovieFormPage;

public class FilmHelper2 extends DriverBasedHelper implements FilmHelper {

  public FilmHelper2(ApplicationManager2 manager) {
    super(manager.getWebDriver());
  }

  @Override
  public void create(Film film) {
      AddMovieFormPage addMovieFormPage = pages.internalPage.ensurePageLoaded().clickAddMovieButton();
        addMovieFormPage.ensurePageLoaded().setTitleNameInput(film.getTitle());
        pages.addMovieFormPage.setYearInput(film.getYear());
        pages.addMovieFormPage.clickSubmitButton();
  }

  @Override
  public void delete(Film film) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<Film> search(String title) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isFilmAdded(Film film) {
      driver.findElement(By.linkText("Home")).click();
        wait.until(ExpectedConditions.
            presenceOfAllElementsLocatedBy(
                    By.xpath("//div[@class='title']")));
      try{
          driver.findElement(By.xpath("//div[contains(text(),'"+film.getTitle()+"')]"));
      }
      catch (Exception e){
          return false;
      }
      return true;
  }
}
