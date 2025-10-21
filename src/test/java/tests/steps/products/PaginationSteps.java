package tests.steps.products;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public final class PaginationSteps {

  @Given("a Customer is on the store page")
  public void customer_is_on_the_store_page() {
  }

  @When("a Customer navigates {string} through page results using {string}")
  public void customer_navigates_through_page_results_using(String direction, String control) {
  }

  @And("the corresponding set of products should be displayed")
  public void corresponding_set_of_products_should_be_displayed() {
  }

  @And("the pagination controls should reflect the current page {string}")
  public void pagination_controls_should_reflect_the_current_page(String pageNumber) {
  }
}
