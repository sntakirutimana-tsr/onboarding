package com.pages.product;

import com.pages.Page;

import com.pages.product.components.*;
import com.utils.FormatUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class ProductsPage extends Page {
  private final String headerText;

  @FindBy(css = "p.woocommerce-result-count")
  private WebElement resultsCounter;

  public ProductsPage(String headerText, WebDriver driver) {
    super(driver);
    this.headerText = headerText;
  }

  public String getResultsCount() {
    return resultsCounter.getText();
  }

  @Override
  protected void prepareIsLoadedCheckpoints() {
    waitForVisibility(By.xpath(FormatUtils.f("//h1[text()='{}']", headerText)), 5);
    waitForVisibility(resultsCounter, 5);

    WebDriver driver = getDriver();
    new SortBy(driver, findElement(By.cssSelector("select[name='orderby']"))).ensureAllCheckpointsAreReady();
    new SearchByName(driver, null).ensureAllCheckpointsAreReady();
    new OurBestSellers(driver, findElement(By.id("woocommerce_top_rated_products-3")));
    new SubCategoryFilter(driver, findElement(By.id("woocommerce_product_categories-3"))).ensureAllCheckpointsAreReady();
    new PriceRangeFilter(driver, findElement(By.id("woocommerce_price_filter-3"))).ensureAllCheckpointsAreReady();
  }
}
