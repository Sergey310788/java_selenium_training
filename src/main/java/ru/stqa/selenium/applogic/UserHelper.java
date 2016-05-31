package ru.stqa.selenium.applogic;

import ru.stqa.selenium.model.User;

public interface UserHelper {

	void loginAs(User user);
	void logout();
	boolean isLoggedIn();
	boolean isLoggedInAs(User user);
	boolean isNotLoggedIn();

}
