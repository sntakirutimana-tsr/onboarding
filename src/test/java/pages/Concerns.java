package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.Envs.DEFAULT_EXPLICIT_WAIT_TIMEOUT;

public abstract class Concerns {
  private final WebDriver driver;
  private final WebDriverWait wait;

  public Concerns(WebDriver webDriver) {
    driver = webDriver;
    wait = new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_EXPLICIT_WAIT_TIMEOUT));
  }

  public WebDriver getDriver() {
    return driver;
  }

  public WebDriverWait getWait() {
    return wait;
  }
}
