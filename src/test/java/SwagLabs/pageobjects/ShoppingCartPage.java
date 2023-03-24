package SwagLabs.pageobjects;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShoppingCartPage extends PageObject {
    @FindBy(xpath = "//button[@id = 'checkout']")
    WebElementFacade checkout_button;

    public void saveProductsInformation() {
        List<WebElementFacade> productsOnCart = withTimeoutOf(10, TimeUnit.SECONDS).findAll(By.xpath("//div[@class = 'cart_item_label']"));
        List<String> productsInformation = new ArrayList<String>();

        for(WebElementFacade product : productsOnCart) {
            WebElement name_element = product.findElement(By.className("inventory_item_name"));
            String name = name_element.getText();

            WebElement description_element = product.findElement(By.className("inventory_item_desc"));
            String description = description_element.getText();

            WebElement price_element = product.findElement(By.className("inventory_item_price"));
            String price = price_element.getText();

            String productInformation = name + '\n' + description + '\n' + price;
            productsInformation.add(productInformation);
        }

        Serenity.recordReportData().withTitle("Details of products on shopping cart").andContents(String.join("\n\n", productsInformation));
    }

    public void goToCheckout() {
        checkout_button.click();
    }
}
