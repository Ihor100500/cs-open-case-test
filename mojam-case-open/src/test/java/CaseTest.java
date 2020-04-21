import org.hamcrest.collection.IsMapContaining;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CaseTest extends BaseTest {

    @Test
    public void firstTest() {
        assertTrue("Main page is not displayed after login", app.mainPage().isMainPageDisplayed());
        app.mainPage().openFirstCasePage();
        Map<String, String> possibleWinItems = app.casePage().collectItemsInfo();
        app.casePage().openCaseFast();
        String receivedItemImage = app.casePage().getWinItemImage();
        String receivedItemName = app.casePage().getWinItemName();
        assertThat(possibleWinItems,
                IsMapContaining.hasEntry(receivedItemImage, receivedItemName));
    }
}
