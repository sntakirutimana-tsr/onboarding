package steps;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import constants.Endpoint;
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
  public void customerProvidesValidCredentials(List<Map<String, String>> credentials) {
    Random random = new Random();

    randomUsername = credentials.get(0).get("username") + random.nextInt(10000);
    String randomEmail = credentials.get(0).get("email") + random.nextInt(10000);
    String password = credentials.get(0).get("password");

    accountPage.enterRegistrationUsername(randomUsername);
    accountPage.enterRegistrationEmail(randomEmail);
    accountPage.enterRegistrationPassword(password);
  }

  @And("Customer submit registration form")
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
  public void customerProvidesInvalidCredentials(List<Map<String, String>> credentials) {
    Map<String, String> credential = credentials.get(0);
    String email = credential.get("email");
    String username = credential.get("username");
    String password = credential.get("password");

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
}
