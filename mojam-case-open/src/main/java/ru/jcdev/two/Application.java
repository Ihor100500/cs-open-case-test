package ru.jcdev.two;

import org.openqa.selenium.WebDriver;
import ru.jcdev.two.pages.CasePage;
import ru.jcdev.two.pages.MainPage;
import ru.jcdev.two.pages.SteamLoginPage;

public class Application {

    private WebDriver driver;

    public Application(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage mainPage() {
        return new MainPage(driver);
    }

    public SteamLoginPage steamLoginPage() {
        return new SteamLoginPage(driver);
    }

    public CasePage casePage() {
        return new CasePage(driver);
    }
}
