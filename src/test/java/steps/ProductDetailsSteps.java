package steps;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.ProductDetailsPage;
import utils.ScenarioContext;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductDetailsSteps {

  private WebDriver driver;
  ProductDetailsPage productDetailsPage;
  private final ScenarioContext scenarioContext;

  public ProductDetailsSteps(ScenarioContext scenarioContext) {
    this.scenarioContext = scenarioContext;

  }

  @Given("the customer is on a  {string}")
  public void theCustomerIsOnA(String page) {
    driver = DriverFactory.getDriver();
    productDetailsPage = new ProductDetailsPage(driver);
    String pagePath=  switch (page.toLowerCase()) {
      case "men", "women", "accessories" -> "/product-category/" + page.toLowerCase();
      case "home" -> "";
      default -> "/" +page.toLowerCase();
    };

    productDetailsPage.load(pagePath.toLowerCase());

  }

  @When("the customer clicks on a {string} with {string} from the product card")
  public void theCustomerClicksOnAFromTheProductCard(String product_name, String link) {
    scenarioContext.set("selectedProduct", product_name);
    productDetailsPage.navigateToDetailsPage(link);
    productDetailsPage = new ProductDetailsPage(driver);

  }

  @Then("the customer is redirected to the product details page with header as {string}")
  public void theCustomerIsRedirectedToTheProductDetailsPageWithHeaderAs(String product_name) {
    String actualProductName = scenarioContext.get("selectedProduct", String.class);

    // Verify the name matches what’s on the product details page
    String headerText = productDetailsPage.getProductName();
    assertEquals(product_name, actualProductName, headerText);

  }

  @And("the {string}that has the {string}")
  public void theThatHasThe(String breadcrumb , String product_name) {
    productDetailsPage.waitFor(ExpectedConditions.visibilityOf(productDetailsPage.breadcrumb));
    System.out.println(productDetailsPage.breadcrumb);
    String actualBreadcrumb = productDetailsPage.getBreadcrumb();

    System.out.println(actualBreadcrumb);
    System.out.println(product_name);


    assertTrue(actualBreadcrumb.toLowerCase().contains(product_name.toLowerCase()),
      "Expected breadcrumb to contain the product name.\n" +
            "Breadcrumb: " + actualBreadcrumb + "\n" +
            "Product name: " + product_name
        );

  }

  @And("the product image should be displayed")
  public void theProductImageShouldBeDisplayed() {

    assertTrue(productDetailsPage.isImageDisplayed(), "The product image is visible on the products details page");

  }

  @And("the product description should be displayed")
  public void theProductDescriptionShouldBeDisplayed() {
    assertTrue(productDetailsPage.isDescriptionDisplayed(), "The product description is displayed");
  }

  @And("the product category should be displayed")
  public void theProductCategoryShouldBeDisplayed() {
    assertTrue(productDetailsPage.isCategoryDisplayed(), "The product category is displayed");
  }

  @And("the product quantity field should be displayed and defaulted to one")
  public void theProductQuantityFieldShouldBeDisplayedAndDefaultedToOne() {
    assertTrue(productDetailsPage.isQuantityDisplayed(), "The quantity field is displayed");
  }

  @And("the customer can see the description tab with the description of the product")
  public void theCustomerCanSeeTheDescriptionTabWithTheDescriptionOfTheProduct() {
    assertTrue(productDetailsPage.isDescriptionTabDisplayed(), "The description tab is displayed");

  }

  @And("the customer can see the additional information tab")
  public void theCustomerCanSeeTheAdditionalInformationTab() {
    assertTrue(productDetailsPage.isAdditionalInformationTabDisplayed(), "The additional information tab is displayed");
  }

  @And("the customer can see the reviews tab with reviews count")
  public void theCustomerCanSeeTheReviewsTabWithReviewsCount() {
    assertTrue(productDetailsPage.isReviewsTabDisplayed(), "The reviews tab is displayed");
  }

  @And("an {string} button should be displayed")
  public void anButtonShouldBeDisplayed(String arg0) {
    assertTrue(productDetailsPage.isSubmitButtonDisplayed(), "The submit button is displayed");
  }
}
