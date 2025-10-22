package com.pages.product.components.cards;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegularProdCard extends ProductCard {
  public RegularProdCard(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  @Override
  protected void initiateElements() {
    name = findElement(By.cssSelector("h2.woocommerce-loop-product__title"));
  }
}
