package steps.products;

import steps.CommonSteps;

import pages.products.ProductPage;

import utils.RunContext;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public final class PaginationSteps {

  @Given("a Customer is on the second results page of the store page")
  public void customer_is_on_second_results_page_of_store_page() {
    ProductPage page = CommonSteps.ensureProductPageIsAccessible("store");
    ensureStoreIsLoadedAfterPaginationEffects(page, "2");
  }

  @When("a Customer navigates through page results using {string}")
  public void customer_navigates_through_page_results_using(String control) {
    ProductPage page = (ProductPage) RunContext.currentPage;
    assertTrue(page.productList().hasTheRightNumberOfItems());

    RunContext.productNameList = page.productList().getNames();
    ensureStoreIsLoadedAfterPaginationEffects(page, control);
  }

  @And("the corresponding set of products should be displayed")
  public void corresponding_set_of_products_should_be_displayed() {
    ProductPage page = (ProductPage) RunContext.currentPage;
    assertTrue(page.productList().hasTheRightNumberOfItems());
    assertTrue(RunContext.productNameList.stream().noneMatch(page.productList().getNames()::contains));
  }

  @And("the pagination controls should reflect the current page {string}")
  public void pagination_controls_should_reflect_the_current_page(String pageNumber) {
    assertTrue(((ProductPage) RunContext.currentPage)
      .productList()
      .getPaginator()
      .isCurrentPage(pageNumber));
  }

  static void ensureStoreIsLoadedAfterPaginationEffects(ProductPage store, String control) {
    RunContext.currentPage = store.productList().getPaginator().viewAnotherPage(control);
  }
}
