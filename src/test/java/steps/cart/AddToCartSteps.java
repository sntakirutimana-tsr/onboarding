package steps.cart;

import factory.DriverFactory;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.HomePage;
import static org.junit.jupiter.api.Assertions.*;

public class AddToCartSteps {
  private WebDriver driver;
  private HomePage homePage;

  @Given("Customer is on Home Page")
  public void customerIsOn() {
    driver = DriverFactory.getDriver();
    homePage = new HomePage(driver);
    homePage.load("/");
  }

  @When("clicks on ADD TO CART button of {string}")
  public void clicksOnADDTOCARTButtonOf(String productName) {
    homePage.AddToCart(productName);
  }

  @Then("a View cart link appears for {string}")
  public void aLinkAppearsFor(String arg1) {
    assertTrue(homePage.isViewCartLinkVisible(), "View cart link should be visible");
  }

  @And("cart counter increments by {int}")
  public void cartCounterIncrementsBy(int expectedCount) {
    int actualCount = homePage.getCartCount();
    assertEquals(expectedCount, actualCount, "Cart counter value should match the expected count");
  }

  @And("the {string} should be in the cart with {string}, {string}, and {string}")
  public void theShouldBeInTheCartWithAnd(String productName, String expectedPrice, String expectedQty, String expectedSubtotal) {
    CartPage cartPage = new CartPage(driver);

    homePage.clickViewCartLink();

    assertEquals(productName, cartPage.getProductName(), "Product name should match");
    assertEquals(expectedPrice, cartPage.getProductPrice(), "Product price should match");
    assertEquals(expectedQty, cartPage.getProductQuantity(), "Product quantity should match");
    assertEquals(expectedSubtotal, cartPage.getProductSubtotal(), "Product subtotal should match");
  }


  //Unique steps for add to cart from product detail page


  @Given("Customer is on the product page for {string}")
  public void customerIsOnTheProductPageFor(String arg0) {
    //My code goes here
  }

  @Then("{string} message is displayed")
  public void hasBeenAddedToYourCartMessageIsDisplayed(String arg0) {
   //My code will go here
  }

}
