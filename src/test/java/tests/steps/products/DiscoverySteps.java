package tests.steps.products;

import tests.steps.CommonSteps;

import com.pages.product.SearchResultsPage;
import com.pages.Page;
import com.pages.product.ProductsPage;
import com.utils.RunContext;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public final class DiscoverySteps {

  @Given("a Customer is on {string} products page")
  public void customer_is_on_products_page(String pageName) {
    RunContext.setPage(CommonSteps.ensureProductPageIsAccessible(pageName));
    RunContext.setPageName(pageName);
  }

  @When("a Customer enters search keyword as {string}")
  public void customer_enters_search_keyword_as(String keyword) {
    ((ProductsPage) RunContext.getPage())
      .searchByName()
      .enterKeyword(keyword);
    RunContext.setSearchKeyword(keyword);
  }

  @And("clicks the ❝SEARCH❞ button")
  public void click_the_SEARCH_button() {
    Page page = ((ProductsPage) RunContext.getPage())
      .searchByName()
      .clickSearchButton(RunContext.getSearchKeyword());
    RunContext.setPage(page);
  }

  @And("only products containing the {string} in their names should be displayed")
  public void only_products_whose_names_contain_the_keyword_should_be_displayed(String keyword) {
    SearchResultsPage page = (SearchResultsPage) RunContext.getPage();
    assertTrue(page.hasOnlyProductsWhoseNamesContain(keyword.toLowerCase()));
  }

  @Then("a message ❝No products were found matching your selection.❞ should be displayed")
  public void message_no_products_were_found_should_be_displayed() {
    assertTrue(((SearchResultsPage) RunContext.getPage())
      .hasNoProductFoundMessage()
    );
  }

  @When("a Customer sorts products by {string}")
  @When("a Customer selects an invalid sorting criterion as {string}")
  @And("sorts products by {string}")
  public void customer_sorts_products_by(String criterion) {
    ProductsPage current = (ProductsPage) RunContext.getPage();
    Page page = current.sortBy().select(criterion, RunContext.getPageName());
    RunContext.setPage(page);
  }

  @Then("the products should be re-arranged by {string}")
  @And("products should be sorted in {string} order")
  public void products_should_be_reordered_by(String criterion) {
    ProductsPage page = (ProductsPage) RunContext.getPage();

    assertTrue(page.isLoaded());
    assertTrue(page.sortBy().getSelectedText().toLowerCase().contains(criterion.toLowerCase()));
    assertTrue(page.areProductsSortedBy(criterion));
  }

  @Then("the system should ignore the invalid option, maintaining the default products order")
  public void system_should_ignore_invalid_option_maintaining_default_products_order() {
    ProductsPage page = (ProductsPage) RunContext.getPage();
    assertEquals("Default sorting", page.sortBy().getSelectedText());
  }

  @When("a Customer sets the price range from {int} to {int}")
  @And("sets the price range from {int} to {int}")
  public void customer_sets_price_range_filter(int min, int max) {
  }

  @And("clicks the ❝FILTER❞ button")
  public void click_the_FILTER_button() {
  }

  @Then("only products within {int} to {int} should be displayed")
  public void only_products_within_the_provided_price_range_should_be_displayed(int min, int max) {
  }

  @Then("the system should clamp the price range to stay within the allowed range of 10 to 150")
  public void system_should_clamp_price_range_to_stay_within_allowed_range_of_10_to_150() {
  }

  @When("a Customer selects sub-category as {string}")
  public void customer_selects_sub_category_as(String subCategory) {
  }

  @Then("only products in the {string} sub-category should be displayed")
  public void only_products_in_the_sub_category_should_be_displayed(String subCategory) {
  }

  @Then("the list of products should remain unchanged")
  public void list_of_products_should_remain_unchanged() {
  }

  @Then("only products in {string} within the price range of {int} to {int} should be displayed")
  public void only_products_in_selected_sub_category_and_within_price_range_set_should_be_displayed(String subCategory, int min, int max) {
  }
}
