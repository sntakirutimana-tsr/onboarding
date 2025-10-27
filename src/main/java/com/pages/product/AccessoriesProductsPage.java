package com.pages.product;

import com.pages.product.components.PriceRangeFilter;

import org.openqa.selenium.WebDriver;

public final class AccessoriesProductsPage extends ProductsPage {
  public AccessoriesProductsPage(String headerText, WebDriver driver) {
    super(headerText, driver);
  }

  @Override
  public PriceRangeFilter priceRangeFilter() {
    return priceRangeFilter(10, 80);
  }

  @Override
  public void prepareIsLoadedCheckpoints() {
    super.prepareIsLoadedCheckpoints();
    priceRangeFilter().ensureAllCheckpointsAreReady();
  }
}
