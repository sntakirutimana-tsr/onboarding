package com.pages.product;

import com.pages.product.components.Paginator;
import com.pages.product.components.PriceRangeFilter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class StorePage extends ProductsPage {
  public StorePage(String headerText, WebDriver driver) {
    super(headerText, driver);
  }

  @Override
  public void prepareIsLoadedCheckpoints() {
    super.prepareIsLoadedCheckpoints();
    new PriceRangeFilter(driver, findElement(By.id("woocommerce_price_filter-3")), 10, 150)
      .ensureAllCheckpointsAreReady();
    new Paginator(getDriver(), findElement(By.cssSelector("nav.woocommerce-pagination"))).ensureAllCheckpointsAreReady();
  }
}
