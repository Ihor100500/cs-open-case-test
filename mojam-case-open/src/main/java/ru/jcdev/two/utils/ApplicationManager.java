package ru.jcdev.two.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationManager {

    private static Properties properties;
    private static Logger logger = Logger.getLogger(ApplicationManager.class.getName());

    static {
        properties = new Properties();
        try (InputStream stream = Files.newInputStream(Paths.get(System.getProperty("user.dir") + "/src/main/resources/props.properties"))) {
            properties.load(stream);
        } catch (IOException e) {
            logger.log(Level.INFO, e.getCause() + " " + e.getMessage());
        }
    }

    /**
     * Some kind of page factory
     */
    public static WebDriver build(String browser) {
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver");
                ChromeOptions chromeOptions = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.default_content_settings.popups", 0);
                chromeOptions.setExperimentalOption("prefs", prefs);
                return new ChromeDriver(chromeOptions);
            case "firefox":
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/geckodriver");
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("dom.webnotifications.enabled", false);
                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("marionette", true);
                capabilities.setCapability(FirefoxDriver.PROFILE, profile);
                return new FirefoxDriver(capabilities);
        }
        return new SafariDriver();
    }

    public static Properties getProperties() {
        return properties;
    }
}
