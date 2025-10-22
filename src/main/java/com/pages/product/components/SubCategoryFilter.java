package com.pages.product.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class SubCategoryFilter extends Component {
  private WebElement dropdown;

  public SubCategoryFilter(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  @Override
  protected void initiateElements() {
    dropdown = findElement(By.id("product_cat"));
  }

  @Override
  public void ensureAllCheckpointsAreReady() {
    waitForVisibility(findElement(By.xpath("//h2[text()='Browse By Categories']")), 5);
    waitForElementToBeInteractive(dropdown, 5);
  }
}
