package tests.steps.products;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public final class PaginationSteps {

  @Given("a Customer is on the store page")
  public void storePageIsLoaded() {
  }

  @When("a Customer navigates {string} through page results using {string}")
  public void navigateThroughProductResults(String direction, String control) {
  }

  @And("the corresponding set of products should be displayed")
  public void populatesANewSetOfProducts() {
  }

  @And("the pagination controls should reflect the current page {string}")
  public void paginationControlsReflectPageResults(String pageNumber) {
  }
}
