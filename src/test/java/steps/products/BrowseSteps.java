package steps.products;

import steps.CommonSteps;

import pages.Homepage;
import pages.products.ProductPage;
import pages.products.components.ProductList;

import utils.RunContext;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public final class BrowseSteps {

  @When("a Customer navigates to {string} products page")
  public void customer_navigates_to_products_page(String pageName) {
    RunContext.currentPage = CommonSteps.ensureProductPageIsAccessible((Homepage) RunContext.currentPage, pageName);
  }

  @And("a list of products should be displayed, each showing an image, name, category as {string}, rating as stars, and price")
  public void list_of_products_should_be_displayed(String expectedCategory) {
    ProductList productList = ((ProductPage) RunContext.currentPage).productList();
    assertTrue(productList.hasTheRightNumberOfItems());
    assertTrue(productList.hasOnlyItemsWithCategory(expectedCategory));
  }

  @And("the ❝Our Best Sellers❞ section should display three products, each showing an image, name, rating as stars, and price")
  public void our_best_sellers_section_should_display_three_products() {
    assertTrue(((ProductPage) RunContext.currentPage).hasOurBestSeller());
  }
}
