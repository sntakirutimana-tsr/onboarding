package steps.accountmgt;

import factory.DriverFactory;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import org.assertj.core.api.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.AccountPage;

import java.util.List;
import java.util.Map;

public class CustomerProfileSteps {

  private WebDriver driver;
  private AccountPage accountPage;

  @Given("Customer is registered with {string}, {string} and {string}")
  public void customerIsRegistered(String username, String email, String password) {
    driver = DriverFactory.getDriver();
    accountPage = new AccountPage(driver);
    accountPage.registerAccount(username, email, password);
  }

  @When("Customer navigates to the Profile page")
  public void customerNavigatesToDashboard() {
    driver.findElement(By.linkText("Profile")).click();
//    Assert.assertTrue("Not on profile page",
//      driver.getCurrentUrl().contains("profile"));
  }
//
  @Then("Customer should see their current profile details:")
  public void customer_should_see_current_profile_details(DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
    for (Map<String, String> row : rows) {
      String field = row.get("Field");
      String expectedValue = row.get("Value");
//      String actualValue = getFieldValue(field);
//      Assert.assertEquals("Mismatch in profile field: " + field, expectedValue, actualValue);
    }
  }
//
  @When("Customer updates profile details to:")
  public void customer_updates_profile_details_to(DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
    for (Map<String, String> row : rows) {
      String field = row.get("Field");
      String value = row.get("Value");
//      setFieldValue(field, value);
    }
  }
//
//  @When("clicks on {string}")
  public void clicks_on(String buttonText) {
    driver.findElement(By.xpath("//button[contains(text(),'" + buttonText + "')]")).click();
  }

  @Then("A confirmation message {string} should be displayed")
  public void a_confirmation_message_should_be_displayed(String expectedMessage) {
    WebElement messageElement = driver.findElement(By.cssSelector(".woocommerce-message"));
//    Assert.assertEquals("Confirmation message mismatch", expectedMessage, messageElement.getText().trim());
  }

  @Then("Updated details should be immediately reflected in the profile")
  public void updated_details_should_be_immediately_reflected_in_profile() {
    // This can be verified by re-reading fields or refreshing the page
    driver.navigate().refresh();
    WebElement confirmation = driver.findElement(By.cssSelector(".woocommerce-message"));
//    Assert.assertTrue("Updated details not reflected.",
//      confirmation.isDisplayed());
  }

  @Given("Customer is logged in and on the Profile page")
  public void customer_is_logged_in_and_on_the_profile_page() {
    // Reuse existing steps or assume login session is active
    driver.get("https://example.com/my-account/edit-account");
//    Assert.assertTrue("Not on profile page",
//      driver.getCurrentUrl().contains("edit-account"));
  }

  @When("Customer enters invalid profile details:")
  public void customer_enters_invalid_profile_details(DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
    for (Map<String, String> row : rows) {
      String field = row.get("Field");
      String value = row.get("Value");
      setFieldValue(field, value);
    }
  }

  @Then("A validation error message should be displayed for:")
  public void a_validation_error_message_should_be_displayed_for(DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
    for (Map<String, String> row : rows) {
      String field = row.get("Field");
      String expectedMessage = row.get("Message");

      WebElement errorElement = getErrorElement(field);
//      Assert.assertEquals("Validation message mismatch for field: " + field,
//        expectedMessage, errorElement.getText().trim());
    }
  }

  // ------------------------
  // Helper methods
  // ------------------------
  private String getFieldValue(String field) {
    switch (field) {
      case "First name":
        return driver.findElement(By.id("account_first_name")).getAttribute("value");
      case "Last name":
        return driver.findElement(By.id("account_last_name")).getAttribute("value");
      case "Display name":
        return driver.findElement(By.id("account_display_name")).getAttribute("value");
      case "Email address":
        return driver.findElement(By.id("account_email")).getAttribute("value");
      default:
        throw new IllegalArgumentException("Unknown field: " + field);
    }
  }

  private void setFieldValue(String field, String value) {
    By locator;
    switch (field) {
      case "First name":
        locator = By.id("account_first_name");
        break;
      case "Last name":
        locator = By.id("account_last_name");
        break;
      case "Display name":
        locator = By.id("account_display_name");
        break;
      case "Email address":
        locator = By.id("account_email");
        break;
      default:
        throw new IllegalArgumentException("Unknown field: " + field);
    }
    WebElement input = driver.findElement(locator);
    input.clear();
    input.sendKeys(value);
  }

  private WebElement getErrorElement(String field) {
    // Adjust locators based on UI error display
    switch (field) {
      case "Email address":
        return driver.findElement(By.id("account_email-error"));
      default:
        throw new IllegalArgumentException("Unknown field for validation: " + field);
    }
  }

  @And("clicks on {string}")
  public void clicksOn(String arg0) {
    // Write code here that turns the phrase above into concrete actions
//    throw new PendingException();
  }
}

