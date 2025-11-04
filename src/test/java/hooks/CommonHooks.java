package hooks;

import factory.DriverFactory;

import utils.RunContext;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import org.openqa.selenium.WebDriver;

public class CommonHooks {

  private WebDriver driver;

  @Before
  public void before() {
    String browser = System.getProperty("browser", "chrome");
    driver = DriverFactory.initializeDriver(browser);
  }

  @After
  public void after() {
    driver.quit();
    RunContext.clear();
  }
}
