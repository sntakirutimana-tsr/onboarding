package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExtendedHelpers {

  private static WebDriverWait wait(WebDriver driver, int timeoutInSecs) {
    return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSecs));
  }

  public static void executeScript(WebDriver driver, String script, Object... args) {
    ((JavascriptExecutor) driver)
      .executeScript(script, args);
  }

  public static void select(WebDriver driver, WebElement self, String partialText) {
    self.click();  // Force proxy resolution
    Select selector = new Select(self);
    selector.selectByContainsVisibleText(partialText);

    try {
      waitForDisappearance(driver, self, 5);
    } catch (Exception ignored) {
      executeScript(driver, "arguments[0].dispatchEvent(new Event('change', { bubbles: true }))", self);
      waitForDisappearance(driver, self, 5);
    }
  }

  public static void waitForDisappearance(WebDriver driver, WebElement element, int timeoutInSec) {
    waitFor(driver, d -> ExpectedConditions.stalenessOf(element), timeoutInSec);
  }

  public static void waitForElementToBeInteractive(WebDriver driver, WebElement element, int timeoutInSec) {
    waitFor(driver, ExpectedConditions.elementToBeClickable(element), timeoutInSec);
  }

  public static void waitFor(WebDriver driver, ExpectedCondition<?> condition, int timeoutInSec) {
    wait(driver, timeoutInSec).until(condition);
  }

  public static void waitForVisibility(WebDriver driver, By locator, int timeoutInSec) {
    waitFor(driver, ExpectedConditions.visibilityOfElementLocated(locator), timeoutInSec);
  }

  public static void waitForVisibility(WebDriver driver, WebElement element, int timeoutInSec) {
    waitFor(driver, ExpectedConditions.visibilityOf(element), timeoutInSec);
  }
}
