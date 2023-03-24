Feature: Buy on Swag Labs

  Background:

    Given user navigates to https://www.saucedemo.com/
    And login with a standard user

    Scenario Outline: Buying products

      Given user goes to 'About' link
      And goes back to products page
      When orders the products by price -high to low-
      And adds the <amount> most expensive products to the cart
      And checks the cart to confirm that it actually contains <amount> products
      Then goes to the shopping cart, save all the product's information, and display them in the Serenity report
      And goes to checkout
      And sets the personal information <firstName>, <lastName> and <postalCode>
      And checks if the total price is correct
      And adds to serenity report the information about payment, delivery and prices
      And the user completes the purchase and verify that a success message is displayed

      Examples:
      |amount|firstName|lastName    |postalCode|
      |4     |Rafaela  |Vasconcellos|11300     |
      |2     |Franco   |Maio        |11200     |