package steps.accountmgt;

import constants.Endpoint;
import factory.DriverFactory;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import org.assertj.core.api.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.AccountDetailsPage;
import pages.AccountPage;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class CustomerProfileSteps {

  private WebDriver driver;
  private AccountPage accountPage;
  private String randomUsername;
  private AccountDetailsPage accountDetailsPage;

  @Given("Customer is registered with {string}, {string} and {string}")
  public void customerIsRegistered(String username, String email, String password) {
    driver = DriverFactory.getDriver();
    accountPage = new AccountPage(driver);
    accountPage.load(Endpoint.ACCOUNT.url);
    accountDetailsPage = new AccountDetailsPage(driver);
    Random random = new Random();

    randomUsername = username + random.nextInt(10000);
    String randomEmail = email + random.nextInt(10000);

    accountPage.enterRegistrationUsername(randomUsername);
    accountPage.enterRegistrationEmail(randomEmail);
    accountPage.enterRegistrationPassword(password);
    accountPage.submitRegistrationForm();
  }

  @When("Customer navigates to the Profile page")
  public void customerNavigatesToProfile() {
    accountDetailsPage.load("/account/edit-account/");
  }

  @Then("Customer should fill their profile details:")
  public void customer_should_see_current_profile_details(DataTable dataTable) {
    Map<String, String> profileDetails = dataTable.asMap(String.class, String.class);

    String firstName = profileDetails.get("First name");
    String lastName = profileDetails.get("Last name");
    accountDetailsPage.enterFirstName(firstName);
    accountDetailsPage.enterLastName(lastName);


  }

  @When("clicks on {string}")
  public void clicks_on(String buttonText) {
    accountDetailsPage.submitProfileForm();
  }

  @Then("A confirmation message {string} should be displayed")
  public void a_confirmation_message_should_be_displayed(String expectedMessage) {
    WebElement messageElement = driver.findElement(By.cssSelector(".woocommerce-message"));
    assertEquals(expectedMessage, messageElement.getText());
  }

}

