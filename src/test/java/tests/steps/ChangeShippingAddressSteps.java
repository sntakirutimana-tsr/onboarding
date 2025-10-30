package tests.steps;

import com.utils.RunContext;
import io.cucumber.java.en.*;
import org.junit.Assert;
import com.pages.checkout.CheckoutPage;
import tests.steps.CommonSteps;

import java.util.List;
import java.util.stream.Collectors;
import io.cucumber.datatable.DataTable;

public class ChangeShippingAddressSteps {


  @Given("Customer is on the checkout page")
  public void customer_is_on_the_checkout_page() {
    CommonSteps.ensureHomepageIsAccessible();
    ((CheckoutPage) RunContext.getPage()).navigateToCheckout();
  }

  @Given("the {string} checkbox is checked")
  public void the_checkbox_is_checked(String checkboxLabel) {
    ((CheckoutPage) RunContext.getPage()).checkShipToDifferentAddress();
  }

  @Given("the shipping address form is displayed")
  public void the_shipping_address_form_is_displayed() {
    Assert.assertTrue("Shipping form should be visible", ((CheckoutPage) RunContext.getPage()).isShippingFormDisplayed());
  }

  @When("Customer clicks on the {string} button without filling the required fields:")
  public void customer_clicks_place_order(String buttonLabel, DataTable table) {
    ((CheckoutPage) RunContext.getPage()).clickPlaceOrder();
  }

  @Then("Customer should see the following error messages displayed at the top of the billing form:")
  public void customer_should_see_error_messages(DataTable expectedErrors) {
    List<String> expected = expectedErrors.asList(String.class);
    List<String> actual = ((CheckoutPage) RunContext.getPage()).getErrorMessages()
      .stream().map(e -> e.getText().trim()).collect(Collectors.toList());

    for (String error : expected) {
      Assert.assertTrue("Missing error: " + error, actual.contains(error));
    }
  }
}