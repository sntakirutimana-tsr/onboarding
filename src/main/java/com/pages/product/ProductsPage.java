package com.pages.product;

import com.pages.Page;
import com.pages.product.components.*;
import com.pages.product.components.cards.RegularProdCard;
import com.utils.Executor;
import com.utils.FormatUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

public abstract class ProductsPage extends Page {
  private final String headerText;

  @FindBy(css = "p.woocommerce-result-count")
  private WebElement resultsCounter;

  public ProductsPage(String headerText, WebDriver driver) {
    super(driver);
    this.headerText = headerText;
  }

  List<WebElement> productList() {
    return getDriver().findElements(By.cssSelector("ul.products li"));
  }

  public final boolean hasProductList(String category) {
    ExpectedCondition<Boolean> listSizeBetween3And8 = new ExpectedCondition<>() {
      @Override
      public Boolean apply(WebDriver driver) {
        int size = productList().size();
        return size >= 3 && size <= 8;
      }

      @Override
      public String toString() {
        return "Number of products must be between 1 and 8";
      }
    };
    return Executor.hasEvaluatedAndSucceed(() -> waitFor(listSizeBetween3And8, 5)) && productList().stream()
      .map(r -> new RegularProdCard(getDriver(), r))
      .allMatch(p -> Executor.hasEvaluatedAndSucceed(() -> p.ensureAllCheckpointsAreReady(category)));
  }

  public String getResultsCount() {
    return resultsCounter.getText();
  }

  public boolean hasOurBestSellers() {
    return Executor.hasEvaluatedAndSucceed(() ->
      new OurBestSellers(driver, findElement(By.id("woocommerce_top_rated_products-3"))).ensureAllCheckpointsAreReady()
    );
  }

  @Override
  protected void prepareIsLoadedCheckpoints() {
    waitForVisibility(By.xpath(FormatUtils.f("//h1[text()='{}']", headerText)), 5);
    waitForVisibility(resultsCounter, 5);

    WebDriver driver = getDriver();
    new SortBy(driver, findElement(By.cssSelector("select[name='orderby']"))).ensureAllCheckpointsAreReady();
    new SearchByName(driver, findElement(By.id("woocommerce_product_search-1"))).ensureAllCheckpointsAreReady();
    new SubCategoryFilter(driver, findElement(By.id("woocommerce_product_categories-3"))).ensureAllCheckpointsAreReady();
  }
}
