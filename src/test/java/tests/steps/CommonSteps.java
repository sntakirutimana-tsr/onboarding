package tests.steps;

import com.pages.Page;
import com.pages.home.Homepage;
import com.pages.product.ProductsPage;
import com.utils.RunContext;

import io.cucumber.java.en.Given;

import static org.junit.Assert.assertTrue;

public final class CommonSteps {

  @Given("a Customer is on the homepage")
  public void customer_is_on_the_homepage() {
    RunContext.setPage(CommonSteps.ensureHomepageIsAccessible());
  }

  public static Page ensureHomepageIsAccessible() {
    Homepage page = Homepage.visit();
    assertTrue(page.isLoaded());
    return page;
  }

  public static ProductsPage ensureProductPageIsAccessible(Homepage home, String name) {
    ProductsPage page = home.browseProducts(name);
    assertTrue(page.isLoaded());
    return page;
  }

  public static ProductsPage ensureProductPageIsAccessible(String name) {
    Homepage home = (Homepage) CommonSteps.ensureHomepageIsAccessible();
    ProductsPage page = home.browseProducts(name);
    assertTrue(page.isLoaded());
    return page;
  }
}
