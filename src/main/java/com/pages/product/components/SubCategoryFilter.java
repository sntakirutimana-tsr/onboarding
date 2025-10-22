package com.pages.product.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class SubCategoryFilter extends Component {
  public SubCategoryFilter(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  @Override
  public void ensureAllCheckpointsAreReady() {
    waitForVisibility(findElement(By.xpath("//h2[text()='Browse By Categories']")), 5);
    waitForElementToBeInteractive(findElement(By.id("product_cat")), 5);
  }
}
