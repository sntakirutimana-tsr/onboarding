package steps.cart;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CartPage;
import pages.Homepage;

public class AddToCartSteps {
  private WebDriver driver;
  private Homepage homePage;

  @Given("Customer is on Home Page")
  public void customerIsOn() {
    driver = DriverFactory.getDriver();
    homePage = new Homepage(driver);
    homePage.load("/");
  }

  @When("clicks on ADD TO CART button of {string}")
  public void clicksOnADDTOCARTButtonOf(String productName) {
    homePage.AddToCart(productName);
  }

  @Then("a View cart link appears for {string}")
  public void aLinkAppearsFor(String arg1) {
    Assert.assertTrue("View cart link should be visible", homePage.isViewCartLinkVisible());
  }

  @And("cart counter increments by {int}")
  public void cartCounterIncrementsBy(int expectedCount) {
    int actualCount = homePage.getCartCount();
    Assert.assertEquals(expectedCount, actualCount);
  }

  @And("the {string} should be in the cart with {string}, {string}, and {string}")
  public void theShouldBeInTheCartWithAnd(String productName, String expectedPrice, String expectedQty,
      String expectedSubtotal) {
    CartPage cartPage = new CartPage(driver);

    homePage.clickViewCartLink();

    Assert.assertEquals(productName, cartPage.getProductName());
    Assert.assertEquals(expectedPrice, cartPage.getProductPrice());
    Assert.assertEquals(expectedQty, cartPage.getProductQuantity());
    Assert.assertEquals(expectedSubtotal, cartPage.getProductSubtotal());
  }

  // Unique steps for add to cart from product detail page

  @Given("Customer is on the product page for {string}")
  public void customerIsOnTheProductPageFor(String arg0) {
    // My code goes here
  }

  @Then("{string} message is displayed")
  public void hasBeenAddedToYourCartMessageIsDisplayed(String arg0) {
    // My code will go here
  }

}
