package tests.steps.products;

import tests.steps.CommonSteps;

import com.pages.product.StorePage;
import com.utils.RunContext;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

public final class PaginationSteps {

  @Given("a Customer is on the second results page of the store page")
  public void customer_is_on_second_results_page_of_store_page() {
    StorePage page = (StorePage) CommonSteps.ensureProductPageIsAccessible("store");
    ensureStoreIsLoadedAfterPaginationEffects(page, "2");
  }

  @When("a Customer navigates {string} through page results using {string}")
  public void customer_navigates_through_page_results_using(String d, String control) {
    StorePage store = (StorePage) RunContext.getPage();
    RunContext.setProductNameList(store.productNameList());
    ensureStoreIsLoadedAfterPaginationEffects(store, control);
  }

  static void ensureStoreIsLoadedAfterPaginationEffects(StorePage store, String control) {
    StorePage page = store.paginator().navigateToPage(control);
    assertTrue(page.isLoadedAfterPaginationEffects());
    RunContext.setPage(page);
  }

  @And("the corresponding set of products should be displayed")
  public void corresponding_set_of_products_should_be_displayed() {
    StorePage page = (StorePage) RunContext.getPage();
    assertTrue(page.hasProductList());
    assertTrue(RunContext.getProductNameList().stream().noneMatch(page.productNameList()::contains));
  }

  @And("the pagination controls should reflect the current page {string}")
  public void pagination_controls_should_reflect_the_current_page(String pageNumber) {
    assertTrue(((StorePage) RunContext.getPage())
      .paginator()
      .isCurrentPage(pageNumber));
  }
}
