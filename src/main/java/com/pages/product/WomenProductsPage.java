package com.pages.product;

import org.openqa.selenium.WebDriver;

public final class WomenProductsPage extends ProductsPage {
  public WomenProductsPage(String headerText, WebDriver driver) {
    super(headerText, driver);
  }

  @Override
  public void prepareIsLoadedCheckpoints() {
    super.prepareIsLoadedCheckpoints();
    priceRangeFilter(10, 100).ensureAllCheckpointsAreReady();
  }
}
