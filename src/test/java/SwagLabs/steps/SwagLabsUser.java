package SwagLabs.steps;

import SwagLabs.pageobjects.*;
import net.serenitybdd.core.steps.ScenarioActor;
import net.thucydides.core.annotations.Steps;

public class SwagLabsUser extends ScenarioActor {
    String actor;

    @Steps(shared = true)
    LoginPage loginPage;

    @Steps(shared = true)
    MainPage mainPage;

    @Steps(shared = true)
    AboutPage aboutPage;

    @Steps(shared = true)
    ShoppingCartPage shoppingCartPage;

    @Steps(shared = true)
    CheckoutPage checkoutPage;

    public void navigateTo() {
        loginPage.setDefaultBaseUrl("https://www.saucedemo.com/");
        loginPage.open();
    }

    public void loginUser(String username, String password) {
        loginPage.loginUser(username, password);
    }

    public void goToAboutPage() {
        mainPage.goToAboutPage();
        aboutPage.checkAboutLink();
    }

    public void goBackToMainPage() {
        aboutPage.goBackToPreviousPage();
    }

    public void sortProductsByPrice() {
        mainPage.sortProductsByPrice_highToLow();
    }

    public void addProductsToCart(Integer amount) {
        mainPage.addProductsToCart(amount);
    }

    public void checkAmountOfProductsOnCart(Integer amount) {
        mainPage.checkAmountOfProductsOnCart(amount);
    }

    public void goToShoppingCart() {
        mainPage.goToShoppingCart();
        shoppingCartPage.saveProductsInformation();
    }

    public void goToCheckout() {
        shoppingCartPage.goToCheckout();
    }

    public void setPersonalInformation(String firstName, String lastName, String postalCode) {
        checkoutPage.setUserInformation(firstName, lastName, postalCode);
    }

    public void checkTotalPrice() {
        checkoutPage.checkTotalPrice();
    }

    public void showCheckoutInformation() {
        checkoutPage.showCheckoutInformation();
    }

    public void completePurchase() {
        checkoutPage.completePurchase();
    }
}
