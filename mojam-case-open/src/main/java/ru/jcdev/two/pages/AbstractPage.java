package ru.jcdev.two.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPage {

    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Scroll to element.
     *
     * @param element the webElement
     */
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Scroll to bottom of the page.
     */
    public void scrollToBottom() {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * Switch between tabs
     *
     * @param tabNumber number of desired tab
     */
    public void switchToTab(int tabNumber) {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabNumber));
    }

    /**
     * Move to element.
     *
     * @param element the webElement
     */
    public void moveToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }


    /**
     * Click.
     *
     * @param webElement the web element
     */
    public void click(WebElement webElement) {
        waitForElementToBeClickable(webElement);
        webElement.click();
    }

    /**
     * Type.
     *
     * @param element the webElement
     * @param text    the text
     */
    public void type(WebElement element, String text) {
        click(element);
        if (text != null) {
            String existingText = element.getAttribute("value");
            if (!text.equals(existingText)) {
                element.clear();
                element.sendKeys(text);
            }
        }
    }

    /**
     * Wait for page loaded.
     */
    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> pageLoadCondition = driver -> {
            assert driver != null;
            return ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState")
                    .equals("complete");
        };
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(pageLoadCondition);
    }

    /**
     * Wait for element to be clickable.
     *
     * @param webElement the web element
     */
    public void waitForElementToBeClickable(WebElement webElement) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 15);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    /**
     * Wait for element to be displayed.
     *
     * @param webElement the web element
     */
    public void waitForElementToBeDisplayed(WebElement webElement) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 15);
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * Stop thread.
     *
     * @param millis the millis
     */
    public void stopThread(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
