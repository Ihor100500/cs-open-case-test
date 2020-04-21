package ru.jcdev.two.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SteamLoginPage extends AbstractPage {

    /**
     * Selectors
     */
    @FindBy(id = "steamAccountName")
    private WebElement steamUsername;

    @FindBy(id = "steamPassword")
    private WebElement steamPassword;

    @FindBy(id = "imageLogin")
    private WebElement signInButton;

    public SteamLoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Login to steam
     */
    public void loginToSteam(String userId, String password) {
        waitForElementToBeClickable(steamUsername);
        type(steamUsername, userId);
        waitForElementToBeClickable(steamPassword);
        type(steamPassword, password);
        waitForElementToBeClickable(signInButton);
        click(signInButton);
    }
}
