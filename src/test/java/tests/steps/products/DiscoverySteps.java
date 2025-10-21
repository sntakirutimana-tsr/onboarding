package tests.steps.products;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public final class DiscoverySteps {

  @Given("a Customer is on {string} products page")
  public void customer_is_on_products_page(String pageName) {
  }

  @When("a Customer enters search keyword as {string}")
  public void customer_enters_search_keyword_as(String keyword) {
  }

  @And("clicks the ❝SEARCH❞ button")
  public void click_the_SEARCH_button() {
  }

  @And("only products containing the {string} in their names should be displayed")
  public void only_products_whose_names_contain_the_keyword_should_be_displayed(String keyword) {
  }

  @Then("a message ❝No products were found matching your selection.❞ should be displayed")
  public void message_no_products_were_found_should_be_displayed() {}

  @When("a Customer sorts products by {string}")
  public void customer_sorts_products_by(String criterion) {}

  @Then("the products should be re-arranged by {string}")
  public void products_should_be_reordered_by(String criterion) {}

  @When("a Customer selects an invalid sorting criterion as {string}")
  public void customer_selects_invalid_sorting_criterion_as(String criterion) {}

  @Then("the system should ignore the invalid option, maintaining the default products order")
  public void system_should_ignore_invalid_option_maintaining_default_products_order() {}
}
