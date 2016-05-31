package ru.stqa.selenium.applogic;

import java.util.List;

import ru.stqa.selenium.model.Film;

public interface FilmHelper {

	void create(Film film);
	void delete(Film film);
	List<Film> search(String title);

	boolean isFilmAdded(Film film);

	boolean isFilmNotAdded();

	boolean noFilmsAdded();

	boolean isFilmFinded();

	boolean isFilmNotFinded();

	String findFilmTitle();

	boolean isFilmDeleted();
}
