package tests.steps;

import com.pages.home.Homepage;

import io.cucumber.java.en.Given;

import static org.junit.Assert.assertTrue;

public final class CommonSteps {

  @Given("a Customer is on the homepage")
  public void customer_is_on_the_homepage() {
    Homepage page = Homepage.visit();
    assertTrue(page.isLoaded());
  }
}
