package steps.products;

import steps.CommonSteps;

import pages.products.components.ProductList;
import pages.products.ProductPage;

import utils.RunContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.Assert.*;

public final class ProductSteps {

  @Given("a Customer is on store products page")
  public void customer_is_on_store_products_page() {
    RunContext.currentPage = CommonSteps.ensureProductPageIsAccessible("store");
  }

  @Then("a message {string} should be displayed")
  public void message_should_be_displayed(String expectedResultsCountMsg) {
    ProductList productList = ((ProductPage) RunContext.currentPage).productList();
    assertEquals(expectedResultsCountMsg, productList.getResultsCountMsg());
  }
}
