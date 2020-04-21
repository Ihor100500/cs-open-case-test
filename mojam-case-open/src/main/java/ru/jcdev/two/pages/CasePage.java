package ru.jcdev.two.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CasePage extends AbstractPage {
    /**
     * Selectors
     */
    @FindBy(xpath = "//div[@class='case-items__item-name']")
    private List<WebElement> caseItemNames;

    @FindBy(xpath = "//div[@class='case-items__item-title']")
    private List<WebElement> caseItemTitles;

    @FindBy(xpath = "//div[@class='case-items__item-img']/img")
    private List<WebElement> caseItemImages;

    @FindBy(xpath = "//button[@class='case-item__open-case-fast-btn']")
    private WebElement fastButton;

    @FindBy(xpath = "//button[@class='case-win-block__open-again-btn']")
    private WebElement openAgainButton;

    @FindBy(xpath = "//img[@class='case-win-block__img']")
    private WebElement winItemImage;

    @FindBy(xpath = "//p[@class='case-win-block__name']")
    private WebElement winItemName;

    @FindBy(xpath = "//p[@class='case-win-block__type']")
    private WebElement winItemTitle;

    private static final String imageRegex = "(\\/[0-9]*x[0-9]*.[a-z]*)";

    public CasePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Open case using fast button
     */
    public void openCaseFast() {
        scrollToElement(fastButton);
        waitForElementToBeClickable(fastButton);
        click(fastButton);
    }

    /**
     * Collecting items information
     */
    public Map<String, String> collectItemsInfo() {
        scrollToBottom();
        stopThread(3000);
        List<String> titles = caseItemTitles.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        List<String> imageLinks = caseItemImages.stream()
                .map(webElement -> webElement.getAttribute("currentSrc").replaceAll(imageRegex, ""))
                .collect(Collectors.toList());
        return IntStream.range(0, imageLinks.size())
                .boxed()
                .collect(Collectors.toMap(imageLinks::get, titles::get));
    }

    /**
     * Getting image of opened item
     */
    public String getWinItemImage() {
        waitForElementToBeDisplayed(winItemImage);
        return winItemImage.getAttribute("currentSrc").replaceAll(imageRegex, "");
    }

    /**
     * Getting title of opened item
     */
    public String getWinItemTitle() {
        waitForElementToBeDisplayed(winItemTitle);
        return winItemTitle.getText();
    }

    /**
     * Getting name of opened item
     */
    public String getWinItemName() {
        waitForElementToBeDisplayed(winItemName);
        return winItemName.getText();
    }


    private List<String> concatenateStrings(List<String> names, List<String> titles) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            result.add(names.get(i).concat(titles.get(i)));
        }
        return result;
    }
}
