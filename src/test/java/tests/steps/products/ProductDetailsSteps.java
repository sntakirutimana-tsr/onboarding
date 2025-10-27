package tests.steps.products;

import com.pages.home.Homepage;
import com.pages.product.ProductsPage;
import com.pages.product.components.cards.RegularProdCard;
import com.utils.DriverProvider;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.steps.CommonSteps;


import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductDetailsSteps {
  @Given("the customer is on the homepage")
  public void theCustomerIsOnTheHomepage() {
    CommonSteps.ensureHomepageIsAccessible();

  }

  @When("the customer clicks on a product's name from the featured products section")
  public void theCustomerClicksOnAProductSNameFromTheFeaturedProductsSection() {
    WebDriver driver = DriverProvider.get();

    WebElement productElement = driver.findElement(By.xpath(
      "//*[@id=\"post-61\"]/div/div[3]/div/div/ul/li[1]/div[2]/a[1]/h2"
    ));

    String productName = productElement.getText().trim();

    RunContext.setPageName(productName);

    productElement.click();


  }

  @Then("the system should redirect to the product details page")
  public void theSystemShouldRedirectToTheProductDetailsPage() {



  }

  @And("the breadcrumb navigation should display the path from the Home page to the product’s category and product name")
  public void theBreadcrumbNavigationShouldDisplayThePathFromTheHomePageToTheProductSCategoryAndProductName() {
    WebDriver driver = DriverProvider.get();
    WebElement breadcrumb = driver.findElement(By.className("woocommerce-breadcrumb"));
    assertTrue("Breadcrumb should be visible", breadcrumb.isDisplayed());

    String expectedProduct = RunContext.getPageName();
    assertTrue("Breadcrumb should contain product name",
      breadcrumb.getText().contains(expectedProduct));


  }

  @And("the product name should be visible")
  public void theProductNameShouldBeVisible() {
    WebDriver driver = DriverProvider.get();
    WebElement productName = driver.findElement(By.cssSelector("h1.product_title.entry-title"));

    assertTrue("Product name should be visible", productName.isDisplayed());

    String expectedName = RunContext.getPageName();
    assertEquals("Product name should match the clicked product",
      expectedName, productName.getText().trim());
  }

  @And("the product image should be displayed with a magnification option")
  public void theProductImageShouldBeDisplayedWithAMagnificationOption() {


    WebDriver driver = DriverProvider.get();

    WebElement productImage = driver.findElement(By.cssSelector(".woocommerce-product-gallery__wrapper"));
    assertTrue("Product image should be visible", productImage.isDisplayed());
  }

  @And("the product description should be visible")
  public void theProductDescriptionShouldBeVisible() {
    WebDriver driver = DriverProvider.get();

    WebElement descriptionTab = driver.findElement(By.className("woocommerce-product-details__short-description"));
    assertTrue("Product description should be visible", descriptionTab.isDisplayed());

  }

  @And("the product category should be displayed with clickable options leading to the respective category or subcategory pages")
  public void theProductCategoryShouldBeDisplayedWithClickableOptionsLeadingToTheRespectiveCategoryOrSubcategoryPages() {
    WebDriver driver = DriverProvider.get();

    WebElement productCategory = driver.findElement(By.cssSelector(".posted_in"));
    assertTrue("Product category link should be visible", productCategory.isDisplayed());

  }

  @And("the product quantity field should be visible and defaulted to {int}")
  public void theProductQuantityFieldShouldBeVisibleAndDefaultedTo(int arg0) {
    WebDriver driver = DriverProvider.get();

    WebElement qty = driver.findElement(By.cssSelector("input.qty"));
    assertTrue("Quantity field should be visible", qty.isDisplayed());

    assertEquals("Quantity should default to " + arg0,
      String.valueOf(arg0), qty.getAttribute("value"));

  }

  @And("the customer can see the description tab with the description of the product")
  public void theCustomerCanSeeTheDescriptionTabWithTheDescriptionOfTheProduct() {

    WebDriver driver = DriverProvider.get();

    WebElement descTab = driver.findElement(By.cssSelector("#tab-title-description > a"));
    assertTrue("Description tab should be visible", descTab.isDisplayed());

  }

  @And("the customer can see the additional information tab")
  public void theCustomerCanSeeTheAdditionalInformationTab() {
    WebDriver driver = DriverProvider.get();

    WebElement addInfoTab = driver.findElement(By.cssSelector("#tab-title-additional_information > a"));
    assertTrue("Additional Information tab should be visible", addInfoTab.isDisplayed());

  }

  @And("the customer can see the reviews tab")
  public void theCustomerCanSeeTheReviewsTab() {
    WebDriver driver = DriverProvider.get();

    WebElement reviewsTab = driver.findElement(By.cssSelector("#tab-title-reviews > a"));
    assertTrue("Reviews tab should be visible", reviewsTab.isDisplayed());

  }

  @And("an {string} button should be displayed")
  public void anButtonShouldBeDisplayed(String buttonText) {
    WebDriver driver = DriverProvider.get();

    WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10))
      .until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//*[@id=\"product-1215\"]/div[2]/form/button")
      ));

    assertTrue(buttonText + " button should be visible", button.isDisplayed());
  }
}
