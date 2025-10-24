package com.pages.product;

import com.pages.product.components.PriceRangeFilter;
import org.openqa.selenium.WebDriver;

public final class WomenProductsPage extends ProductsPage {
  public WomenProductsPage(String headerText, WebDriver driver) {
    super(headerText, driver);
  }

  @Override
  public PriceRangeFilter priceRangeFilter() {
    return priceRangeFilter(10, 100);
  }

  @Override
  public void prepareIsLoadedCheckpoints() {
    super.prepareIsLoadedCheckpoints();
    priceRangeFilter().ensureAllCheckpointsAreReady();
  }
}
