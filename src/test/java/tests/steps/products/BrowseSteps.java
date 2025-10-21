package tests.steps.products;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BrowseSteps {

  @When("a Customer navigates to {string} products page")
  public void navigateToProductsPage(String pageName) {}

  @Then("a message {string} should be displayed")
  public void containsResultsIndicatorMessage(String message) {}

  @And("a list of products should be displayed, each showing an image, name, category as {string}, rating as stars, and price")
  public void containsAListOfProducts(String category) {}

  @And("the ❝Our Best Sellers❞ section should display three products, each showing an image, name, rating as stars, and price")
  public void containsOurBestSellersContent() {}
}
