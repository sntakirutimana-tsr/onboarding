package com.pages.concerns;

import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public abstract class Waits implements Waitable {

  private WebDriverWait wait(int timeoutInSecs) {
    return new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSecs));
  }

  @Override
  public void waitForDisappearance(WebElement element, int timeoutInSec) {
    wait(timeoutInSec).until(ExpectedConditions.invisibilityOf(element));
  }

  @Override
  public void waitForElementToBeInteractive(WebElement element, int timeoutInSec) {
    wait(timeoutInSec).until(ExpectedConditions.elementToBeClickable(element));
  }

  @Override
  public void waitFor(ExpectedCondition<?> condition, int timeoutInSec) {
    wait(timeoutInSec).until(condition);
  }

  @Override
  public void waitForVisibility(By locator, int timeoutInSecs) {
    wait(timeoutInSecs).until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  @Override
  public void waitForVisibility(WebElement element, int timeoutInSecs) {
    wait(timeoutInSecs).until(ExpectedConditions.visibilityOf(element));
  }
}


