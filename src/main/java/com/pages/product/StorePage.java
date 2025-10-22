package com.pages.product;

import com.pages.product.components.Paginator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class StorePage extends ProductsPage {
  public StorePage(String headerText, WebDriver driver) {
    super(headerText, driver);
  }

  @Override
  public void prepareIsLoadedCheckpoints() {
    super.prepareIsLoadedCheckpoints();
    new Paginator(getDriver(), findElement(By.cssSelector("nav.woocommerce-pagination"))).ensureAllCheckpointsAreReady();
  }
}
