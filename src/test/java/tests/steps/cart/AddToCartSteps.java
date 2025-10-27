package tests.steps.cart;

import com.pages.product.ProductsPage;
import com.utils.RunContext;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.utils.DriverProvider;
import com.pages.home.Homepage;
import tests.steps.CommonSteps;

import java.time.Duration;

public class AddToCartSteps {
  private final WebDriver driver = DriverProvider.get();
  private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  private int previousCartCounter = 0;


  @Given("Customer is on {string}")
  public void customerIsOn(String pageName) {
    Homepage home = (Homepage) CommonSteps.ensureHomepageIsAccessible();
    RunContext.setPage(home);
  }

  @When("clicks on ADD TO CART button of {string}")
  public void clicksOnButtonOf(String productName) {

    By cartCounterLocator = By.xpath("//div[@id='ast-site-header-cart']//span[@class='count']");

    try {
      WebElement counterElement = driver.findElement(cartCounterLocator);
      String countText = counterElement.getText().trim();
      previousCartCounter = countText.isEmpty() ? 0 : Integer.parseInt(countText);
    } catch (NoSuchElementException | NumberFormatException e) {
      previousCartCounter = 0;
      System.out.println("DEBUG: Cart counter element not readable, assuming previous count was 0.");
    }

    By addToCartButton = By.xpath("//a[contains(@aria-label, '" + productName + "')]");
    WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
    button.click();
    System.out.println("DEBUG: Clicked ADD TO CART for " + productName);
  }

  @Then("a {string} link appears for {string}")
  public void aViewCartLinkAppears(String linkText, String productName) {
    By viewCartLinkLocator = By.cssSelector("a[title='View cart']");
    try {
      System.out.println("DEBUG: Looking for 'View cart' link with XPath: " + viewCartLinkLocator);

      wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartLinkLocator));
      WebElement viewCartLink = driver.findElement(viewCartLinkLocator);

      // Verify the link text
      Assert.assertEquals(
        "The text of the success link was incorrect.",
        linkText, // Expected value: "View cart"
        viewCartLink.getText().trim() // Actual value
      );
      System.out.println("DEBUG: Successfully found and validated 'View cart' link.");

    } catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
      System.err.println("ERROR: Timed out waiting for element or element not found. Locator: " + viewCartLinkLocator);
      Assert.fail("The '" + linkText + "' link did not appear after adding the product to the cart.");
    }
  }

  @And("cart counter increments by {int}")
  public void andCartCounterIncrementsBy(int increment) {


    By cartCounterLocator = By.cssSelector(".ast-cart-menu-wrap .count");

// Example usage remains the same
    int expectedNewCount = previousCartCounter + increment;

    // 2. Wait for the counter to update its text
    wait.until(ExpectedConditions.textToBe(cartCounterLocator, String.valueOf(expectedNewCount)));

    // 3. Read the new count and verify
    WebElement counterElement = driver.findElement(cartCounterLocator);
    String newCountText = counterElement.getText().trim();
    int newCartCounter = newCountText.isEmpty() ? 0 : Integer.parseInt(newCountText);

    Assert.assertEquals(
      "Cart counter did not increment correctly. Expected: " + expectedNewCount + ", Actual: " + newCartCounter,
      expectedNewCount,
      newCartCounter
    );

  }

  @And("the {string} should be in the cart with {string}, {string}, and {string}")
  public void theShouldBeInTheCartWithAnd(String productName, String price, String quantity, String subtotal) {


    By viewCartLinkLocator = By.cssSelector("a[title='View cart']");

    // Click it to navigate to the Cart page
    WebElement link = wait.until(ExpectedConditions.elementToBeClickable(viewCartLinkLocator));
    link.click();

    // Wait for the Cart page to load
    wait.until(ExpectedConditions.titleContains("Cart"));
    System.out.println("DEBUG: Navigated to the Cart page via embedded click.");



    By productRowLocator = By.xpath("//td[@class='product-name']/a[text()='" + productName + "']");



    wait.until(ExpectedConditions.visibilityOfElementLocated(productRowLocator));
    WebElement productRow = driver.findElement(By.xpath("//td[@class='product-name']/a[text()='" + productName + "']/ancestor::tr"));

// Now, use the CSS Selector to find the price *within* that row
    WebElement priceElement = productRow.findElement(By.cssSelector(".product-price .woocommerce-Price-amount.amount"));
    String actualPrice = priceElement.getText().trim();
    Assert.assertEquals("Price mismatch in cart.", price, actualPrice);


    String actualQuantity = driver.findElement(By.xpath("//td[@class='product-quantity']//input")).getAttribute("value");

    Assert.assertEquals("Quantity mismatch in cart.", quantity, actualQuantity);

    // 5. Verify Subtotal
    String subtotalPath = ".//td[@class='product-subtotal']//span[@class='woocommerce-Price-amount amount']";
    String actualSubtotal = driver.findElement(By.xpath(subtotalPath)).getText().trim();
    Assert.assertEquals("Subtotal mismatch in cart.", subtotal, actualSubtotal);

    System.out.println("DEBUG: All product details verified for " + productName + " in the cart.");
  }

}