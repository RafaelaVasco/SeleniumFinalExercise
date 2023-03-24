package SwagLabs.pageobjects;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

public class MainPage extends PageObject {
    @FindBy(id = "react-burger-menu-btn")
    WebElementFacade btnMenu;

    @FindBy(className = "product_sort_container")
    WebElementFacade selectSort;

    @FindBy(className = "shopping_cart_badge")
    List<WebElementFacade> lblShoppingCartBadge;

    @FindBy(xpath = "//a[@class = 'shopping_cart_link']")
    WebElementFacade shoppingCart_button;

    static List<WebElementFacade> products;

    public void goToAboutPage() {
        btnMenu.click();
        WebElementFacade aboutLink = withTimeoutOf(5, TimeUnit.SECONDS).find(By.xpath("//a[text() = 'About']"));
        aboutLink.click();
    }

    public void sortProductsByPrice_highToLow() {
        Select dropdown = new Select(selectSort);
        dropdown.selectByValue("hilo");

        products = withTimeoutOf(10, TimeUnit.SECONDS).findAll(By.xpath("//div[@class = 'inventory_item']"));
    }

    public void addProductsToCart(Integer amount) {
        for (Integer n = 0; n < amount; n++) {
            WebElement addToCart_button = products.get(n).findElement(By.xpath("//button[text() = 'Add to cart']"));
            addToCart_button.click();
        }
    }

    public void checkAmountOfProductsOnCart(Integer amount) {
        Assert.assertTrue(parseInt(lblShoppingCartBadge.get(0).getText()) == amount);
    }

    public void goToShoppingCart() {
        shoppingCart_button.click();
    }
}
