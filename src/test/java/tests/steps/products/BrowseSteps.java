package tests.steps.products;

import com.pages.home.Homepage;
import com.pages.product.ProductsPage;
import com.utils.RunContext;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

public final class BrowseSteps {

  @When("a Customer navigates to {string} products page")
  public void customer_navigates_to_products_page(String pageName) {
    ProductsPage page = ((Homepage) RunContext.getPage()).browseProducts(pageName);
    assertTrue(page.isLoaded());
    RunContext.setPage(page);
  }

  @And("a list of products should be displayed, each showing an image, name, category as {string}, rating as stars, and price")
  public void list_of_products_should_be_displayed(String expectedCategory) {
    assertTrue(((ProductsPage) RunContext.getPage()).hasProductList(expectedCategory));
  }

  @And("the ❝Our Best Sellers❞ section should display three products, each showing an image, name, rating as stars, and price")
  public void our_best_sellers_section_should_display_three_products() {
    assertTrue(((ProductsPage) RunContext.getPage()).hasOurBestSellers());
  }
}
