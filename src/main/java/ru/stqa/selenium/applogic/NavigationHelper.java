package ru.stqa.selenium.applogic;

import ru.stqa.selenium.model.Film;

public interface NavigationHelper {

  void openMainPage();
  void openRelativeUrl(String url);
  void gotoUserProfilePage();
  void gotoUserManagementPage();

  void gotoAddFilmForm();

  boolean isNotHomePage();

  void gotoFilmPage(Film film);
}
