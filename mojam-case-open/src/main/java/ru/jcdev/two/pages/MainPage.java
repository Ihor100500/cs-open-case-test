package ru.jcdev.two.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends AbstractPage {

    /**
     * Selectors
     */
    @FindBy(xpath = "//div[@class='user__logged-out']")
    private WebElement steamEntranceButton;

    @FindBy(xpath = "//a[@class='logo']")
    private WebElement mainLogo;

    @FindBy(xpath = "//div[@class='modal-holder']")
    private WebElement modalWindow;

    @FindBy(xpath = "//button[@class='btn-close']")
    private WebElement modalWindowCloseIcon;

    @FindBy(xpath = "//div[@class='holder']/a")
    private List<WebElement> casesList;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Clicking on login through steam button
     */
    public void clickOnSteamLogInButton() {
        waitForElementToBeClickable(steamEntranceButton);
        click(steamEntranceButton);
    }

    /**
     * Opening first case in the list
     */
    public void openFirstCasePage() {
        scrollToElement(casesList.get(0));
        waitForElementToBeClickable(casesList.get(0));
        click(casesList.get(0));
    }

    /**
     * Waiting for page download
     */
    public boolean isMainPageDisplayed() {
        waitForPageLoaded();
        waitForElementToBeDisplayed(mainLogo);
        return mainLogo.isDisplayed();
    }
}
