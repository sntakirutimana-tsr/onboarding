package com.pages.product.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Paginator extends Component {
  public Paginator(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  @Override
  public void ensureAllCheckpointsAreReady() {
    waitForVisibility(getRoot(), 5);
  }
}
