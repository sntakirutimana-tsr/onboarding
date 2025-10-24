package com.pages.product.components.cards;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class BestSellerProdCard extends ProductCard {
  public BestSellerProdCard(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  @Override
  protected void initiateElements() {
    name = findElement(By.cssSelector("span.product-title"));
  }

  
}
