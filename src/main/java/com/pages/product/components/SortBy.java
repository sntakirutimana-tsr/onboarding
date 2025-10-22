package com.pages.product.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class SortBy extends Component {
  public SortBy(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  @Override
  public void ensureAllCheckpointsAreReady() {
    waitForElementToBeInteractive(getRoot(), 5);
  }
}
