package com.pages.product;

import com.pages.product.components.PriceRangeFilter;

import org.openqa.selenium.WebDriver;

public final class MenProductsPage extends ProductsPage {
  public MenProductsPage(String headerText, WebDriver driver) {
    super(headerText, driver);
  }

  @Override
  public PriceRangeFilter priceRangeFilter() {
    return priceRangeFilter(20, 150);
  }

  @Override
  public void prepareIsLoadedCheckpoints() {
    super.prepareIsLoadedCheckpoints();
    priceRangeFilter().ensureAllCheckpointsAreReady();
  }
}
