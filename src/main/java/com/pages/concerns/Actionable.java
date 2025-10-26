package com.pages.concerns;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public abstract class Actionable extends Waits {

  protected void executeScript(String script, Object... args) {
    ((JavascriptExecutor) getDriver())
      .executeScript(script, args);
  }

  public void select(WebElement self, String partialText) {
    self.click();  // Force proxy resolution
    Select selector = new Select(self);
    selector.selectByContainsVisibleText(partialText);

    try {
      waitForDisappearance(self, 10);
    } catch (Exception ignored) {
      executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }))", self);
      waitForDisappearance(self, 10);
    }
  }
}
