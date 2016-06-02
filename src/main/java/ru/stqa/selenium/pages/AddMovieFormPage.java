package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by Сергей on 01.06.2016.
 */
public class AddMovieFormPage extends AnyPage{
    public AddMovieFormPage(PageManager pages) {
        super(pages);
    }
    public AddMovieFormPage ensurePageLoaded(){
        super.ensurePageLoaded();
        wait.until(presenceOfElementLocated(By.id("updateform")));
        return this;
    }
    @FindBy(name = "name")
    private WebElement titleNameInput;
    @FindBy(name = "year")
    private WebElement yearInput;
    @FindBy(name = "submit")
    private WebElement submitButton;

    public void setTitleNameInput(String text) {
        titleNameInput.sendKeys(text);
    }

    public void setYearInput(String text) {
        yearInput.sendKeys(text);
    }
    public void clickSubmitButton() {
        submitButton.click();
    }
}
