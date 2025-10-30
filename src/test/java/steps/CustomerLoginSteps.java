package steps;

import constants.Endpoint;
import customTypes.LoginDto;
import domain.Customer;
import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.AccountPage;
import utils.ConfigLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerLoginSteps {
  private WebDriver driver;
  private AccountPage accountPage;

  @Given("Customer is on the login page")
  public void customerIsOnTheLoginPage() {
    driver = DriverFactory.getDriver();
    accountPage = new AccountPage(driver);
    // Load the page
    accountPage.load(Endpoint.ACCOUNT.url);
  }

  @When("customer enters valid/invalid credentials")
  public void customerEntersValidCredentials(LoginDto loginDto) {
    String username_or_email = loginDto.getUsername_or_email();
    String password = loginDto.getPassword();

    username_or_email = username_or_email == null ? "" : username_or_email;
    password = password == null ? "" : password;

    accountPage.enterUsername(username_or_email);
    accountPage.enterPassword(password);
  }

  @And("Customer submit login form")
  public void customerClicksOnButton() {
    accountPage.submitLoginForm();
  }

  @Then("Customer, {customer} is logged in")
  public void customerShouldBeRedirectedToTheirDashboard(Customer customer) {
    String actualMessage = accountPage.getWelcomeMessage();
    String expectedMessage = "Hello " + customer.getUsername() + " (not " + customer.getUsername() + "? Log out)";
    assertEquals(expectedMessage, actualMessage);
  }

  @Then("Login fails with message {string} displayed")
  public void loginFailsWithMessageDisplayed(String message) {
    String actualMessage = accountPage.getErrorMessage();
    assertEquals(message, actualMessage);
  }

  @And("Customer stays login form")
  public void customerStaysOnTheLoginForm() {
    assertEquals(ConfigLoader.getInstance().getBaseUrl() + "/account/", driver.getCurrentUrl());
  }
}
