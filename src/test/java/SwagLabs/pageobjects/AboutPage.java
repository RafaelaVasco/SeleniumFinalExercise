package SwagLabs.pageobjects;

import net.thucydides.core.pages.PageObject;
import org.junit.Assert;

public class AboutPage extends PageObject {

    public void checkAboutLink() {
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertEquals("https://saucelabs.com/", currentUrl);
    }

    public void goBackToPreviousPage() {
        getDriver().navigate().back();
    }
}
