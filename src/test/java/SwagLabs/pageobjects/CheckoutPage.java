package SwagLabs.pageobjects;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckoutPage extends PageObject {
    @FindBy(xpath = "//input[@id = 'first-name']")
    WebElementFacade firstName_input;

    @FindBy(xpath = "//input[@id = 'last-name']")
    WebElementFacade lastName_input;

    @FindBy(xpath = "//input[@id = 'postal-code']")
    WebElementFacade postalCode_input;

    @FindBy(xpath = "//input[@id = 'continue']")
    WebElementFacade continue_button;

    @FindBy(xpath = "//div[@class = 'summary_subtotal_label']")
    WebElementFacade subTotalPrice;

    @FindBy(xpath = "//div[@class = 'summary_tax_label']")
    WebElementFacade taxPrice;

    @FindBy(xpath = "//div[contains(@class, 'summary_total_label')]")
    WebElementFacade totalPrice;

    @FindBy(xpath = "//div[@class = 'summary_info']/div")
    List<WebElementFacade> checkoutInformation_elements;

    @FindBy(xpath = "//button[@id = 'finish']")
    WebElementFacade finish_button;

    public void setUserInformation(String firstName, String lastName, String postalCode) {
        firstName_input.sendKeys(firstName);
        lastName_input.sendKeys(lastName);
        postalCode_input.sendKeys(postalCode);

        continue_button.click();
    }

    public void checkTotalPrice() {
        DecimalFormat df = new DecimalFormat("0.00");
        Double subTotal = Double.parseDouble(subTotalPrice.getText().split("\\$")[1]);

        /* compare sum(products price) = subtotal */
        List<WebElementFacade> productsOnCart = withTimeoutOf(10, TimeUnit.SECONDS).findAll(By.xpath("//div[@class = 'cart_item_label']"));
        Double productsTotalPrice = 0.00;
        for(WebElementFacade product : productsOnCart) {
            WebElement price_element = product.findElement(By.className("inventory_item_price"));
            Double price = Double.parseDouble(price_element.getText().split("\\$")[1]);
            productsTotalPrice = Double.sum(productsTotalPrice, price);
        }
        Assert.assertTrue(Double.compare(subTotal, productsTotalPrice) == 0);

        /* compare subtotal + tax = total */
        Double tax = Double.parseDouble(taxPrice.getText().split("\\$")[1]);
        Double total = Double.parseDouble(totalPrice.getText().split("\\$")[1]);
        Double subtotalTax_sum = Double.parseDouble(df.format(Double.sum(subTotal, tax)));

        Assert.assertTrue(Double.compare(total, subtotalTax_sum) == 0);
    }

    public void showCheckoutInformation() {
        String checkoutInformation = "";
        for(WebElementFacade checkoutInfo : checkoutInformation_elements) {
            if(!checkoutInfo.hasClass("cart_footer")) {
                checkoutInformation += checkoutInfo.getText() + "\n";
                if(checkoutInfo.hasClass("summary_value_label")) {
                    checkoutInformation += "\n";
                }
            }
        }

        Serenity.recordReportData().withTitle("Checkout information").andContents(checkoutInformation);
    }

    public void completePurchase() {
        finish_button.click();

        WebElementFacade success_label = withTimeoutOf(10, TimeUnit.SECONDS).find(By.xpath("//h2[@class = 'complete-header']"));

        String successMessage = "Thank you for your order!";
        Assert.assertEquals(successMessage, success_label.getText());
    }
}
