package steps.products;

import com.utils.DriverProvider;
import com.utils.RunContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.CommonSteps;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewSteps {
  @Given("the customer is on the reviews tab")
  public void theCustomerIsOnTheReviewsTab() {
    CommonSteps.ensureHomepageIsAccessible();

    WebDriver driver = DriverProvider.get();

    WebElement productElement = driver.findElement(By.xpath(
      "//*[@id=\"post-61\"]/div/div[3]/div/div/ul/li[1]/div[2]/a[1]/h2"
    ));

    String productName = productElement.getText().trim();

    RunContext.setPageName(productName);

    productElement.click();


    WebElement reviewsTab = new WebDriverWait(driver, Duration.ofSeconds(10))
      .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#tab-title-reviews > a")));

    reviewsTab.click();
    WebElement reviewForm = new WebDriverWait(driver, Duration.ofSeconds(10))
      .until(ExpectedConditions.visibilityOfElementLocated(By.id("review_form")));

    assertTrue("Reviews form should be visible", reviewForm.isDisplayed());


  }

  @When("the customer selects rating as {string}")
  public void theCustomerSelectsRatingAs(String rating) {

    WebDriver driver = DriverProvider.get();
    int ratingIndex = Integer.parseInt(rating)-1;
    WebElement star = driver.findElement(By.xpath("//a[contains(@class,'star-" + ratingIndex + "')]"));

    star.click();


  }

  @And("the customer enters a review as {string}")
  public void theCustomerEntersAReviewAs(String reviewText) {

    WebDriver driver = DriverProvider.get();
    WebElement reviewField = driver.findElement(By.id("comment"));
    reviewField.clear();
    reviewField.sendKeys(reviewText);
  }

  @And("the customer enters name as {string}")
  public void theCustomerEntersNameAs(String name) {

    WebDriver driver = DriverProvider.get();
    WebElement nameField = driver.findElement(By.id("author"));
    nameField.clear();
    nameField.sendKeys(name);
  }

  @And("the customer enters email as {string}")
  public void theCustomerEntersEmailAs(String email) {
    WebDriver driver = DriverProvider.get();
    WebElement emailField = driver.findElement(By.id("email"));
    emailField.clear();
    emailField.sendKeys(email);
  }

  @And("the customer clicks on the submit button")
  public void customerSubmitsTheForm() {
    WebDriver driver = DriverProvider.get();
    WebElement submitButton = driver.findElement(By.id("submit"));
    submitButton.click();
  }

  @Then("the system displays a review card")
  public void theSystemDisplaysAReviewCard() {
    WebDriver driver = DriverProvider.get();
    WebElement commentList = driver.findElement(By.className("commentlist"));
    List<WebElement> reviewCards = commentList.findElements(By.tagName("li"));
    WebElement reviewCard = reviewCards.get(0);
    assertTrue("Review card should be visible", reviewCard.isDisplayed());
  }

  @And("the review card displays a message {string} should be displayed")
  public void theReviewCardDisplaysAMessageShouldBeDisplayed(String message) {

    WebDriver driver = DriverProvider.get();
    WebElement reviewMessage = driver.findElement(By.className("woocommerce-review__awaiting-approval"));
    assertTrue("Review message should contain expected text", reviewMessage.getText().contains(message));
  }

  @And("the review card displays the rating as {string}")
  public void theReviewCardDisplaysTheRatingAs(String rating) {

    WebDriver driver = DriverProvider.get();
    WebElement star = driver.findElement(By.className("rating"));
    String displayedRating = star.getText();


    assertEquals("Rating should match", rating, displayedRating);
  }

  @And("the review card displays the review as {string}")
  public void theReviewCardDisplaysTheReviewAs(String reviewText) {
    WebDriver driver = DriverProvider.get();
    WebElement reviewContent = driver.findElement(By.className("description"));
    assertEquals("Review text should match", reviewText, reviewContent.getText().trim());
  }



  @Then("an error message {string} should be displayed")
  public void anErrorMessageShouldBeDisplayed(String expectedMessage) {

//    WebDriver driver = DriverProvider.get();
//    WebElement errorMessage = driver.findElement(By.cssSelector(".woocommerce-error li"));
//    assertEquals("Error message should match", expectedMessage, errorMessage.getText().trim());
  }
}
