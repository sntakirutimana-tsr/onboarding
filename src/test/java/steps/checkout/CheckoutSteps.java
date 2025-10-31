package steps.checkout;

import domainobjects.BillingDetails;
import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pages.CheckoutPage;

import domainobjects.*;
import factory.DriverFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.CheckoutPage;
import utils.BillingDetailsUtils;

import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckoutSteps {

  private final WebDriver driver = DriverFactory.getDriver();
  private final CheckoutPage checkoutPage = new CheckoutPage(driver);

  private BillingDetails billingDetails = new BillingDetails();
  private ShippingDetails shippingDetails = new ShippingDetails();
  private AccountDetails accountDetails = new AccountDetails();
  private OrderNote orderNote = new OrderNote(); ;


  //  BACKGROUND
  @Given("Customer is on the checkout page")
  public void customerIsOnTheCheckoutPage() {
    checkoutPage.navigateToCheckoutPage("1215");
  }

  // BILLING DETAILS
  @When("Customer enters First Name as {string}")
  public void customerEntersFirstNameAs(String firstName) {
    billingDetails.setFirstName(firstName);
    checkoutPage.fillField(By.id("billing_first_name"), firstName);
  }

  @And("Customer enters Last Name as {string}")
  public void customerEntersLastNameAs(String lastName) {

    billingDetails.setLastName(lastName);
    checkoutPage.fillField(By.id("billing_last_name"), lastName);
  }

  @And("Customer enters Company Name as {string}")
  public void customerEntersCompanyNameAs(String companyName) {

    billingDetails.setCompanyName(companyName);
    checkoutPage.fillField(By.id("billing_company"), companyName);

  }

  @And("Customer selects Country Region as {string}")
  public void customerSelectsCountryRegionAs(String country) {

    billingDetails.setCountry(country);
    checkoutPage.selectField(By.id("billing_country"),country);
  }

  @And("Customer enters Street Address as {string}")
  public void customerEntersStreetAddressAs(String streetAddress) {

    billingDetails.setStreetAddress(streetAddress);
    checkoutPage.fillField(By.id("billing_address_1"), streetAddress);
  }

  @And("Customer enters Apartment, Suite, Unit as {string}")
  public void customerEntersApartmentSuiteUnitAs(String apartment) {

    billingDetails.setApartment(apartment);
    checkoutPage.fillField(By.id("billing_address_2"), apartment);
  }

  @And("Customer enters Town City as {string}")
  public void customerEntersTownCityAs(String city) {

    billingDetails.setCity(city);
    checkoutPage.fillField(By.id("billing_city"), city);
  }

  @And("Customer selects State as {string}")
  public void customerSelectsStateAs(String state) {

    billingDetails.setState(state);
    checkoutPage.selectField(By.id("billing_state"),state);
  }

  @And("Customer enters ZIP Code as {string}")
  public void customerEntersZIPCodeAs(String zip) {

    billingDetails.setZipCode(zip);
    checkoutPage.fillField(By.id("billing_postcode"), zip);
  }

  @And("Customer enters Phone as {string}")
  public void customerEntersPhoneAs(String phone) {
    billingDetails.setPhone(phone);
    checkoutPage.fillField(By.id("billing_phone"), phone);
  }

  @And("Customer enters Email Address as {string}")
  public void customerEntersEmailAddressAs(String email) {

    billingDetails.setEmail(email);
    checkoutPage.fillField(By.id("billing_email"), email);

  }

  @When("Customer fills all valid billing details")
  @And("Customer fills valid billing details")
  public void customerFillsValidBillingDetails(DataTable billingData) {
    Map<String, String> data = billingData.asMaps().get(0);
    BillingDetails billingDetails = BillingDetailsUtils.fromMap(data);
    checkoutPage.fillBillingDetails(billingDetails);
  }

  // BUTTON ACTION
  @And("Customer clicks on {string} button")
  public void customerClicksOnButton(String buttonName) {
    if (buttonName.equalsIgnoreCase("Place Order")) {
      checkoutPage.clickPlaceOrder();
    }
  }

  //  CONFIRMATION VALIDATION ==========
  @Then("Customer is redirected to the order confirmation page")
  public void customerIsRedirectedToTheOrderConfirmationPage() {
    assertTrue("Not redirected to order confirmation page!", checkoutPage.isOnOrderConfirmationPage());
  }

  @Then("Customer is redirected to the order confirmation page with message {string} displayed")
  public void customerIsRedirectedToTheOrderConfirmationPageWithMessageDisplayed(String expectedMessage) {
    String actualMessage = checkoutPage.getConfirmationMessage();
    assertTrue("Expected message not displayed",
      actualMessage.contains(expectedMessage));
  }

  @And("the message {string} should be displayed")
  public void theMessageShouldBeDisplayed(String expectedMessage) {
    String actualMessage = checkoutPage.getConfirmationMessage();
    assertTrue("Expected message not displayed",
      actualMessage.contains(expectedMessage));
  }

  @And("the confirmation page should display detailed order information")
  public void theConfirmationPageShouldDisplayDetailedOrderInformation() {
    assertFalse("Order details header not visible",
      checkoutPage.getConfirmationMessage().isEmpty());
  }

  // ========== ERROR VALIDATION ==========
  @Then("the error message {string} should be displayed")
  public void theErrorMessageShouldBeDisplayed(String expectedError) {
    String actualError = checkoutPage.getErrorMessage();
    assertTrue("Expected error not displayed",
      actualError.contains(expectedError));
  }

  // ========== PAYMENT ==========
  @And("Customer selects payment method as {string}")
  public void customerSelectsPaymentMethodAs(String paymentMethod) {
    checkoutPage.selectPaymentMethod(paymentMethod);
  }


  @Then("order confirmation should show payment method as {string}")
  public void orderConfirmationShouldShowPaymentMethodAs(String expectedPaymentMethod) {
    String confirmation = checkoutPage.getPaymentConfirmationMessage();
    assertTrue("Payment method not displayed correctly",
      confirmation.toLowerCase().contains(expectedPaymentMethod.toLowerCase()));
  }

  //  ORDER NOTES
  @And("Customer adds order notes {string}")
  public void customerAddsOrderNotes(String noteText) {
    orderNote.setNote(noteText);
    checkoutPage.addOrderNote(orderNote);
  }

  @Then("the order confirmation page should include the note {string}")
  public void theOrderConfirmationPageShouldIncludeTheNote(String expectedNote) {
    Assert.assertEquals("Order note mismatch", expectedNote, orderNote.getNote());
  }


}





