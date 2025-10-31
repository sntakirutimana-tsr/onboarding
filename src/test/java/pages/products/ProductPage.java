package pages.products;

import pages.BasePage;
import pages.products.components.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.Executor.*;
import static utils.ExtendedHelpers.*;

public class ProductPage extends BasePage {
  @FindBy(css = "h1")
  private WebElement header;
  @FindBy(id = "woocommerce_product_search-1")
  private WebElement searchByNameShadow;
  @FindBy(id = "woocommerce_top_rated_products-3")
  private WebElement ourBestSellersShadow;
  @FindBy(id = "woocommerce_price_filter-3")
  private WebElement priceFilterShadow;
  @FindBy(css = "select[name='orderby']")
  private WebElement sortByShadow;
  @FindBy(id = "woocommerce_product_categories-3")
  private WebElement categoryFilterShadow;

  private final SearchByName searchByName;
  private final ProductList productList;
  private final SortBy sortBy;
  private final OurBestSellers ourBestSellers;
  private final SubCategoryFilter subCategoryFilter;
  private final PriceRangeFilter priceRangeFilter;

  public ProductPage(WebDriver driver) {
    super(driver);

    searchByName = new SearchByName(driver, searchByNameShadow);
    productList = new ProductList(driver);
    sortBy = new SortBy(driver, sortByShadow);
    ourBestSellers = new OurBestSellers(driver, ourBestSellersShadow);
    subCategoryFilter = new SubCategoryFilter(driver, categoryFilterShadow);
    priceRangeFilter = new PriceRangeFilter(driver, priceFilterShadow);
  }

  public WebElement getHeader() {
    return header;
  }

  public String getHeaderText() {
    return getText(header);
  }

  public ProductList getProductList() {
    return productList;
  }

  public SearchByName getSearchByName() {
    return searchByName;
  }

  public SortBy getSortBy() {
    return sortBy;
  }

  public OurBestSellers getOurBestSellers() {
    return ourBestSellers;
  }

  public SubCategoryFilter getCategoryFilter() {
    return subCategoryFilter;
  }

  public PriceRangeFilter getPriceFilter() {
    return priceRangeFilter;
  }

  public final boolean hasNoProductFoundMessage() {
    return hasEvaluatedAndSucceed(() ->
      waitForVisibility(
        driver,
        By.xpath("//p[contains(@class, 'woocommerce-no-products-found') and contains(text(), 'No products were found matching your selection.')]"), 5));
  }

  public boolean hasOurBestSeller() {
    return hasEvaluatedAndSucceed(ourBestSellers::ensureIsReady);
  }

  public boolean isReady() {
    return hasEvaluatedAndSucceed(() -> {
      waitForVisibility(driver, header, 10);
      waitForVisibility(driver, searchByNameShadow, 2);
      waitForVisibility(driver, sortByShadow, 2);
      waitForVisibility(driver, categoryFilterShadow, 2);
      waitForVisibility(driver, priceFilterShadow, 2);
    });
  }
}
