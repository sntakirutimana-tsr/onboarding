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

  public static ProductsPage buildFor(String pageName, WebDriver driver) {
    String page = pageName.replaceAll("'s$", "").toLowerCase();
    return switch (page) {
      case "accessories" -> new AccessoriesProductsPage("Accessories", driver);
      case "men" -> new MenProductsPage("Men", driver);
      case "women" -> new WomenProductsPage("Women", driver);
      case "store" -> new StorePage("Store", driver);
      default -> new ProductsPage(pageName, driver) {
        @Override
        public PriceRangeFilter priceRangeFilter() {
          return priceRangeFilter(10, 150);
        }

        @Override
        public boolean hasProductList() {
          return Executor.hasEvaluatedAndSucceed(() ->
            waitFor(productListSizeCondition(1), 5));
        }
      };
    };
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

  public boolean areAllProductsInPriceRange(double minPrice, double maxPrice) {
    return productCardList().stream()
      .allMatch(p -> {
        double productPrice = p.getPrice();
        return productPrice >= minPrice && productPrice <= maxPrice;
      });
  }

  protected ExpectedCondition<Boolean> productListSizeCondition(int minSize) {
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

  public boolean hasProductList() {
    return Executor.hasEvaluatedAndSucceed(() -> waitFor(productListSizeCondition(3), 5));
  }

  public final boolean hasProductList(String category) {
    return hasProductList() &&
      productCardList().stream()
        .allMatch(p -> Executor.hasEvaluatedAndSucceed(() -> p.ensureAllCheckpointsAreReady(category)));
  }

  public final boolean hasNoProductFoundMessage() {
    return Executor.hasEvaluatedAndSucceed(() ->
      waitForVisibility(
        By.xpath("//p[contains(@class, 'woocommerce-no-products-found') and contains(text(), 'No products were found matching your selection.')]"), 5));
  }

  public String getResultsCount() {
    return resultsCounter.getText();
  }

  public boolean hasOurBestSellers() {
    return Executor.hasEvaluatedAndSucceed(() ->
      new OurBestSellers(driver, findElement(By.id("woocommerce_top_rated_products-3"))).ensureAllCheckpointsAreReady()
    );
  }

  public final PriceRangeFilter priceRangeFilter(int min, int max) {
    return new PriceRangeFilter(driver, findElement(By.id("woocommerce_price_filter-3")), min, max);
  }

  public abstract PriceRangeFilter priceRangeFilter();

  public SearchByName searchByName() {
    return new SearchByName(driver, findElement(By.id("woocommerce_product_search-1")));
  }

  public SortBy sortBy() {
    return new SortBy(driver, findElement(By.cssSelector("select[name='orderby']")));
  }

  public SubCategoryFilter subCategoryFilter() {
    return new SubCategoryFilter(driver, findElement(By.id("woocommerce_product_categories-3")));
  }

  protected void prepareHeaderAndResultsCounterCheckpoints() {
    waitForVisibility(By.xpath(FormatUtils.f("//h1[text()=\"{}\"]", headerText)), 10);
    waitForVisibility(resultsCounter, 5);
  }

  public final boolean isLoaded(int minPrice, int maxPrice) {
    return Executor.hasEvaluatedAndSucceed(() -> prepareIsLoadedCheckpoints(minPrice, maxPrice));
  }

  void prepareCheckpoints() {
    prepareHeaderAndResultsCounterCheckpoints();
    sortBy().ensureAllCheckpointsAreReady();
    searchByName().ensureAllCheckpointsAreReady();
    subCategoryFilter().ensureAllCheckpointsAreReady();
  }

  protected final void prepareIsLoadedCheckpoints(int minPrice, int maxPrice) {
    prepareCheckpoints();
    priceRangeFilter(minPrice, maxPrice).ensureAllCheckpointsAreReady();
  }

  @Override
  protected void prepareIsLoadedCheckpoints() {
    try {
      prepareCheckpoints();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new AssertionError(e);
    }
  }
}
