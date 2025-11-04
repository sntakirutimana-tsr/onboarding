package steps.products;

import steps.CommonSteps;

import pages.products.components.PriceRangeFilter;
import pages.products.components.ProductList;
import pages.products.ProductPage;

import utils.RunContext;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public final class DiscoverySteps {

  @Given("a Customer is on {string} products page")
  public void customer_is_on_products_page(String pageName) {
    RunContext.currentPage = CommonSteps.ensureProductPageIsAccessible(pageName);
  }

  @When("a Customer enters search keyword as {string}")
  public void customer_enters_search_keyword_as(String keyword) {
    ((ProductPage) RunContext.currentPage)
      .searchByName()
      .type(keyword);
  }

  @And("clicks the ❝SEARCH❞ button")
  public void click_the_SEARCH_button() {
    RunContext.currentPage = ((ProductPage) RunContext.currentPage)
      .searchByName()
      .search();
  }

  @And("only products containing the {string} in their names should be displayed")
  public void only_products_whose_names_contain_the_keyword_should_be_displayed(String keyword) {
    ProductList productList = ((ProductPage) RunContext.currentPage).productList();
    assertTrue(productList.hasTheRightNumberOfItems());
    assertTrue(productList.hasOnlyItemsWhoseNamesContain(keyword.toLowerCase()));
  }

  @Then("a message ❝No products were found matching your selection.❞ should be displayed")
  public void message_no_products_were_found_should_be_displayed() {
    assertTrue(((ProductPage) RunContext.currentPage).hasNoProductFoundMessage());
  }

  @When("a Customer sorts products by {string}")
  @And("sorts products by {string}")
  public void customer_sorts_products_by(String criterion) {
    ProductPage page = (ProductPage) RunContext.currentPage;
    RunContext.currentPage = page.sortBy().selectOption(criterion);
  }

  @When("a Customer selects an invalid sorting criterion as {string}")
  public void customer_selects_invalid_sorting_criterion_as(String criterion) {
    ProductPage page = (ProductPage) RunContext.currentPage;
    ProductList productList = page.productList();

    assertTrue(productList.hasTheRightNumberOfItems());
    RunContext.productNameList = productList.getNames();
    assertThrows(NoSuchElementException.class, () -> page.sortBy().selectOption(criterion));
  }

  @Then("the products should be re-arranged by {string}")
  @And("products should be sorted in {string} order")
  public void products_should_be_reordered_by(String criterion) {
    ProductPage page = (ProductPage) RunContext.currentPage;
    ProductList productList = page.productList();

    assertTrue(productList.hasTheRightNumberOfItems());
    assertTrue(page.sortBy().getSelectionChoiceVisibleText().toLowerCase().contains(criterion.toLowerCase()));
    assertTrue(productList.areSortedBy(criterion));
  }

  @Then("the system should ignore the invalid option, maintaining the default products order")
  public void system_should_ignore_invalid_option_maintaining_default_products_order() {
    ProductPage page = (ProductPage) RunContext.currentPage;
    assertEquals("Default sorting", page.sortBy().getSelectionChoiceVisibleText());
    assertEquals(RunContext.productNameList, page.productList().getNames());
  }

  @When("a Customer sets the price range from {double} to {double}")
  @And("sets the price range from {double} to {double}")
  public void customer_sets_price_range_filter(double min, double max) {
    ((ProductPage) RunContext.currentPage).priceFilter()
      .setRange(min, max);
  }

  @And("clicks the ❝FILTER❞ button")
  public void click_the_FILTER_button() {
    RunContext.currentPage = ((ProductPage) RunContext.currentPage).priceFilter()
      .applyFilter();
  }

  @Then("only products within {double} to {double} should be displayed")
  public void only_products_within_the_provided_price_range_should_be_displayed(double min, double max) {
    int minPrice = (int) (Math.floor(min / 10) * 10);
    int maxPrice = (int) (Math.ceil(max / 10) * 10);

    ProductPage page = (ProductPage) RunContext.currentPage;
    ProductList productList = page.productList();
    PriceRangeFilter filter = page.priceFilter();

    assertTrue(productList.hasTheRightNumberOfItems());
    assertTrue(filter.hasPriceLabel(minPrice, maxPrice));
    assertTrue(productList.areInPriceRange(min, max));
  }

  @When("a Customer selects sub-category as {string}")
  public void customer_selects_sub_category_as(String subCategory) {
    RunContext.currentPage = ((ProductPage) RunContext.currentPage).subCategoryFilter()
      .selectOption(subCategory);
//    RunContext.currentPageName = subCategory.replace("’", "'");
  }

  @When("a Customer selects invalid sub-category as {string}")
  public void customer_selects_invalid_sub_category_as(String subCategory) {
    ProductPage page = (ProductPage) RunContext.currentPage;
    ProductList productList = page.productList();
    assertTrue(productList.hasTheRightNumberOfItems());

    RunContext.productNameList = productList.getNames();
    assertThrows(TimeoutException.class, () -> page.subCategoryFilter().selectOption(subCategory));
  }

  @And("filters the results to show items priced from {double} to {double}")
  public void filters_results_to_show_items_in_price_range(double min, double max) {
    PriceRangeFilter filter = ((ProductPage) RunContext.currentPage).priceFilter();
    filter.setRange(min, max);

    ProductPage page = filter.applyFilter();
    assertTrue(page.productList().hasTheRightNumberOfItems());
    RunContext.currentPage = page;
  }

  @Then("the list of products should remain unchanged")
  public void list_of_products_should_remain_unchanged() {
    ProductPage page = (ProductPage) RunContext.currentPage;
    assertEquals(RunContext.productNameList, page.productList().getNames());
  }

  @Then("only products in {string} within the price range of {double} to {double} should be displayed")
  public void only_products_in_category_within_price_range_should_be_displayed(String category, double min, double max) {
    ProductList productList = ((ProductPage) RunContext.currentPage).productList();

    assertTrue(productList.hasTheRightNumberOfItems());
    assertTrue(productList.areInPriceRange(min, max));
    assertTrue(productList.hasOnlyItemsWithCategory(category));
  }
}
