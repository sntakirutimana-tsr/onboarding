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

import lombok.Getter;

import java.util.List;
import java.util.stream.IntStream;

public abstract class ProductsPage extends Page {
  @Getter
  private final String headerText;

  @FindBy(css = "p.woocommerce-result-count")
  private WebElement resultsCounter;

  public ProductsPage(String headerText, WebDriver driver) {
    super(driver);
    this.headerText = headerText;
  }

  public static ProductsPage buildFor(String page, WebDriver driver) {
    return switch (page.toLowerCase()) {
      case "accessories" -> new AccessoriesProductsPage("Accessories", driver);
      case "men" -> new MenProductsPage("Men", driver);
      case "women" -> new WomenProductsPage("Women", driver);
      default -> new StorePage("Store", driver);
    };
  }

  public final PriceRangeFilter priceRangeFilter(int min, int max) {
    return new PriceRangeFilter(driver, findElement(By.id("woocommerce_price_filter-3")), min, max);
  }

  public List<String> productNameList() {
    return productCardList().stream()
      .map(RegularProdCard::getName)
      .toList();
  }

  List<WebElement> productList() {
    return getDriver().findElements(By.cssSelector("ul.products li"));
  }

  protected List<RegularProdCard> productCardList() {
    return productList().stream()
      .map(r -> new RegularProdCard(getDriver(), r))
      .toList();
  }

  public boolean areProductsSortedBy(String criterion) {
    List<RegularProdCard> products = productCardList();
    return switch (criterion.toLowerCase()) {
      case "average rating" -> IntStream.range(0, products.size() - 1)
        .allMatch(i -> products.get(i).getRating() >= products.get(i + 1).getRating());
      case "price: low to high" -> IntStream.range(0, products.size() - 1)
        .allMatch(i -> products.get(i).getPrice() <= products.get(i + 1).getPrice());
      case "price: high to low" -> IntStream.range(0, products.size() - 1)
        .allMatch(i -> products.get(i).getPrice() >= products.get(i + 1).getPrice());
      default -> throw new IllegalArgumentException("Unknown sorting criterion~" + criterion);
    };
  }

  ExpectedCondition<Boolean> productListSizeCondition(int minSize) {
    return new ExpectedCondition<>() {
      @Override
      public Boolean apply(WebDriver driver) {
        int size = productList().size();
        return size >= minSize && size <= 8;
      }

      @Override
      public String toString() {
        return "Number of products must be between 1 and 8";
      }
    };
  }

  public final boolean hasProductList() {
    return Executor.hasEvaluatedAndSucceed(() -> waitFor(productListSizeCondition(3), 5));
  }

  public final boolean hasProductList(String category) {
    return hasProductList() &&
      productCardList().stream()
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

  public SearchByName searchByName() {
    return new SearchByName(driver, findElement(By.id("woocommerce_product_search-1")));
  }

  public SortBy sortBy() {
    return new SortBy(driver, findElement(By.cssSelector("select[name='orderby']")));
  }

  protected void prepareHeaderAndResultsCounterCheckpoints() {
    waitForVisibility(By.xpath(FormatUtils.f("//h1[text()='{}']", headerText)), 5);
    waitForVisibility(resultsCounter, 5);
  }

  @Override
  protected void prepareIsLoadedCheckpoints() {
    prepareHeaderAndResultsCounterCheckpoints();
    sortBy().ensureAllCheckpointsAreReady();
    searchByName().ensureAllCheckpointsAreReady();
    new SubCategoryFilter(driver, findElement(By.id("woocommerce_product_categories-3"))).ensureAllCheckpointsAreReady();
  }
}
