package steps.products;

import steps.CommonSteps;

import pages.products.components.SubCategoryFilter;
import pages.products.components.PriceRangeFilter;
import pages.products.ProductPage;

import utils.RunContext;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.NoSuchElementException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;
import static utils.Executor.hasEvaluatedAndSucceed;

public final class DiscoverySteps {

  @Given("a Customer is on {string} products page")
  public void customer_is_on_products_page(String pageName) {
    RunContext.currentPage = CommonSteps.ensureProductPageIsAccessible(pageName);
    RunContext.currentPageName = pageName;
  }

  @When("a Customer enters search keyword as {string}")
  public void customer_enters_search_keyword_as(String keyword) {
    ((ProductPage) RunContext.currentPage)
      .getSearchByName()
      .type(keyword);
    RunContext.searchKeyword = keyword;
  }

  @And("clicks the ❝SEARCH❞ button")
  public void click_the_SEARCH_button() {
    RunContext.currentPage = ((ProductPage) RunContext.currentPage)
      .getSearchByName()
      .search();
  }

  @And("only products containing the {string} in their names should be displayed")
  public void only_products_whose_names_contain_the_keyword_should_be_displayed(String keyword) {
    ProductPage page = (ProductPage) RunContext.currentPage;
    assertTrue(page.getProductList().hasOnlyItemsWhoseNamesContain(keyword.toLowerCase()));
  }

  @Then("a message ❝No products were found matching your selection.❞ should be displayed")
  public void message_no_products_were_found_should_be_displayed() {
    assertTrue(((ProductPage) RunContext.currentPage)
      .hasNoProductFoundMessage()
    );
  }

  @When("a Customer sorts products by {string}")
  @And("sorts products by {string}")
  public void customer_sorts_products_by(String criterion) {
    ProductPage page = (ProductPage) RunContext.currentPage;
    page.getSortBy().ensureIsReady();
    RunContext.currentPage = page.getSortBy().selectOption(criterion);
  }

  @When("a Customer selects an invalid sorting criterion as {string}")
  public void customer_selects_invalid_sorting_criterion_as(String criterion) {
    ProductPage current = (ProductPage) RunContext.currentPage;
    assertThrows(NoSuchElementException.class, () -> current.getSortBy().selectOption(criterion));
  }

  @Then("the products should be re-arranged by {string}")
  @And("products should be sorted in {string} order")
  public void products_should_be_reordered_by(String criterion) {
    ProductPage page = (ProductPage) RunContext.currentPage;
    assertTrue(hasEvaluatedAndSucceed(() -> page.getProductList().ensureIsReady()));
    assertTrue(page.getSortBy().getSelectedText().toLowerCase().contains(criterion.toLowerCase()));
    assertTrue(page.getProductList().areSortedBy(criterion));
  }

  @Then("the system should ignore the invalid option, maintaining the default products order")
  public void system_should_ignore_invalid_option_maintaining_default_products_order() {
    ProductPage page = (ProductPage) RunContext.currentPage;
    assertEquals("Default sorting", page.getSortBy().getSelectedText());
  }

  @When("a Customer sets the price range from {double} to {double}")
  @And("sets the price range from {double} to {double}")
  public void customer_sets_price_range_filter(double min, double max) {
    ((ProductPage) RunContext.currentPage).getPriceFilter()
      .setRange(min, max);
  }

  @And("clicks the ❝FILTER❞ button")
  public void click_the_FILTER_button() {
    RunContext.currentPage = ((ProductPage) RunContext.currentPage).getPriceFilter()
      .applyFilter();
  }

  @Then("only products within {double} to {double} should be displayed")
  public void only_products_within_the_provided_price_range_should_be_displayed(double min, double max) {
    int minPrice = (int) (Math.floor(min / 10) * 10);
    int maxPrice = (int) (Math.ceil(max / 10) * 10);

    ProductPage page = (ProductPage) RunContext.currentPage;
    PriceRangeFilter filter = page.getPriceFilter();

    assertTrue(page.isReady());
    assertTrue(filter.hasPriceLabel(minPrice, maxPrice));
    assertTrue(page.getProductList().areInPriceRange(min, max));
  }

  @When("a Customer selects sub-category as {string}")
  public void customer_selects_sub_category_as(String subCategory) {
    RunContext.currentPage = ((ProductPage) RunContext.currentPage).getCategoryFilter()
      .selectOption(subCategory);
    RunContext.currentPageName = subCategory.replace("’", "'");
  }

  @When("a Customer selects invalid sub-category as {string}")
  public void customer_selects_invalid_sub_category_as(String subCategory) {
    SubCategoryFilter filter = ((ProductPage) RunContext.currentPage).getCategoryFilter();
    assertThrows(NoSuchElementException.class, () -> filter.selectOption(subCategory));
  }

  @And("filters the results to show items priced from {double} to {double}")
  public void filters_results_to_show_items_in_price_range(double min, double max) {
    PriceRangeFilter filter = ((ProductPage) RunContext.currentPage).getPriceFilter();
    filter.setRange(min, max);

    ProductPage page = filter.applyFilter();
    assertTrue(page.isReady());
    RunContext.currentPage = page;
  }

  @Then("the list of products should remain unchanged")
  public void list_of_products_should_remain_unchanged() {
    ProductPage page = (ProductPage) RunContext.currentPage;
    assertThrows(TimeoutException.class,
      () -> page.waitFor(ExpectedConditions.invisibilityOfElementLocated(By.tagName("h1"))));
  }

  @Then("only products in {string} within the price range of {double} to {double} should be displayed")
  public void only_products_in_category_within_price_range_should_be_displayed(String category, double min, double max) {
    ProductPage page = (ProductPage) RunContext.currentPage;
    assertTrue(page.getProductList().areInPriceRange(min, max));
    assertTrue(page.getProductList().hasOnlyItemsWithCategory(category));
  }
}
