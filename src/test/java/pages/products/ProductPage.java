package pages.products;

import pages.BasePage;
import pages.products.components.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utils.Executor.*;

public class ProductPage extends BasePage {
  private final By searchByNameLocator = By.id("woocommerce_product_search-1");
  private final By ourBestSellersLocator = By.id("woocommerce_top_rated_products-3");
  private final By priceFilterLocator = By.id("woocommerce_price_filter-3");
  private final By sortByLocator = By.cssSelector("form.woocommerce-ordering");
  private final By categoryFilterLocator = By.id("woocommerce_product_categories-3");

  public ProductPage(WebDriver driver) {
    super(driver);
  }

  public String getHeaderText() {
    return getText(By.tagName("h1"));
  }

  public ProductList productList() {
    return new ProductList(getDriver());
  }

  public SearchByName searchByName() {
    return new SearchByName(getDriver(), searchByNameLocator);
  }

  public SortBy sortBy() {
    return new SortBy(getDriver(), sortByLocator);
  }

  OurBestSellers ourBestSellers() {
    return new OurBestSellers(getDriver(), ourBestSellersLocator);
  }

  public SubCategoryFilter subCategoryFilter() {
    return new SubCategoryFilter(getDriver(), categoryFilterLocator);
  }

  public PriceRangeFilter priceFilter() {
    return new PriceRangeFilter(getDriver(), priceFilterLocator);
  }

  public final boolean hasNoProductFoundMessage() {
    return hasEvaluatedSuccessfully(() ->
      findBy(
        By.xpath(
          "//p[contains(@class, 'woocommerce-no-products-found') and contains(text(), 'No products were found matching your selection.')]")
      )
    );
  }

  public boolean hasOurBestSeller() {
    return hasEvaluatedSuccessfully(ourBestSellers()::ensureHasTitleAndThreeProducts);
  }
}
