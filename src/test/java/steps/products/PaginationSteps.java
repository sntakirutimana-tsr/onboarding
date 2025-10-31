package steps.products;

import steps.CommonSteps;

import pages.products.ProductPage;

import utils.RunContext;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;
import static utils.ExtendedHelpers.waitForDisappearance;

public final class PaginationSteps {

  @Given("a Customer is on the second results page of the store page")
  public void customer_is_on_second_results_page_of_store_page() {
    ProductPage page = CommonSteps.ensureProductPageIsAccessible("store");
    page.getProductList().getPaginator().ensureIsReady();
    ensureStoreIsLoadedAfterPaginationEffects(page, "2");
  }

  @When("a Customer navigates {string} through page results using {string}")
  public void customer_navigates_through_page_results_using(String d, String control) {
    ProductPage page = (ProductPage) RunContext.currentPage;
    page.getProductList().ensureIsReady();
    page.getProductList().getPaginator().ensureIsReady();
    RunContext.productNameList = page.getProductList().getNames();
    ensureStoreIsLoadedAfterPaginationEffects(page, control);
  }

  static void ensureStoreIsLoadedAfterPaginationEffects(ProductPage store, String control) {
    ProductPage page = store.getProductList().getPaginator().navigateToPage(control);
    waitForDisappearance(page.getDriver(), page.getHeader(), 5);
    assertTrue(page.isReady());
    page.getProductList().ensureIsReady();
    page.getProductList().getPaginator().ensureIsReady();
    RunContext.currentPage = page;
  }

  @And("the corresponding set of products should be displayed")
  public void corresponding_set_of_products_should_be_displayed() {
    ProductPage page = (ProductPage) RunContext.currentPage;
    assertTrue(RunContext.productNameList.stream().noneMatch(page.getProductList().getNames()::contains));
  }

  @And("the pagination controls should reflect the current page {string}")
  public void pagination_controls_should_reflect_the_current_page(String pageNumber) {
    assertTrue(((ProductPage) RunContext.currentPage)
      .getProductList()
      .getPaginator()
      .isCurrentPage(pageNumber));
  }
}
