package com.pages.product;

import com.pages.product.components.Paginator;
import com.pages.product.components.cards.RegularProdCard;
import com.utils.Executor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public final class StorePage extends ProductsPage {
  public StorePage(String headerText, WebDriver driver) {
    super(headerText, driver);
  }

  public Paginator paginator() {
    return new Paginator(getDriver(), findElement(By.cssSelector("nav.woocommerce-pagination")));
  }

  public List<String> productNameList() {
    return productCardList().stream()
      .map(RegularProdCard::getName)
      .toList();
  }

  public boolean isLoadedAfterPaginationEffects() {
    return Executor.hasEvaluatedAndSucceed(() -> {
      prepareHeaderAndResultsCounterCheckpoints();
      paginator().ensureAllCheckpointsAreReady();
    });
  }

  @Override
  public void prepareIsLoadedCheckpoints() {
    super.prepareIsLoadedCheckpoints();
    priceRangeFilter(10, 150).ensureAllCheckpointsAreReady();
    paginator().ensureAllCheckpointsAreReady();
  }
}
