package SwagLabs.stepsdefinitions;

import SwagLabs.steps.SwagLabsUser;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class UserStepsDefinitions {
    @Steps(shared = true)
    SwagLabsUser swagLabsUser;

    @Given("^user navigates to https://www.saucedemo.com/$")
    public void navigateTo() {
        swagLabsUser.navigateTo();
        Serenity.takeScreenshot();
    }

    @And("^login with a standard user$")
    public void loginUser() {
        swagLabsUser.loginUser("standard_user", "secret_sauce");
        Serenity.takeScreenshot();
    }

    @Given("^user goes to 'About' link$")
    public void goToAboutPage() {
        swagLabsUser.goToAboutPage();
        Serenity.takeScreenshot();
    }

    @And("^goes back to products page$")
    public void goBackToMainPage() {
        swagLabsUser.goBackToMainPage();
    }

    @When("^order the products by price -high to low-$")
    public void sortProductsByPrice() {
        swagLabsUser.sortProductsByPrice();
        Serenity.takeScreenshot();
    }

    @And("^add the (.*) most expensive products to the cart$")
    public void addProductsToCart(Integer amount) {
        swagLabsUser.addProductsToCart(amount);
    }

    @And("^check the cart to confirm that it actually contains (.*) products$")
    public void checkAmountOfProductsOnCart(Integer amount) {
        swagLabsUser.checkAmountOfProductsOnCart(amount);
        Serenity.takeScreenshot();
    }

    @Then("^go to the shopping cart, save all the product's information, and display them in the Serenity report$")
    public void goToShoppingCart() {
        swagLabsUser.goToShoppingCart();
        Serenity.takeScreenshot();
    }

    @And("^go to checkout$")
    public void goToCheckout() {
        swagLabsUser.goToCheckout();
        Serenity.takeScreenshot();
    }

    @And("^set the personal information (.*), (.*) and (.*)$")
    public void setPersonalInformation(String firstName, String lastName, String postalCode) {
        swagLabsUser.setPersonalInformation(firstName, lastName, postalCode);
        Serenity.takeScreenshot();
    }

    @And("^check if the total price is correct$")
    public void checkTotalPrice() {
        swagLabsUser.checkTotalPrice();
        Serenity.takeScreenshot();
    }

    @And("^add to serenity report the information about payment, delivery and prices$")
    public void showCheckoutInformation() {
        swagLabsUser.showCheckoutInformation();
        Serenity.takeScreenshot();
    }

    @And("^the user completes the purchase and verify that a success message is displayed$")
    public void completePurchase() {
        swagLabsUser.completePurchase();
        Serenity.takeScreenshot();
    }
}
