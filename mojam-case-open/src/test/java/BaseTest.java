import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.jcdev.two.Application;
import ru.jcdev.two.utils.SelServer;

import static org.junit.Assert.assertTrue;
import static ru.jcdev.two.utils.ApplicationManager.build;
import static ru.jcdev.two.utils.ApplicationManager.getProperties;

public class BaseTest {

    private static SelServer server;
    private static WebDriver driver;
    protected Application app;

    @Before
    public void setup() {
        // start/set the server
        server = new SelServer();
        server.startSelServer();
        assertTrue(server.isServerUp());

        // set the driver and load page of test
        driver = build(getProperties().getProperty("browser"));
        driver.manage().window().maximize();
        driver.get(getProperties().getProperty("web.baseUrl"));

        // set the application to play and login to steam
        app = new Application(driver);
        String mainWindow = driver.getWindowHandle();
        app.mainPage().clickOnSteamLogInButton();
        String childWindow = driver.getWindowHandles().stream().filter(window -> !window.equals(mainWindow)).findFirst().get();
        driver.switchTo().window(childWindow);
        app.steamLoginPage().loginToSteam(getProperties().getProperty("web.adminLogin"), getProperties().getProperty("web.adminPassword"));
        app.mainPage().stopThread(5000);
        driver.switchTo().window(mainWindow);
    }

    /**
     * quits the driver
     * kills the selenium server
     */
    @After
    public void teardown() {
        driver.quit();
        server.stopSelServer();
    }

}
