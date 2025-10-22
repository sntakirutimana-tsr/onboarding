package com.pages.concerns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public interface Waitable extends Drivable {
  void waitForElementToBeInteractive(WebElement element, int timeoutInSec);

  void waitFor(ExpectedCondition<?> condition, int timeoutInSec);

  void waitForVisibility(By locator, int timeoutInSecs);

  void waitForVisibility(WebElement element, int timeoutInSecs);

  void waitForTextVisibility(WebElement element, String expected, int timeoutInSecs);
}
