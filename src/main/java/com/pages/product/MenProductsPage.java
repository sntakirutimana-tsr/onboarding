package com.pages.product;

import org.openqa.selenium.WebDriver;

public final class MenProductsPage extends ProductsPage {
  public MenProductsPage(String headerText, WebDriver driver) {
    super(headerText, driver);
  }

  @Override
  public void prepareIsLoadedCheckpoints() {
    super.prepareIsLoadedCheckpoints();
    priceRangeFilter(20, 150).ensureAllCheckpointsAreReady();
  }
}
