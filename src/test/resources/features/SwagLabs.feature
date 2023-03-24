Feature: Buy on Swag Labs

  Background:

    Given user navigates to https://www.saucedemo.com/
    And login with a standard user

    Scenario Outline: Buying products

      Given user goes to 'About' link
      And goes back to products page
      When order the products by price -high to low-
      And add the <amount> most expensive products to the cart
      And check the cart to confirm that it actually contains <amount> products
      Then go to the shopping cart, save all the product's information, and display them in the Serenity report
      And go to checkout
      And set the personal information <firstName>, <lastName> and <postalCode>
      And check if the total price is correct
      And add to serenity report the information about payment, delivery and prices
      And the user completes the purchase and verify that a success message is displayed

      Examples:
      |amount|firstName|lastName    |postalCode|
      |4     |Rafaela  |Vasconcellos|11300     |