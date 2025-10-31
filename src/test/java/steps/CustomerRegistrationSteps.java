package steps;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import constants.Endpoint;
import domain.Customer;
import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AccountPage;
import utils.ConfigLoader;

public class CustomerRegistrationSteps {
  private String randomUsername;
  private final WebDriver driver = DriverFactory.getDriver();
  private final AccountPage accountPage = new AccountPage(driver);

  @Given("Customer is on the registration form")
  public void customerIsOnTheRegistrationForm() {
    accountPage.load(Endpoint.ACCOUNT.url);
  }

  @When("Customer provides valid credentials")
  public void customerProvidesValidCredentials(Customer customer) {
    Random random = new Random();

    randomUsername = customer.getUsername() + random.nextInt(10000);
    String randomEmail = customer.getEmail() + random.nextInt(10000);
    String password = customer.getPassword();

    accountPage.enterRegistrationUsername(randomUsername);
    accountPage.enterRegistrationEmail(randomEmail);
    accountPage.enterRegistrationPassword(password);
  }

  @And("Customer clicks REGISTER button")
  public void customerSubmitRegistrationForm() {
    accountPage.submitRegistrationForm();
  }

  @Then("Customer with {string} should be registered with a welcome message displayed")
  public void customerIsRedirectedToTheirDashboard(String username) {
    String actualMessage = accountPage.getWelcomeMessage();
    String expectedMessage = "Hello " + randomUsername + " (not " + randomUsername + "? Log out)";
    assertEquals(expectedMessage, actualMessage);
  }

  @When("Customer provides invalid credentials")
  public void customerProvidesInvalidCredentials(Customer customer) {

    String email = customer.getEmail();
    String username = customer.getUsername();
    String password = customer.getPassword();

    email = email == null ? "" : email;
    username = username == null ? "" : username;
    password = password == null ? "" : password;

    accountPage.enterRegistrationEmail(email);
    accountPage.enterRegistrationUsername(username);
    accountPage.enterRegistrationPassword(password);
  }

  @Then("Registration fails with message {string} displayed")
  public void registrationFailsWithMessageDisplayed(String message) {
    String actualMessage = accountPage.getErrorMessage();
    Assert.assertEquals(actualMessage, message);
  }

  @And("Customer remains on the registration form")
  public void customerRemainsOnTheRegistrationForm() {
    assertEquals(ConfigLoader.getInstance().getBaseUrl() + "/account/", driver.getCurrentUrl());
  }

  @Then("Browser should show email validation message {string}")
  public void browserShouldShowEmailValidationMessage(String message) {
    String validationMessage = accountPage.getRegistrationEmailValidationMessage();
    assertEquals(validationMessage, message);
  }
}
