package com.pages.concerns;

import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public abstract class Waits implements Waitable {

  private WebDriverWait wait(int timeoutInSecs) {
    return new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSecs));
  }

  @Override
  public WebElement waitForVisibility(By locator, int timeoutInSecs) {
    return wait(timeoutInSecs).until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  @Override
  public void waitForVisibility(WebElement element, int timeoutInSecs) {
    wait(timeoutInSecs).until(ExpectedConditions.visibilityOf(element));
  }

  @Override
  public void waitForTextVisibility(WebElement element, String expected, int timeoutInSecs) {
    wait(timeoutInSecs)
      .until(ExpectedConditions.textToBePresentInElement(element, expected));
  }
}


