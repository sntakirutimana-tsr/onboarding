package tests.steps.products;

import com.pages.product.ProductsPage;
import com.utils.RunContext;

import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;

public final class ProductSteps {

  @Then("a message {string} should be displayed")
  public void message_should_be_displayed(String expectedResultsCountMsg) {
    String actualMsg = ((ProductsPage) RunContext.getPage()).getResultsCount();
    assertEquals(expectedResultsCountMsg, actualMsg);
  }
}
