package ru.stqa.selenium.pages;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import ru.stqa.selenium.applogic.ApplicationManager;
//import ru.stqa.selenium.applogic0.ApplicationManager0;
//import ru.stqa.selenium.applogic1.ApplicationManager1;
import ru.stqa.selenium.applogic2.ApplicationManager2;

public class TestBase {

  protected ApplicationManager2 app;

	@BeforeClass
	public void init() {
		app = new ApplicationManager2();
	}
	
	@AfterSuite
	public void stop() {
	  app.stop();
	}

}
