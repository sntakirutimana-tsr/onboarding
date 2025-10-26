package tests.steps.products;

import tests.steps.CommonSteps;

import com.pages.product.components.SubCategoryFilter;
import com.pages.product.components.PriceRangeFilter;
import com.pages.product.SearchResultsPage;
import com.pages.Page;
import com.pages.product.ProductsPage;
import com.utils.RunContext;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.NoSuchElementException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

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
    assertTrue(((ProductsPage) RunContext.getPage())
      .hasNoProductFoundMessage()
    );
  }

  @When("a Customer sorts products by {string}")
  @And("sorts products by {string}")
  public void customer_sorts_products_by(String criterion) {
    ProductsPage current = (ProductsPage) RunContext.getPage();
    Page page = current.sortBy().selectOption(criterion, RunContext.getPageName());
    RunContext.setPage(page);
  }

  @When("a Customer selects an invalid sorting criterion as {string}")
  public void customer_selects_invalid_sorting_criterion_as(String criterion) {
    ProductsPage current = (ProductsPage) RunContext.getPage();
    assertThrows(NoSuchElementException.class, () -> current.sortBy().selectOption(criterion, RunContext.getPageName()));
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

  @When("a Customer sets the price range from {double} to {double}")
  @And("sets the price range from {double} to {double}")
  public void customer_sets_price_range_filter(double min, double max) {
    ((ProductsPage) RunContext.getPage()).priceRangeFilter()
      .setRange(min, max);
  }

  @And("clicks the ❝FILTER❞ button")
  public void click_the_FILTER_button() {
    Page page = ((ProductsPage) RunContext.getPage()).priceRangeFilter()
      .applyFilter(RunContext.getPageName());
    RunContext.setPage(page);
  }

  @Then("only products within {double} to {double} should be displayed")
  public void only_products_within_the_provided_price_range_should_be_displayed(double min, double max) {
    int minPrice = (int) (Math.floor(min / 10) * 10);
    int maxPrice = (int) (Math.ceil(max / 10) * 10);

    ProductsPage page = (ProductsPage) RunContext.getPage();
    PriceRangeFilter filter = page.priceRangeFilter(minPrice, maxPrice);

    assertTrue(page.isLoaded(minPrice, maxPrice));
    assertTrue(filter.hasPriceLabel(minPrice, maxPrice));
    assertTrue(page.areAllProductsInPriceRange(min, max));
  }

  @When("a Customer selects sub-category as {string}")
  public void customer_selects_sub_category_as(String subCategory) {
    Page page = ((ProductsPage) RunContext.getPage()).subCategoryFilter()
      .selectOption(subCategory);
    RunContext.setPage(page);
    RunContext.setPageName(subCategory.replace("’", "'"));
  }

  @When("a Customer selects invalid sub-category as {string}")
  public void customer_selects_invalid_sub_category_as(String subCategory) {
    SubCategoryFilter filter = ((ProductsPage) RunContext.getPage()).subCategoryFilter();
    assertThrows(NoSuchElementException.class, () -> filter.selectOption(subCategory));
  }

  @And("filters the results to show items priced from {double} to {double}")
  public void filters_results_to_show_items_in_price_range(double min, double max) {
    PriceRangeFilter filter = ((ProductsPage) RunContext.getPage()).priceRangeFilter();
    filter.setRange(min, max);

    Page page = filter.applyFilter(RunContext.getPageName());
    assertTrue(page.isLoaded());
    RunContext.setPage(page);
  }

  @Then("the list of products should remain unchanged")
  public void list_of_products_should_remain_unchanged() {
    ProductsPage page = (ProductsPage) RunContext.getPage();
    assertThrows(TimeoutException.class,
      () -> page.waitFor(ExpectedConditions
        .invisibilityOfElementWithText(By.tagName("h1"), page.getHeaderText()), 10));
  }

  @Then("only products in {string} within the price range of {double} to {double} should be displayed")
  public void only_products_in_category_within_price_range_should_be_displayed(String category, double min, double max) {
    ProductsPage page = (ProductsPage) RunContext.getPage();
    assertTrue(page.areAllProductsInPriceRange(min, max));
    assertTrue(page.hasProductList(category));
  }
}
