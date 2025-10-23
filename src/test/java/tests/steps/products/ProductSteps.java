package tests.steps.products;

import tests.steps.CommonSteps;

import com.pages.product.ProductsPage;
import com.utils.RunContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;

public final class ProductSteps {

  @Given("a Customer is on store products page")
  public void customer_is_on_store_products_page() {
    RunContext.setPage(CommonSteps.ensureProductPageIsAccessible("store"));
  }

  @Then("a message {string} should be displayed")
  public void message_should_be_displayed(String expectedResultsCountMsg) {
    String actualMsg = ((ProductsPage) RunContext.getPage()).getResultsCount();
    assertEquals(expectedResultsCountMsg, actualMsg);
  }
}
