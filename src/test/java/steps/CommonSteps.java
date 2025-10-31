package steps;

import pages.BasePage;
import pages.Homepage;
import pages.products.ProductPage;

import factory.DriverFactory;

import utils.RunContext;

import io.cucumber.java.en.Given;

import static org.junit.Assert.*;

public final class CommonSteps {

  @Given("a Customer is on the homepage")
  public void customer_is_on_the_homepage() {
    RunContext.currentPage = CommonSteps.ensureHomepageIsAccessible();
  }

  public static BasePage ensureHomepageIsAccessible() {
    Homepage page = new Homepage(DriverFactory.getDriver());
    page.load("");
    assertTrue(page.isReady());
    return page;
  }

  public static ProductPage ensureProductPageIsAccessible(Homepage home, String name) {
    ProductPage page = home.browseProducts(name);
    assertTrue(page.isReady());
    return page;
  }

  public static ProductPage ensureProductPageIsAccessible(String name) {
    Homepage home = (Homepage) CommonSteps.ensureHomepageIsAccessible();
    ProductPage page = home.browseProducts(name);
    assertTrue(page.isReady());
    return page;
  }
}
