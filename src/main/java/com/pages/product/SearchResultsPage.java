package com.pages.product;

import com.pages.product.components.PriceRangeFilter;
import com.utils.Executor;
import com.utils.FormatUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage extends ProductsPage {
  public SearchResultsPage(String partialHeaderText, WebDriver driver) {
    super(FormatUtils.f("Search results: “{}”", partialHeaderText), driver);
  }

  @Override
  public PriceRangeFilter priceRangeFilter() {
    return priceRangeFilter(10, 150);
  }

  @Override
  protected void prepareIsLoadedCheckpoints() {
    waitForVisibility(
      By.xpath(FormatUtils.f("//h1[text()='{}']", getHeaderText())), 5);
  }

  public final boolean hasOnlyProductsWhoseNamesContain(String productPartialName) {
    return Executor.hasEvaluatedAndSucceed(() -> waitFor(productListSizeCondition(1), 5)) &&
      productCardList().stream()
        .allMatch(p -> p.getName().toLowerCase().contains(productPartialName));
  }
}
